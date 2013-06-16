package Session.Stateless;

import javax.ejb.Remote;

/**
 *
 * @author chandan
 */
@Remote
public interface TrasferFundsRemote {

    String transferFunds(String accountFromNo, String accountToNo,
            float amount);
}
