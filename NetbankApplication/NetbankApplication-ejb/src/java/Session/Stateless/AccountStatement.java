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
public class AccountStatement implements AccountStatementRemote, AccountStatementLocal {
    @EJB
    private AccountFacadeLocal accountFacade;
    private Collection<Transactions> debitCollection;
    private Collection<Transactions> creditCollection;
    private Collection<Transactions> transactionCollection;

    public Collection<Transactions> viewTransactions(String accountNo){
        Account account = accountFacade.find(accountNo);
        debitCollection = account.getDebitTransactions();
        transactionCollection = debitCollection;
        creditCollection = account.getCreditTransactions();
        for (Transactions transaction : creditCollection){
            transactionCollection.add(transaction);
        }
     
        return transactionCollection;
    } 
}
