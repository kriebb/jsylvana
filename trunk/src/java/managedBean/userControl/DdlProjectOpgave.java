/*
 * DdlProjectOpgave.java
 *
 * Created on 28 november 2006, 20:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean.userControl;

import Domein.DomeinController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Kristof
 */
public class DdlProjectOpgave extends DdlProject {
    
    private final int DEFAULT_NOT_USED=-1;
    private int selectedProjectOpgaveID=DEFAULT_NOT_USED;
    private List<SelectItem> projectOpgaven;


    /** Creates a new instance of DdlProjectOpgave */
    public DdlProjectOpgave() 
    {
    }
    
    public int getSelectedProjectOpgaveID() 
    {
        return selectedProjectOpgaveID;
    }

    public void setSelectedProjectOpgaveID(int selectedProjectOpgaveID) 
    {
        this.selectedProjectOpgaveID = selectedProjectOpgaveID;
    }
    public List<SelectItem> getProjectOpgaven() 
    {
        projectOpgaven = new ArrayList<SelectItem>();
        Collection<Domein.ProjectPackage.ProjectOpgave> colProjectOpgaven = DomeinController.getInstance().getProjectBeheerder().GetOpgavenByProjectId(super.getSelectedProjectID());
        projectOpgaven.add(new SelectItem(DEFAULT_NOT_USED, "--Selecteer een projectopgave--",""));
        for(Domein.ProjectPackage.ProjectOpgave p : colProjectOpgaven)
        {
            projectOpgaven.add(new SelectItem(p.getOpgaveId(),p.getOpgaveTitel(),""));
        }
        return projectOpgaven;
    }
    public void ddlProjectOpgaveChanged(ValueChangeEvent event)
    {
       setSelectedProjectOpgaveID(Integer.parseInt(event.getNewValue().toString()));
       FacesContext.getCurrentInstance().renderResponse();
    }    
}
