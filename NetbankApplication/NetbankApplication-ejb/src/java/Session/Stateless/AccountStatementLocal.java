package Session.Stateless;

import entities.Transactions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chandan
 */
@Local
public interface AccountStatementLocal {

    List<Transactions> viewTransactions(String accountNo);
}
