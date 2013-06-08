
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * HIT8119
 * @author chandan 1785265
 */
@Entity
@Table(name = "ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountno", query = "SELECT a FROM Account a WHERE a.accountno = :accountno"),
    @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name"),
    @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
    @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance"),
    @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password"),
    @NamedQuery(name = "Account.findByPhoneno", query = "SELECT a FROM Account a WHERE a.phoneno = :phoneno")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ACCOUNTNO")
    private String accountno;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BALANCE")
    private float balance;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHONENO")
    private String phoneno;
    @Column(name = "SECQUES")
    private String secques;
    @Column(name = "SECANS")
    private String secans;
    @OneToMany(mappedBy = "accountto", fetch = FetchType.EAGER)
    private Collection<Transactions> creditTransactions;
    @OneToMany(mappedBy = "accountfrom", fetch = FetchType.EAGER)
    private Collection<Transactions> debitTransactions;

    public Account() {
    }

    public Collection<Transactions> getCreditTransactions() {
        return creditTransactions;
    }

    public void setCreditTransactions(Collection<Transactions> creditTransactions) {
        this.creditTransactions = creditTransactions;
    }

     public Collection<Transactions> getDebitTransactions() {
        return debitTransactions;
    }

    public void setDebitTransactions(Collection<Transactions> debitTransactions) {
        this.debitTransactions = debitTransactions;
    }
    
    public Account(String accountno) {
        this.accountno = accountno;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getSecques() {
        return secques;
    }

    public void setSecques(String secques) {
        this.secques = secques;
    }

    public String getSecans() {
        return secans;
    }

    public void setSecans(String secans) {
        this.secans = secans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountno != null ? accountno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountno == null && other.accountno != null) || (this.accountno != null && !this.accountno.equals(other.accountno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Account[ accountno=" + accountno + " ]";
    }

}
