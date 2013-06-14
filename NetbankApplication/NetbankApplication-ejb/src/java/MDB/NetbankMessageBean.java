package MDB;

import Session.Stateless.ResetPasswordRemote;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author chandan
 */
@MessageDriven(mappedName = "jms/queue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode",
        propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType",
        propertyValue = "javax.jms.Queue")
})
public class NetbankMessageBean implements MessageListener {
    @EJB
    private ResetPasswordRemote resetPassword;
    
    public NetbankMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof MapMessage) {
            try {
                MapMessage map = (MapMessage) message;
                String accountNo = map.getString("accountNo");
                String secAns = map.getString("secAns");
                System.out.println("Account No : " + accountNo + "ANS : " 
                        + secAns);
                resetPassword.reset(accountNo, secAns);
            } catch (JMSException e) {
                e.printStackTrace();
                System.out.println("Error: Invalid message.");
            }
        } else {
            System.err.println("Unknown message format!");
        }
    }
}
