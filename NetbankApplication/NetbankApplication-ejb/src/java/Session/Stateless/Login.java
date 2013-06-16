package Session.Stateless;

import entities.Account;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chandan
 */
@Stateless
public class Login implements LoginRemote, LoginLocal {

    @EJB
    private AccountFacadeLocal accountFacade;

    public boolean doLogin(String accountNo, String password) {
        Account account = accountFacade.find(accountNo);
        if (account != null && account.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
