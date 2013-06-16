package Session.Stateless;

import entities.Account;
import entities.Transactions;
import java.text.SimpleDateFormat;
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
            float amount) {
        Account accountTo = new Account();
        Account accountFrom = new Account();
        accountTo = accountFacade.find(accountToNo);
        accountFrom = accountFacade.find(accountFromNo);
        if (accountTo == null) {
            return "Account no you wish to trasfer doesn't exist!";
        } else if (accountToNo.equals(accountFromNo)) {
            return "You cannot trasfer money to your account!";
        } else {
            if (amount <= accountFrom.getBalance()) {
                accountFrom.setBalance(accountFrom.getBalance() - amount);
                accountTo.setBalance(accountTo.getBalance() + amount);
                accountFacade.edit(accountFrom);
                accountFacade.edit(accountTo);
                //Adding time stamp to transaction
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat
                        ("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                System.out.println(formattedDate);
                Transactions transaction = new Transactions(amount, accountFrom,
                        accountTo, formattedDate);
                transactionsFacade.create(transaction);
            } else {
                return "Insufficient funds for transfer";
            }
        }
        return "Transfer successfull. New balance: " + accountFrom.getBalance();
    }
}
