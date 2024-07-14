package sys.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "penman")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user-name")
    String name;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false, unique = true)
    String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users-roles",
    joinColumns = @JoinColumn(name = "user-id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role-id", referencedColumnName = "id"))
    Set<Role> roles;
}
