
package Session.Stateless;

import entities.Account;
import java.util.List;
import javax.ejb.Remote;

/**
 * HIT8119
 * @author chandan 1785265
 */
@Remote
public interface AccountFacadeRemote {

    void create(entities.Account account);

    void edit(entities.Account account);

    void remove(entities.Account account);

    entities.Account find(Object id);

    List<Account> findAll();

    List<Account> findRange(int[] range);

    int count();

}
