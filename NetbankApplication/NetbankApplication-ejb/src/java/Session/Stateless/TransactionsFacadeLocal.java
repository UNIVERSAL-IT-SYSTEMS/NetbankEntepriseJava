
package Session.Stateless;

import entities.Transactions;
import java.util.List;
import javax.ejb.Local;

/**
 * HIT8119
 * @author chandan 1785265
 */
@Local
public interface TransactionsFacadeLocal {

    void create(Transactions transactions);

    void edit(Transactions transactions);

    void remove(Transactions transactions);

    Transactions find(Object id);

    List<Transactions> findAll();

    List<Transactions> findRange(int[] range);

    int count();

}
