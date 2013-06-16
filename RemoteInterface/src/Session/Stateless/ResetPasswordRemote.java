package Session.Stateless;

import javax.ejb.Remote;

/**
 *
 * @author chandan
 */
@Remote
public interface ResetPasswordRemote {

    String reset(String accountNo, String secans);
}
