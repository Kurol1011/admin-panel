package kz.management.adminpanel.dto;

import java.util.Date;

public class RespondentDTO {

    private long id;
    private String fullName;
    private int amountComputers;
    private int amountEmployee;
    private Date date;

    public RespondentDTO() {
    }

    public RespondentDTO(long id, String fullName, int amountComputers, int amountEmployee, Date date) {
        this.id = id;
        this.fullName = fullName;
        this.amountComputers = amountComputers;
        this.amountEmployee = amountEmployee;
        this.date = date;
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

    @Override
    public String toString() {
        return "RespondentDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", amountComputers=" + amountComputers +
                ", amountEmployee=" + amountEmployee +
                ", date=" + date +
                '}';
    }
}
