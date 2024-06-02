package ru.ddc.b2bcolab.model;

import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.function.Function;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer implements UserDetails, CredentialsContainer {
    private String phoneNumber;
    private String email;
    private String password;
    @Setter
    private Set<Authority> customerAuthorities;
    private boolean enabled;

    private Customer(String phoneNumber, String email, String password, Set<Authority> customerAuthorities, boolean enabled) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.customerAuthorities = customerAuthorities;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.customerAuthorities.stream().map(AuthorityDetails::of).toList();
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public static final class CustomerBuilder {
        private String phoneNumber;
        private String email;
        private String password;
        private List<Authority> authorities = new ArrayList<>();
        private boolean enabled = true;
        private Function<String, String> passwordEncoder;

        private CustomerBuilder() {
        }

        public CustomerBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder password(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder roles(Role... roles) {
            List<Authority> authorities = new ArrayList<>();
            for (Role role : roles) {
                authorities.add(new Authority(phoneNumber, role));
            }
            return this.authorities(authorities);
        }

        public CustomerBuilder authorities(Collection<? extends Authority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public CustomerBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public CustomerBuilder passwordEncoder(Function<String, String> passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
            return this;
        }

        public Customer build() {
            String encodedPassword = this.passwordEncoder.apply(this.password);
            return new Customer(this.phoneNumber, this.email, encodedPassword, new HashSet<>(this.authorities), this.enabled);
        }
    }
}
