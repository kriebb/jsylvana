/*
 * ProjectOpgave.java
 *
 * Created on 28 november 2006, 14:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean;

import Domein.*;
import Domein.ProjectPackage.*;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Kristof
 */
public class ProjectOpgave {
    
    /** Creates a new instance of ProjectOpgave */
    private int selectedProjectID=-1;
    private List<SelectItem> projecten; 
    public ProjectOpgave() 
    {        
        projecten = new ArrayList<SelectItem>();
        Collection<Project> colProjecten = DomeinController.getInstance().getProjectBeheerder().GetProjecten();
        getProjecten().add(new SelectItem(getSelectedProjectID(), "--Selecteer een project--",""));
        for(Project p : colProjecten)
        {
            getProjecten().add(new SelectItem(p.getProjectId(),p.getProjectTitel(),""));
        }

    }

    /**
     *Enkel een getter, om het op te halen uit de projectlijst;
     */

    public String vulSelectedItemsProjecten()
    {        
        return "success";
    }
    public List<SelectItem> getProjecten() {
        return projecten;
    }    

    public int getSelectedProjectID() {
        return selectedProjectID;
    }

    public void setSelectedProjectID(int selectedProjectID) {
        this.selectedProjectID = selectedProjectID;
    }
 
    
    
    
}
