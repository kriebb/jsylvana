/*
 * ProjectOpgave.java
 *
 * Created on 28 november 2006, 14:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean;

import managedBean.userControl.DdlProject;

/**
 *
 * @author Kristof
 */
public class ProjectOpgave {
    
    /** Creates a new instance of ProjectOpgave */

    private DdlProject ddlProject;
    
    public ProjectOpgave() 
    {        
        ddlProject = new DdlProject();
    }

    public DdlProject getDdlProject() {
        return ddlProject;
    }

 
 
    
    
    
}
