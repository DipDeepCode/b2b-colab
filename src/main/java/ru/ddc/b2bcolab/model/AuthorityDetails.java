package ru.ddc.b2bcolab.model;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityDetails implements GrantedAuthority {
    private final Authority authority;

    private AuthorityDetails(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getRole().name();
    }

    public static AuthorityDetails of(Authority authority) {
        return new AuthorityDetails(authority);
    }
}
