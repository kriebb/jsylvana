/*
 * Student.java
 *
 * Created on 28 november 2006, 22:09
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
public class ViewStudent {
    
    /** Creates a new instance of Student */
    private DdlProjectOpgave ddlProjectOpgave;
    public ViewStudent() 
    {        
        ddlProjectOpgave = new DdlProjectOpgave();
    }

    public DdlProjectOpgave getDdlProjectOpgave() {
        return ddlProjectOpgave;
    }

 
    
}
