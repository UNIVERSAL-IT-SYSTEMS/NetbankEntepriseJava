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
public class ViewBalance implements ViewBalanceRemote, ViewBalanceLocal {
    @EJB
    private AccountFacadeLocal accountFacade;

    public String viewBalance(String accountNo){
        Account account = new Account();
        account = accountFacade.find(accountNo);
        if(account != null){
            return "Account balance of account number: "+ accountNo+" is: "+
                    String.valueOf(account.getBalance());
        }
        return "Account number doesnt exist";
    }

}
