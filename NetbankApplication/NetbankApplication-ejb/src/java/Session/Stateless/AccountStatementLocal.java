/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session.Stateless;

import entities.Transactions;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author chandan
 */
@Local
public interface AccountStatementLocal {
    Collection<Transactions> viewTransactions(String accountNo);
}
