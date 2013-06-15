package Session.Stateful;

import Session.Stateless.AccountFacadeLocal;
import Session.Stateless.AccountStatementLocal;
import Session.Stateless.ResetPasswordLocal;
import Session.Stateless.TrasferFundsLocal;
import Session.Stateless.ViewBalanceLocal;
import entities.Account;
import entities.Transactions;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author chandan
 */
@Stateful
public class AccountState implements AccountStateRemote, AccountStateLocal {
    @EJB
    private AccountStatementLocal accountStatement;
    @EJB
    private ResetPasswordLocal resetPassword1;
    @EJB
    private TrasferFundsLocal trasferFunds;
    @EJB
    private ViewBalanceLocal viewBalance;
    @EJB
    private AccountFacadeLocal accountFacade;

    private Account account;
    
    public AccountState(){
        account = null;
    }
    
    @Override
    public boolean login(String accountNo, String password){
        account = accountFacade.find(accountNo);
        if (account != null && account.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public String showBalance(){
        return viewBalance.viewBalance(account.getAccountno());
    }
    
    @Override
    public String transfer(String accountToNo, float amount ){
        return trasferFunds.transferFunds(account.getAccountno(),
                accountToNo, amount);
    }
    
    @Override
    public String resetPassword(String secans){
        return resetPassword1.reset(account.getAccountno(), secans);
    }
    
    @Override
    public List<Transactions> statement(){
        return accountStatement.viewTransactions(account.getAccountno());
    }
}
