package org.binar.userservice.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Kelas untuk memodelkan entity/table users ke database
 * @author Agustinus
 */
@Entity
@Data
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 25)
    private String username;

    @NotBlank
    @Size(min = 8, max = 25)
    private String password;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userxrole",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    public Users(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Users() {

    }

}
