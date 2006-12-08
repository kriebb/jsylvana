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
import Domein.ProjectPackage.ProjectOpgave;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.application.FacesMessage;
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
        try
        {

        List<Domein.ProjectPackage.ProjectOpgave> colProjectOpgaven = new ArrayList<ProjectOpgave>(DomeinController.getInstance().getProjectBeheerder().GetOpgavenByProjectId(super.getSelectedProjectID()));        
        Collections.sort(colProjectOpgaven,new ProjectOpgaveComparator());
        projectOpgaven.add(new SelectItem(DEFAULT_NOT_USED, "--Selecteer een projectopgave--",""));
        for(Domein.ProjectPackage.ProjectOpgave p : colProjectOpgaven)
        {
            projectOpgaven.add(new SelectItem(p.getOpgaveId(),p.getOpgaveTitel(),""));
        }
        
        }        
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
        }       
        return projectOpgaven;
    }
    public void ddlProjectOpgaveChanged(ValueChangeEvent event)
    {
       setSelectedProjectOpgaveID(Integer.parseInt(event.getNewValue().toString()));
       FacesContext.getCurrentInstance().renderResponse();
    }
    private class ProjectOpgaveComparator implements Comparator {
    public int compare(Object obj1, Object obj2)
    {        
      return (((ProjectOpgave)obj1).getOpgaveTitel().compareToIgnoreCase(((ProjectOpgave)obj2).getOpgaveTitel()));
    }
        }
    

}

