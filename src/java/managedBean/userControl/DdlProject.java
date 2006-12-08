/*
 * DdlProject.java
 *
 * Created on 28 november 2006, 20:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean.userControl;

import Domein.DomeinController;
import Domein.ProjectPackage.Project;
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


public class DdlProject {
   
   private static final int DEFAULT_NOT_CHOOSEN=-1;
   private int selectedProjectID=DEFAULT_NOT_CHOOSEN;
   private List<SelectItem> projecten;    
    /** Creates a new instance of DdlProject */
    public DdlProject() 
    {
        try
        {
        projecten = new ArrayList<SelectItem>();
        List<Project> colProjecten = new ArrayList<Project>(DomeinController.getInstance().getProjectBeheerder().GetProjecten());
        Collections.sort(colProjecten,new ProjectComparator());
        getProjecten().add(new SelectItem(DEFAULT_NOT_CHOOSEN, "--Selecteer een project--",""));
        for(Project p : colProjecten)
        {
            getProjecten().add(new SelectItem(p.getProjectId(),p.getProjectTitel(),""));
        }
        }        
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
        }
    }
    public void ddlProjectChanged(ValueChangeEvent event)
    {
       selectedProjectID = Integer.parseInt(event.getNewValue().toString());
       FacesContext.getCurrentInstance().renderResponse();       
    }
       public List<SelectItem> getProjecten() {
        return projecten;
    }    

    public int getSelectedProjectID() 
    {
        return selectedProjectID;
    }

    public void setSelectedProjectID(int selectedProjectID) {
        this.selectedProjectID = selectedProjectID;
    }
    private class ProjectComparator implements Comparator {
    public int compare(Object obj1, Object obj2)
    {        
      return (((Project)obj1).getProjectTitel().compareToIgnoreCase(((Project)obj2).getProjectTitel()));
    }
    }


}



    
