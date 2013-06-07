
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * HIT8119
 * @author chandan 1785265
 */
@Entity
@Table(name = "TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByTransactionid", query = "SELECT t FROM Transactions t WHERE t.transactionid = :transactionid"),
    @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transactions.findByDate", query = "SELECT t FROM Transactions t WHERE t.date = :date")})
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSACTIONID")
    private Integer transactionid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNT")
    private float amount;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "ACCOUNTTO", referencedColumnName = "ACCOUNTNO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Account accountto;
    @JoinColumn(name = "ACCOUNTFROM", referencedColumnName = "ACCOUNTNO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Account accountfrom;

    public Transactions(){
        
    }
    public Transactions(float amount, Account accountFrom, Account accountTo,
            Date date) {
        this.amount = amount;
        this.accountfrom = accountFrom;
        this.accountto = accountTo;
        this.date = date;
    }

    public Transactions(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public Integer getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccountto() {
        return accountto;
    }

    public void setAccountto(Account accountto) {
        this.accountto = accountto;
    }

    public Account getAccountfrom() {
        return accountfrom;
    }

    public void setAccountfrom(Account accountfrom) {
        this.accountfrom = accountfrom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionid != null ? transactionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionid == null && other.transactionid != null) || (this.transactionid != null && !this.transactionid.equals(other.transactionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transactions[ transactionid=" + transactionid + " ]";
    }

}
