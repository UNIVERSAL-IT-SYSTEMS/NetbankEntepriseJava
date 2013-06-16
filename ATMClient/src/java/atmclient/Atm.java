package atmclient;

import Session.Stateless.AccountStatementRemote;
import Session.Stateless.LoginRemote;
import Session.Stateless.TrasferFundsRemote;
import Session.Stateless.ViewBalanceRemote;
import entities.Transactions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * HIT8119
 *
 * @author chandan 1785265
 */
public class Atm {
    @Resource(mappedName = "jms/queue")
    private static Queue queue;
    @Resource(mappedName = "jms/queueFactory")
    private static ConnectionFactory queueFactory;

    @EJB
    private static TrasferFundsRemote trasferFunds;
    @EJB
    private static ViewBalanceRemote viewBalance;
    @EJB
    private static LoginRemote login;
    @EJB
    private static AccountStatementRemote accountStatement;
    
    public static void main(String[] args) {
        Atm atm = new Atm();
        String answer = "";
        System.out.println("Welcome to ATM banking!");
        System.out.println("Login to proceed");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your accountNo: ");
        String acctNo = input.nextLine();
        System.out.println("Enter your password: ");
        String pass = input.nextLine();
        if (login.doLogin(acctNo, pass)) {
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
                        System.out.println(viewBalance.viewBalance(acctNo));
                        break;
                    case 2:
                        System.out.println("Enter the account number you wish"
                                + " to transfer: ");
                        String acctTo = input.nextLine();
                        System.out.println("Enter amount: ");
                        String amount = input.nextLine();
                        try {
                            Float floatamount = Float.parseFloat(amount);
                            System.out.println(trasferFunds.transferFunds(
                                    acctNo, acctTo, floatamount));
                            break;
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input for amount!");
                            break;
                        }
                    case 3:
                        List<Transactions> transactions = null;
                        transactions = accountStatement.viewTransactions
                                (acctNo);
                        System.out.println("Account From      "+
                                "Account To       "+ "Amount       "+
                                "Date");
                        if (transactions != null) {
                            for (Transactions transaction : transactions) {
                                System.out.println(transaction.getAccountfrom()
                                        .getAccountno()+"           "
                                        +transaction.getAccountto().
                                        getAccountno()+
                                        "            "+transaction.getAmount()+ 
                                        "         "+ transaction.getDate());
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Enter the security answer: ");
                        String ans = input.nextLine();
                        System.out.println(atm.resetPassword(acctNo, ans));
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid input!");
                }
                System.out.println("Enter y to continue else "
                        + "any key to exist.");
                answer = input.nextLine();
            } while (answer.equalsIgnoreCase("y"));
        } else {
            System.out.println("AccountNo or password doesn't match");
        }
    }
    
     /**
     * @param accountNo
     * @param secAns
     * @return
     */
    public String resetPassword(String accountNo, String secAns) {
        Map<String, String> mData = new HashMap<String, String>();
        mData.put("accountNo", accountNo);
        mData.put("secAns", secAns);
        try {
            System.out.println("Sending Message to change password");
            sendJMSMessageToQueue(mData);
            return "Request send successfully. Check your email.";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "failure";
        }
    }
    
    private void sendJMSMessageToQueue(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = queueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            messageProducer.send(createJMSMessageForjmsQueue(session,
                    messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).
                            log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    private Message createJMSMessageForjmsQueue(Session session, 
            Object messageData) throws JMSException {
        
        MapMessage mapM = session.createMapMessage();
        if (messageData instanceof Map){
            Map msgData = (Map) messageData;
            mapM.setString("accountNo", msgData.get("accountNo").toString());
            mapM.setString("secAns", msgData.get("secAns").toString());
        }
        return mapM;
    }
}
