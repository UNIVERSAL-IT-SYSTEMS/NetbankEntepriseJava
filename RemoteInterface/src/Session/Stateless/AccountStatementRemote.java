/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
