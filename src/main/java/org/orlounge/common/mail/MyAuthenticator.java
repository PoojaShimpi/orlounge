/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.orlounge.common.mail;

/**
 *
 * @author satyam
 */
import javax.mail.PasswordAuthentication;


public class MyAuthenticator extends javax.mail.Authenticator{
        
    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {  
                        return new PasswordAuthentication("sonisatyam92",
                                "soni9377326813");
                    }  
}
