package Session.Stateless;

import javax.ejb.Remote;

/**
 *
 * @author chandan
 */
@Remote
public interface LoginRemote {

    boolean doLogin(String accountNo, String password);
}
