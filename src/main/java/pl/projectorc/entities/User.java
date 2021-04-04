package pl.projectorc.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String secondName;
    @NotEmpty
    private String address;
    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities;
    @Builder.Default
    private Boolean accountNonExpired = true;
    @Builder.Default
    private Boolean accountNonLocked = true;
    @Builder.Default
    private Boolean credentialNonExpired = true;
    @Builder.Default
    private Boolean enabled = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @PrePersist
    public void createdAt() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updateAt() {
        modifiedAt = LocalDateTime.now();
    }


    // to be deleted

    public User(@NotEmpty String username, @NotEmpty String password, @NotEmpty @Email String email, @NotEmpty String firstName, @NotEmpty String secondName, @NotEmpty String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
    }
}
