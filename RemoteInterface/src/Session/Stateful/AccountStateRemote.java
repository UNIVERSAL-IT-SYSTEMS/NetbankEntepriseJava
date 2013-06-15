package Session.Stateful;

import entities.Transactions;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author chandan
 */
@Remote
public interface AccountStateRemote {
    
    public boolean login(String accountNo, String password);
    
    public String showBalance();
    
    public String transfer(String accountToNo, float amount );
    
    public String resetPassword(String secans);
    
    public List<Transactions> statement();
}
