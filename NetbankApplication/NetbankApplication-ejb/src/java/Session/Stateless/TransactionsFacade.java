package Session.Stateless;

import entities.Transactions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * HIT8119
 *
 * @author chandan 1785265
 */
@Stateless
public class TransactionsFacade extends AbstractFacade<Transactions>
        implements TransactionsFacadeLocal {

    @PersistenceContext(unitName = "NetbankApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionsFacade() {
        super(Transactions.class);
    }
}
