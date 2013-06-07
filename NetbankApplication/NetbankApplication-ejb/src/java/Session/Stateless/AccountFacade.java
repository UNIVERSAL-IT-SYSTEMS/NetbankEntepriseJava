
package Session.Stateless;

import entities.Account;
import entities.Transactions;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * HIT8119
 * @author chandan 1785265
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeLocal, AccountFacadeRemote {
 
    @PersistenceContext(unitName = "NetbankApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
}
