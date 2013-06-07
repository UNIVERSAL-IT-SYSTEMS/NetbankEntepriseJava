/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session.Stateless;

import entities.Account;
import entities.Transactions;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TrasferFunds implements TrasferFundsRemote, TrasferFundsLocal {
    @EJB
    private TransactionsFacadeLocal transactionsFacade;
    @EJB
    private AccountFacadeLocal accountFacade;

    public String transferFunds(String accountFromNo, String accountToNo, 
            float amount){
        Account accountTo = new Account();
        Account accountFrom =new Account();
        accountTo = accountFacade.find(accountToNo);
        accountFrom = accountFacade.find(accountFromNo);
        if(accountTo == null || accountFrom == null){
            return "Either accounts doesn't exist";
        }else{
            if(amount <= accountFrom.getBalance()){
                accountFrom.setBalance(accountFrom.getBalance() - amount);
                accountTo.setBalance(accountTo.getBalance() + amount);
                accountFacade.edit(accountFrom);
                accountFacade.edit(accountTo);
                //Adding time stamp to transaction
                Date date = new Date(new Date().getTime());
                Transactions transaction = new Transactions(amount, accountFrom,
                        accountTo, date );
                transactionsFacade.create(transaction);
            }else{
                return "Insufficient funds for transfer";
            }
        }
        return "transfer successfull!";
    }

}
