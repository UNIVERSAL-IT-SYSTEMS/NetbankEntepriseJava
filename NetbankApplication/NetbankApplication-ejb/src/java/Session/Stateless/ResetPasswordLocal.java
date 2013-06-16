package Session.Stateless;

import javax.ejb.Local;

/**
 *
 * @author chandan
 */
@Local
public interface ResetPasswordLocal {

    String reset(String accountNo, String secans);
}
