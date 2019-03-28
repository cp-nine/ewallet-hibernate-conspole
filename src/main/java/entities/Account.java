package entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @Column(name = "account_number")
    private Long account_number;
    @Column(name = "account_name")
    private String account_name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_date")
    private Date open_date;
    @Column(name = "balance")
    private Long balance;
    @Column(name = "password")
    private String pasword;


    @ManyToOne
    @JoinColumn(name = "cif")// @JoinColumn(name = "cif", referencedColumnName = "cif")
    public Customer cif;

    public Account() {
    }

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Date getOpen_date() {
        return open_date;
    }

    public void setOpen_date(Date open_date) {
        this.open_date = open_date;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public Customer getCif() {
        return cif;
    }

    public void setCif(Customer cif) {
        this.cif = cif;
    }

}
