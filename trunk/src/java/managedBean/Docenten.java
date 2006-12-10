/*
 * ProjectOpgave.java
 *
 * Created on 28 november 2006, 14:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean;

import Domein.DocentPackage.Docent;
import Domein.DocentPackage.DocentInDocentTeam;
import Domein.DocentPackage.DocentTeam;
import Domein.DomeinController;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import managedBean.userControl.DdlProjectOpgave;

/**
 *
 * @author Kristof
 */
public class Docenten {
    
    /**members*/
    private static int DEFAULT = -1;
    private DdlProjectOpgave ddlProjectOpgave;
    private List<SelectItem> docentTeams;
    private int selectDocentTeamID;
    private List<SelectItem> docenten;
    private String selectDocentID;
    private List<DocentInDocentTeam> projectLuikenDocenten;
    private int selectLuikID;
            
    /**constructor*/
    public Docenten() 
    {        
        ddlProjectOpgave = new DdlProjectOpgave();
    }

    /**getters en setters*/
    public DdlProjectOpgave getDdlProjectOpgave() {
        return ddlProjectOpgave;
    }

    public int getSelectDocentTeamID() {
        return selectDocentTeamID;
    }

    public void setSelectDocentTeamID(int selectDocentTeamID) {
        this.selectDocentTeamID = selectDocentTeamID;
    }
    
    public String getSelectDocentID() {
        return selectDocentID;
    }

    public void setSelectDocentID(String selectDocentID) {
        this.selectDocentID = selectDocentID;
    }
    
    public int getSelectLuikID() {
        return selectLuikID;
    }

    public void setSelectLuikID(int selectLuikID) {
        this.selectLuikID = selectLuikID;
    }
    
    public List<SelectItem> getDocentTeams()
    {
        docentTeams=new ArrayList<SelectItem>();
        try
        {
            List<Domein.DocentPackage.DocentTeam> docenten = new ArrayList<Domein.DocentPackage.DocentTeam>(DomeinController.getInstance().getDocentBeheerder().GetDocentTeam_ValueCollection(ddlProjectOpgave.getSelectedProjectOpgaveID()));
            docentTeams.add(new SelectItem(DEFAULT, "--Selecteer een docentteam--",""));
            for(Domein.DocentPackage.DocentTeam dt :docenten)
            {
                docentTeams.add(new SelectItem(dt.getDocentTeamId(),dt.getDocentTeamId()+"",""));
            }
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
        }
        return docentTeams;
    }
    
    public List<SelectItem> getDocenten()
    {
        docenten = new ArrayList<SelectItem>();
        try {
            List<Docent> docentenList = new ArrayList<Docent>(DomeinController.getInstance().getDocentBeheerder().GetDocentenValues());
            docenten.add(new SelectItem(DEFAULT, "--Selecteer een docent--",""));
            for(Docent d : docentenList)
            {
                docenten.add(new SelectItem(d.getDocentId(),d.getNaamVoornaam(),""));
            }
        } 
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
        }
        return docenten;
    }
    
    public List<DocentInDocentTeam> getProjectluikenEnDocenten()
    {
        try
        {
            projectLuikenDocenten = new ArrayList<DocentInDocentTeam>(DomeinController.getInstance().getDocentBeheerder().GetLuikenEnDocenten(getSelectDocentTeamID(),getDdlProjectOpgave().getSelectedProjectID(),getDdlProjectOpgave().getSelectedProjectOpgaveID()));
        }
        catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage("fout",new FacesMessage(ex.getMessage()));
        }
        return projectLuikenDocenten;
    }
    
    /**actionevents*/
    public void ddlDocentTeamsChanged(ValueChangeEvent event)
    {
        setSelectDocentTeamID(Integer.parseInt(event.getNewValue().toString()));
        FacesContext.getCurrentInstance().renderResponse();
    }
    
    public void ddlDocentenChanged(ValueChangeEvent event)
    {
        setSelectDocentID(event.getNewValue().toString());
        FacesContext.getCurrentInstance().renderResponse();
    }
    public void nieuwDocentTeam(ActionEvent e)
    {
        DocentTeam dt = new DocentTeam(0,ddlProjectOpgave.getSelectedProjectOpgaveID(),getDdlProjectOpgave().getSelectedProjectID());
        DomeinController.getInstance().getDocentBeheerder().MakeDocentTeam(dt);
    }
    public void deleteDocentTeam(ActionEvent e)
    {   
        Map<Integer,DocentTeam> docentenMap = DomeinController.getInstance().getDocentBeheerder().GetDocentTeam(ddlProjectOpgave.getSelectedProjectOpgaveID());
        DomeinController.getInstance().getDocentBeheerder().DeleteDocentTeam(docentenMap.get(getSelectDocentTeamID()));
    }
    public void updateDocentTeam(ActionEvent e)
    {
        
    }
}
