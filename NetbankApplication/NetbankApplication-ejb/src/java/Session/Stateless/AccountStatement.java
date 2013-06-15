/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session.Stateless;

import entities.Account;
import entities.Transactions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chandan
 */
@Stateless
public class AccountStatement implements AccountStatementLocal {
    @EJB
    private AccountFacadeLocal accountFacade;
    private Collection<Transactions> debitCollection;
    private Collection<Transactions> creditCollection;

    @Override
    public List<Transactions> viewTransactions(String accountNo){
        Account account = accountFacade.find(accountNo);
        if(account != null){
            debitCollection = account.getDebitTransactions();
            System.out.println("##### Debit count");
            System.out.println(debitCollection.size());
            creditCollection = account.getCreditTransactions();  
            System.out.println("##### Credit count");
            System.out.println(creditCollection.size());
            List<Transactions> result = new ArrayList<Transactions>();
            if(debitCollection != null){
                result.addAll(debitCollection);
            }
            if(creditCollection!= null){
                result.addAll(creditCollection);
            }
            System.out.println("##### Total count");
            System.out.println(result.size());
            return result;
        }
        return null;
    } 
}
