package softuniBlog.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    private Integer id;

    private String email;

    private String fullName;

    private String password;

    @Transient public String getPhotosImagePath() {
        if (avatar == null || id == null) return null;

        return "/user-photos/" + id + "/" + avatar;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private Set<Role> roles;

    private Set<Article> articles;

    public User(String email, String fullName, String password){
        this.email = email;
        this.fullName = fullName;
        this.password = password;

        this.roles = new HashSet<>();
        this.articles = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "fullName", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "password", length = 60, nullable = false)
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = true, length = 64)
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @OneToMany(mappedBy = "author")
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public User(){

    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Transient
    public boolean isAdmin(){
        return this.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }

    @Transient
    public boolean isAuthor(Article article){
        return Objects.equals(this.getId(), article.getAuthor().getId());
    }

    public void setPhotos(String fileName) {
    }
}
