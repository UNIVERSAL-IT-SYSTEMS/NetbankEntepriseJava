/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session.Stateless;

import entities.Account;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chandan
 */
@Stateless
public class ResetPassword implements ResetPasswordRemote, ResetPasswordLocal {
    @EJB
    private AccountFacadeLocal accountFacade;

    public String reset(String accountNo, String secans){
        try{
            Account account = accountFacade.find(accountNo);
            if(secans.equals(account.getSecans())){
                UUID uuid = UUID.randomUUID();
                String newPaasword = uuid.toString().substring(0, 6);
                account.setPassword(newPaasword);
                accountFacade.edit(account);
                System.out.println("Your new password is: " + newPaasword);
                return "success";
            }else{
                System.out.println("Security Answer is incorrect");
                return "failure";
            }
        }catch(Exception e){
            System.out.println("userID doesnot exist");
            return"Account null";
        }
    }
}
