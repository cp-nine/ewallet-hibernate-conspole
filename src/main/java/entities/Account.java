package entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @Column(name = "account_number")
    private Integer account_number;
    @Column(name = "account_name")
    private String account_name;
    @Column(name = "open_date")
    private String open_date;
    @Column(name = "balance")
    private Long balance;


    @ManyToOne
    @JoinColumn(name = "cif")// @JoinColumn(name = "cif", referencedColumnName = "cif")
    public Customer cif;

    public Account() {
    }

    public Integer getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Integer account_number) {
        this.account_number = account_number;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getOpen_date() {
        return open_date;
    }

    public void setOpen_date(String open_date) {
        this.open_date = open_date;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Customer getCif() {
        return cif;
    }

    public void setCif(Customer cif) {
        this.cif = cif;
    }

//    @Override
//    public String toString(){
//        return String.format("Brith Date: %s, Username: %s", this.cif.getbDate(), this.cif.getUsername());
//    }
}
