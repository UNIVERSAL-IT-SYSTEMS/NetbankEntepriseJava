/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session.Stateless;

import entities.Account;
import entities.Transactions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chandan
 */
@Stateless
public class AccountStatement implements AccountStatementRemote, AccountStatementLocal {
    @EJB
    private AccountFacadeLocal accountFacade;
    private Collection<Transactions> debitCollection;
    private Collection<Transactions> creditCollection;
    private Collection<Transactions> transactionCollection;

    @Override
    public Iterable<Transactions> viewTransactions(String accountNo){
        Account account = accountFacade.find(accountNo);
        if(account != null){
            debitCollection = account.getDebitTransactions();
            creditCollection = account.getCreditTransactions();        
            Collection<Transactions> result = new ArrayList<Transactions>();
            if(debitCollection != null){
                result.addAll(debitCollection);
            }
            if(creditCollection!= null){
                result.addAll(creditCollection);
            }
            return result;
        }
        return null;
    } 
}
