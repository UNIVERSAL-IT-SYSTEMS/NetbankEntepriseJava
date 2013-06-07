/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session.Stateless;

import javax.ejb.Remote;

/**
 *
 * @author chandan
 */
@Remote
public interface TrasferFundsRemote {
    
     String transferFunds(String accountFromNo, String accountToNo, 
            float amount);
}
