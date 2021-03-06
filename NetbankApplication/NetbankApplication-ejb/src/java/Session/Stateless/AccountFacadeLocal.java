package Session.Stateless;

import entities.Account;
import java.util.List;
import javax.ejb.Local;

/**
 * HIT8119
 *
 * @author chandan 1785265
 */
@Local
public interface AccountFacadeLocal {

    void create(Account account);

    void edit(Account account);

    void remove(Account account);

    Account find(Object id);

    List<Account> findAll();

    List<Account> findRange(int[] range);

    int count();
}
