/**
 * Класс шифрует пароль алгоритмом SHA-256.
 * 
 */
package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jvm
 */
public class EncriptPass {
    private String encriptPassword;
    
    /**
     * 
     * @param password - шифруемый пароль (String)
     * @param salts - соль (String)
     * @throws NoSuchAlgorithmException 
     */
    public EncriptPass(String password, String salts) throws NoSuchAlgorithmException {
        setEncriptPassword(password,salts);
    }

    public EncriptPass(String password) throws NoSuchAlgorithmException {
        setEncriptPassword(password,"0");
    }

    public EncriptPass() {
    }

    public void setEncriptPassword(String password, String salts) {
        password=salts+password;
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("SHA-256");
            m.update(password.getBytes(),0,password.length());
        this.encriptPassword = new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptPass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * 
     * @return - зашифрованный пароль (SHA-256) (String)
     */
    public String getEncriptPassword(){
        return this.encriptPassword;
    }
    
    public String getSalts(){
        Date time = new Date();
        
        String s = Long.toString(time.getTime());
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            return  new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptPass.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
}
