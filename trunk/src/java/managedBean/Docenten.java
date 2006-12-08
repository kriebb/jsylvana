/*
 * ProjectOpgave.java
 *
 * Created on 28 november 2006, 14:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean;

import managedBean.userControl.DdlProjectOpgave;

/**
 *
 * @author Kristof
 */
public class Docenten {
    
    /** Creates a new instance of ProjectOpgave */

    private DdlProjectOpgave ddlProjectOpgave;
    public Docenten() 
    {        
        ddlProjectOpgave = new DdlProjectOpgave();
    }

    public DdlProjectOpgave getDdlProjectOpgave() {
        return ddlProjectOpgave;
    }

 
 
    
    
    
}
