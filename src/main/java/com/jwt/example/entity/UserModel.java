package com.jwt.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String emailId;

    @Column
    private String pass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "role_id",
            referencedColumnName = "id"
    )
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
/*        if (role == null || role.getRoleName() == null || role.getRoleName().isEmpty()) {
            // Log the issue or assign a default role
            return Set.of(new SimpleGrantedAuthority("ROLE_USER")); // Default role
        }*/
        return Set.of(new SimpleGrantedAuthority(this.role.getRoleName()));
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

}
