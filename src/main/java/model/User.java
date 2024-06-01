package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class User {
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String email;
    @Getter
    private String phone;
    private Role role;
    @Getter
    private boolean enabled;
    @Getter
    private boolean is_moderation;

    public String getRole() {
        return role == null ? null : role.name();
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                ", is_moderation=" + is_moderation +
                '}';
    }
}
