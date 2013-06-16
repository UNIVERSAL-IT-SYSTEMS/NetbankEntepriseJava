package Session.Stateless;

import entities.Transactions;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author chandan
 */
@Remote
public interface AccountStatementRemote {

    List<Transactions> viewTransactions(String accountNo);
}
