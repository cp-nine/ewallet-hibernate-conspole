package entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Transaction")
@Table(name = "tb_transaction")
public class TrxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "transaction_type")
    private String trxType;
    @Column(name = "acn_debet")
    private Long acnDebet;
    @Column(name = "acn_credit")
    private Long acnCredit;
    @Column(name = "amount")
    private Long amount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_transaction")
    private Date date;

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public Long getAcnDebet() {
        return acnDebet;
    }

    public void setAcnDebet(Long acnDebet) {
        this.acnDebet = acnDebet;
    }

    public Long getAcnCredit() {
        return acnCredit;
    }

    public void setAcnCredit(Long acnCredit) {
        this.acnCredit = acnCredit;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
