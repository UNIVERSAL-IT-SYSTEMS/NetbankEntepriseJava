package Session.Stateless;

import javax.ejb.Remote;

/**
 *
 * @author chandan
 */
@Remote
public interface ViewBalanceRemote {

    String viewBalance(String accountNo);
}
