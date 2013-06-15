/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session.Stateless;

import entities.Account;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chandan
 */
@Stateless
public class Login implements LoginLocal {
    @EJB
    private AccountFacadeLocal accountFacade;

    public boolean doLogin(String accountNo, String password){
        Account account = accountFacade.find(accountNo);
        if (account != null && account.getPassword().equals(password)){
            return true;
        }
        return false;
    }

}
