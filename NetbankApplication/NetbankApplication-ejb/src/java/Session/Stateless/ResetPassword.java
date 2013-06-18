/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session.Stateless;

import entities.Account;
import java.util.Date;
import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author chandan
 */
@Stateless
public class ResetPassword implements ResetPasswordLocal {
    @EJB
    private AccountFacadeLocal accountFacade;
    
    @Resource(name = "mail/myMailSession")
    private Session mailSession;
    
    public String reset(String accountNo, String secans){
        try{
            Account account = accountFacade.find(accountNo);
            if(secans.equals(account.getSecans())){
                UUID uuid = UUID.randomUUID();
                String newPaasword = uuid.toString().substring(0, 6);
                account.setPassword(newPaasword);
                accountFacade.edit(account);
                System.out.println("Your new password is: " + newPaasword);
                // Create the message object
                Message message = new MimeMessage(mailSession);

                // Adjust the recipients. Here we have only one
                // recipient. The recipient's address must be
                // an object of the InternetAddress class.
                message.setRecipients(Message.RecipientType.TO,
                               InternetAddress.parse(account.getEmail(), false));

                // Set the message's subject
                message.setSubject("Netbank password reset");

                // Insert the message's body
                message.setText("Your password has been changed successfully.\n"
                        + " Your new password is: "+newPaasword);
                Date timeStamp = new Date();
                message.setSentDate(timeStamp);

                // Use the 'send' static method of the Transport
                // class to send the message
                Transport.send(message);
                return "Request sent successfully. Check your email.";
            }else{
                System.out.println("Security answer is incorrect");
                return "Security answer is incorrect";
            }
        }catch(Exception e){
            System.out.println("Email failed");
            return"Check your mail";
        }
    }
}
