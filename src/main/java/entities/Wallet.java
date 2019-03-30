package entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tb_wallet")
public class Wallet {

    @Id
    @Column(name = "wallet_id")
    private Integer wallet_id;
    @Column(name = "description")
    private String description;
    @Column(name = "kode_toko")
    private String kode_toko;
    @Column(name = "type")
    private String type;
    @Column(name = "active_balance")
    private Long activeBalance;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "wallet_id", cascade = CascadeType.ALL)
    private Set<WalletAccount> walletaccount;


    public Integer getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(Integer wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKode_toko() {
        return kode_toko;
    }

    public void setKode_toko(String kode_toko) {
        this.kode_toko = kode_toko;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getActiveBalance() {
        return activeBalance;
    }

    public void setActiveBalance(Long activeBalance) {
        this.activeBalance = activeBalance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<WalletAccount> getWalletaccount() {
        return walletaccount;
    }

    public void setWalletaccount(Set<WalletAccount> walletaccount) {
        this.walletaccount = walletaccount;
    }
}
