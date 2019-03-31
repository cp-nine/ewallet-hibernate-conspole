package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_customer")
public class Customer {

    @Id
    @Column(name = "cif")
    private String cif;
    @Column(name = "first_name")
    private String fname;
    @Column(name = "last_name")
    private String lname;
    @Column(name = "brith_date")
    private String bDate;

    //    tambahan
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cif", cascade = CascadeType.ALL)
    public Set<Account> accounts;

    public Customer() {
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
