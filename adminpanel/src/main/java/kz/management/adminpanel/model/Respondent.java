package kz.management.adminpanel.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "info_respondent_computers")
public class Respondent {
    @Id
    @Column(name = "respondent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="respondent_full_name")
    @NotEmpty(message = "fullName shouldn't be empty")
    private String fullName;

    @Column(name="amount_computers")
    @NotNull(message = "amount of computers shouldn't be empty")
    private int amountComputers;

    @Column(name="amount_employee")
    @NotNull(message = "amount of employee shouldn't be empty")
    private int amountEmployee;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    public Respondent() {
    }

    public Respondent(long id, String fullName, int amountComputers, int amountEmployee, Date date, User user) {
        this.id = id;
        this.fullName = fullName;
        this.amountComputers = amountComputers;
        this.amountEmployee = amountEmployee;
        this.date = date;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAmountComputers() {
        return amountComputers;
    }

    public void setAmountComputers(int amountComputers) {
        this.amountComputers = amountComputers;
    }

    public int getAmountEmployee() {
        return amountEmployee;
    }

    public void setAmountEmployee(int amountEmployee) {
        this.amountEmployee = amountEmployee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //todo error mb?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respondent that = (Respondent) o;
        return getId() == that.getId() && getAmountComputers() == that.getAmountComputers() && getAmountEmployee() == that.getAmountEmployee() && getFullName().equals(that.getFullName()) && getDate().equals(that.getDate()) && getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getAmountComputers(), getAmountEmployee(), getDate(), getUser());
    }
}
