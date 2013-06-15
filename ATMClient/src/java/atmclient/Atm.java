package atmclient;

import Session.Stateful.AccountStateRemote;
import entities.Transactions;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 * HIT8119
 *
 * @author chandan 1785265
 */
public class Atm {
    @EJB
    private static AccountStateRemote accountState;
    
    public static void main(String[] args) {
        String answer = "";
        System.out.println("Welcome to ATM banking!");
        System.out.println("Login to proceed");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your accountNo: ");
        String acctNo = input.nextLine();
        System.out.println("Enter your password: ");
        String pass = input.nextLine();
        if (accountState.login(acctNo, pass)) {
            do {
                System.out.println("Menu");
                System.out.println("1. Check account balance.");
                System.out.println("2. Transfer money.");
                System.out.println("3. View account statement.");
                System.out.println("4. Change your password.");
                System.out.println("5. Exit.");
                System.out.println("Enter your choice: ");
                int menuInput = Integer.parseInt(input.nextLine());
                switch (menuInput) {
                    case 1:
                        System.out.println(accountState.showBalance());
                        break;
                    case 2:
                        System.out.println("Enter the account number "
                                + "you wish to transfer: ");
                        String acctTo = input.nextLine();
                        System.out.println("Enter amount: ");
                        String amount = input.nextLine();
                        try {
                            Float floatamount = Float.parseFloat(amount);
                            System.out.println(accountState.transfer(acctTo,
                                    floatamount));
                            break;
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input for amount!");
                            break;
                        }
                    case 3:
                        List<Transactions> transactions = null;
                        transactions = accountState.statement();
                        System.out.println("Account From      "+
                                "Account To       "+ "Amount       "+
                                "Date");
                        if (transactions != null) {
                            for (Transactions transaction : transactions) {
                                System.out.println(transaction.getAccountfrom()
                                        .getAccountno()+"           "
                                        +transaction.getAccountto()
                                        .getAccountno()+
                                        "            "+transaction.getAmount()+ 
                                        "         "+ transaction.getDate());
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Enter the security answer: ");
                        String ans = input.nextLine();
                        System.out.println(accountState.resetPassword(ans));
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid input!");
                }
                System.out.println("Enter y to continue else any key to exist.");
                answer = input.nextLine();
            } while (answer.equalsIgnoreCase("y"));
        } else {
            System.out.println("AccountNo or password doesn't match");
        }
    }
}
