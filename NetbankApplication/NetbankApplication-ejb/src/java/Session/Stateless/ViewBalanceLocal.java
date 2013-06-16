package Session.Stateless;

import javax.ejb.Local;

/**
 *
 * @author chandan
 */
@Local
public interface ViewBalanceLocal {

    String viewBalance(String accountNo);
}
