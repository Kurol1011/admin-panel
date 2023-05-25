package kz.management.adminpanel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import kz.management.adminpanel.model.enums.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name", nullable = false)
    @NotEmpty(message = "name should not be empty")
    //@Size(min = 2,max = 30, message = "Name should be between 2 and 30 characters")
    //@Pattern(regexp = "\b[A-Z][a-z]*\b",message = "Name is not valid")
    private String firstName;

    @Column(name="last_name",nullable = false)
    @NotEmpty(message = "surname should not be empty")
    //@Size(min = 2,max = 45, message = "Surname should be between 2 and 30 characters")
    //@Pattern(regexp = "\b[A-Z][a-z]*\b",message = "Name is not valid")
    private String lastName;

    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Column(name="email",nullable = false)
    @NotEmpty(message = "email should not be empty")
    //@Size(max = 250, message = "email should be less than 250")
    @Email(message = "email should valid")
    private String email;

    @Column(name="password",nullable = false)
    @NotEmpty(message = "password should not be empty")
    //@Size(min = 3, max = 250, message = "password should be greater than 3(?) and less than 250 characters")
    private String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(long id, String firstName, String lastName, Date dateOfBirth, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
