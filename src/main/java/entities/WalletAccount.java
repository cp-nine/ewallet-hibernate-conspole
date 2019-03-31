package entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_wallet_account")
public class WalletAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wa_id")
    private Integer waId;
    @Column(name = "account_number")
    private Long account_number;
    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet_id;

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWaId() {
        return waId;
    }

    public void setWaId(Integer waId) {
        this.waId = waId;
    }

    public Wallet getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(Wallet wallet_id) {
        this.wallet_id = wallet_id;
    }
}
