/*
 * ApplicationException.java
 *
 * Created on 1 december 2006, 11:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Utils;

/**
 *
 * @author Kristof
 */
public class ApplicationException extends Exception{
    
    /** Creates a new instance of ApplicationException */
    public ApplicationException() {
        super("ApplicationException occured");
    }
    public ApplicationException(String message)
    {
        super(message);
    }
    
}
