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
public interface LoginRemote {
    
    boolean doLogin(String accountNo, String password);
}
