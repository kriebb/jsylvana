/*
 * ProjectOpgave.java
 *
 * Created on 28 november 2006, 14:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean;

import Domein.DomeinController;
import Domein.ProjectPackage.Project;
import Domein.ProjectPackage.ProjectOpgave;
import Utils.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import managedBean.userControl.DdlProject;

/**
 *
 * @author Kristof
 */
public class ViewProjectOpgave
{
    
    /** Creates a new instance of ProjectOpgave */
    
    private DdlProject ddlProject;
    private boolean drilldownTableVisible=false;
    private int selectRow = -1;
    
    public ViewProjectOpgave()
    {
        ddlProject = new DdlProject();
        this.editMode=false;
        this.insertMode=true;
        this.drilldownTableVisible=false;
        this.projectVeranderd=false;
        this.vorigProject=ddlProject.getSelectedProjectID();
    }
    
    
    private boolean hardDeleteEnabled = false;
    
    public void hardDelete(ActionEvent e)
    {
        boolean result =false;
        setDrilldownTableVisible(false);
        try
        {
            result = DomeinController.getInstance().getProjectBeheerder().HardDeleteProjectOpgave(DomeinController.getInstance().getProjectBeheerder().GetOpgaveByProjectID_OpgaveID(this.getDdlProject().getSelectedProjectID(),this.selectRow));
        }
        catch(ApplicationException ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
            result = false;
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
            result = false;
        }
        finally
        {
            if(!result)
            {
                FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("Mislukt. U kunt dit projectopgave momenteel niet verwijderen. Probeer het later opnieuw"));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("Gelukt!"));
            }
            this.hardDeleteEnabled=false;
            this.insertMode=true;
            FacesContext.getCurrentInstance().renderResponse();
        }    
    }
    
    public boolean isHardDeleteEnabled()
    {
        return hardDeleteEnabled;
    }
    public DdlProject getDdlProject()
    {
        return ddlProject;
    }
    public Object[] getProjectOpgaveBySelectedProjectID()
    {
        try
        {
        List<ProjectOpgave> col = new ArrayList<ProjectOpgave>(DomeinController.getInstance().getProjectBeheerder().GetOpgavenByProjectId(ddlProject.getSelectedProjectID())) ;
    Collections.sort(col,new ProjectOpgaveComparator());
    return col.toArray();
        }        
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
        }
        return new Object[1];
    }   

    private class ProjectOpgaveComparator implements Comparator {
    public int compare(Object obj1, Object obj2)
    {        
      return (((ProjectOpgave)obj1).getOpgaveTitel().compareToIgnoreCase(((ProjectOpgave)obj2).getOpgaveTitel()));
    }
        }

    
    public boolean isDrilldownTableVisible()
    {
        return drilldownTableVisible;
    }
    
    
    public void editCurrentRow(ActionEvent e)
    {
        selectRow = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectRow"));
        vorigProject = ddlProject.getSelectedProjectID();
        setDrilldownTableVisible(true);
        this.hardDeleteEnabled=false;
        setEditMode(true);
        setInsertMode(false);        
        this.updatedOpgave=null;
        FacesContext.getCurrentInstance().renderResponse();
    }
    public void cancel(ActionEvent e)
    {
        selectRow = -1;
        setDrilldownTableVisible(false);
        setEditMode(false);
        setInsertMode(true);
        this.hardDeleteEnabled=false;
        this.updatedOpgave=null;
        this.createdOpgave=null;
        FacesContext.getCurrentInstance().renderResponse();
        
    }
    public void checkEdit(ActionEvent e)
    {
        boolean result =false;
        ProjectOpgave editedProjectOpgave = this.selectedProjectOpgave;
        try
        {
            result = DomeinController.getInstance().getProjectBeheerder().UpdateProjectOpgave(editedProjectOpgave);
        }
        catch(ApplicationException ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
            result=false;
        }
        
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
            result = false;
        }
        finally
        {
            if(!result)
            {
                FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("Mislukt Updaten"));
                this.setDrilldownTableVisible(true);
                editMode=true;
                insertMode=false;
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("Gelukt! Updaten"));
                this.setDrilldownTableVisible(false);
                editMode=false;
                insertMode=true;
                this.updatedOpgave=null;
                selectRow=-1;
            }
            this.hardDeleteEnabled=false;
            
            FacesContext.getCurrentInstance().renderResponse();
        }
        
    }
    private boolean editMode =false;
    private boolean insertMode=true;
    public boolean isEditMode()
    {
        return editMode;
    }
    public boolean isInsertMode()
    {
        return insertMode;
    }
    public void checkInsert(ActionEvent e)
    {
        boolean result =false;
        ProjectOpgave newProjectOpgave = this.selectedProjectOpgave;
        newProjectOpgave.setProjectId(this.ddlProject.getSelectedProjectID());
        try
        {
            result = DomeinController.getInstance().getProjectBeheerder().MakeProjectOpgave(newProjectOpgave);
        }
        catch(ApplicationException ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
            result=false;
        }
        
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
            result = false;
        }
        finally
        {
            if(!result)
            {
                FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("Mislukt Inserten"));
                this.setDrilldownTableVisible(true);
                editMode=false;
                insertMode=true;            
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("Gelukt! Inserten"));
                this.setDrilldownTableVisible(false);
                editMode=false;
                insertMode=true;
                this.createdOpgave=null;
            }
            this.hardDeleteEnabled=false;
            FacesContext.getCurrentInstance().renderResponse();
        }
        
    }
    public void checkDeleteCurrentRow(ActionEvent e)
    {
        boolean result =false;
        selectRow = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deleteRow"));
        setDrilldownTableVisible(false);
        try
        {
            result = DomeinController.getInstance().getProjectBeheerder().DeleteProjectOpgave(DomeinController.getInstance().getProjectBeheerder().GetOpgaveByProjectID_OpgaveID(this.getDdlProject().getSelectedProjectID(),this.selectRow));
        }
        catch(ApplicationException ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
            result = false;
        }
        finally
        {
            if(!result)
            {
                FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("Mislukt. U kunt altijd eens de 'Hard Delete' proberen"));
                this.hardDeleteEnabled=true;
                this.insertMode=false;
            }
            else
            {
                this.hardDeleteEnabled=false;
                this.insertMode=true;
                FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage("Gelukt!"));
            }
            FacesContext.getCurrentInstance().renderResponse();

        }
        
    }
    
    
    public int getSelectRow()
    {
        return selectRow;
    }
    
    /**
     *Het geselecteerde project wordt hierin gestopt
     **/
    private ProjectOpgave selectedProjectOpgave;
    /**
     *Twee tussenvariabelen, zodat mijn gewijzigde waarde wordt afgeschermd, omdat anders via SetProjectOpgave, alles weer wordt bij het oude.
     **/
    private ProjectOpgave createdOpgave;
    private ProjectOpgave updatedOpgave;
    /**
     * Omdat de setters en getters blijkbaar twee maal worden opgeroepen, heb ik deze methode nodig, om m'n 
     * gewijzigde projectopgaven af te schermen, zodat niet hun vorige waarde wordt overschreven doordat het tweemaal
     * wordt opgeroepen uit de databank.
     **/
    private ProjectOpgave createNew()
    {
        if(createdOpgave==null)
        {
            createdOpgave=new ProjectOpgave();
        }
        return createdOpgave;
    }
    /**
     * Omdat de setters en getters blijkbaar twee maal worden opgeroepen, heb ik deze methode nodig, om m'n 
     * gewijzigde projectopgaven af te schermen, zodat niet hun vorige waarde wordt overschreven doordat het tweemaal
     * wordt opgeroepen uit de databank.
     **/
   
    private ProjectOpgave getOpgaveClone(ProjectOpgave op)
    {
        ProjectOpgave returnwaarde = new ProjectOpgave();
        returnwaarde.setProject(op.getProject());
        returnwaarde.setOpgaveId(op.getOpgaveId());
        returnwaarde.setOpgaveTitel(op.getOpgaveTitel());
        returnwaarde.setKorteOmschrijving(op.getKorteOmschrijving());
        returnwaarde.setAantalGroepen(op.getAantalGroepen());
        returnwaarde.setAantalStudentenPerGroep(op.getAantalStudentenPerGroep());
        return returnwaarde;
    }
    private ProjectOpgave updatedNew()
    {
        if(updatedOpgave==null)
        {
                //Dit is nodig, om te vermijden dat de pointers de heletijd naar elkaar verwijzen. Dit loopt anders mis bij de 
                //validatie.
                updatedOpgave = getOpgaveClone(DomeinController.getInstance().getProjectBeheerder().GetOpgaveByProjectID_OpgaveID(ddlProject.getSelectedProjectID(),this.selectRow));            
        }
        return updatedOpgave;
    }
    
    private boolean projectVeranderd=false;
    private int vorigProject=-1;
    public boolean isProjectVeranderd()
    {
        if(ddlProject.getSelectedProjectID()==this.vorigProject)
        {
            projectVeranderd=false;
        }
        else
        {
            vorigProject = ddlProject.getSelectedProjectID();
            projectVeranderd=true;
            selectRow=-1;
        }
        return this.projectVeranderd;
        
    }
    
    public void setSelectedProjectOpgave(ProjectOpgave po)
    {
        this.selectedProjectOpgave=po;
    }
    public ProjectOpgave getSelectedProjectOpgave()
    {
        if(this.editMode)
        {
            this.selectedProjectOpgave=updatedNew();
        }
        else
        {
            this.selectRow=-1;
            if(this.insertMode)
            {
                this.selectedProjectOpgave=createNew();
            }
            else
            {
                this.selectedProjectOpgave=null;
            }
        }
        return this.selectedProjectOpgave;
    }
    
    public void setDrilldownTableVisible(boolean editable)
    {
        this.drilldownTableVisible = editable;
    }
    
    private void setInsertMode(boolean b)
    {
        insertMode=b;
    }
    private void setEditMode(boolean b)
    {
        editMode=b;
    }
    public void toggleInsertMode(ActionEvent e)
    {
        this.setDrilldownTableVisible(true);
        this.setEditMode(false);
        this.updatedOpgave=null;
        this.selectRow=-1;
        this.vorigProject=ddlProject.getSelectedProjectID();
        this.setInsertMode(true);
        FacesContext.getCurrentInstance().renderResponse();
    }
    private boolean beperkteEditModus  = false;
    public boolean isBeperkteEditModus()
    {
        if(ddlProject.getSelectedProjectID()!=-1)
        {
            Project p = DomeinController.getInstance().getProjectBeheerder().GetProjectenDictionary().get(ddlProject.getSelectedProjectID());
            beperkteEditModus = p.inschrijvingBegonnen() && p.inschrijvingGedaan();
        }
        else
        {
            this.beperkteEditModus=false;
        }
            return this.beperkteEditModus;
    }
    
    
    
    
    
            
}
