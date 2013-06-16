package Session.Stateless;

import javax.ejb.Local;

/**
 *
 * @author chandan
 */
@Local
public interface LoginLocal {

    boolean doLogin(String accountNo, String password);
}
