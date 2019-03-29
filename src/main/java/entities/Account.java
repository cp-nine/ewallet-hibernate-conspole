package entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "account_name")
    private String accountName;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_date")
    private Date open_date;
    @Column(name = "balance")
    private Long balance;
    @Column(name="username")
    private String username;
    @Column(name = "password")
    private String pasword;


    @ManyToOne
    @JoinColumn(name = "cif")// @JoinColumn(name = "cif", referencedColumnName = "cif")
    public Customer cif;

    public Account() {
    }

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountname() {
		return accountName;
	}

	public void setAccountname(String accountname) {
		this.accountName = accountname;
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


    public String getUsername() {
        return username;
    }

 
	public void setUsername(String username) {
		this.username = username;
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
