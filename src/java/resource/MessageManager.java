/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import java.util.ResourceBundle;

/**
 *
 * @author jvm
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.messages");
    private MessageManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
