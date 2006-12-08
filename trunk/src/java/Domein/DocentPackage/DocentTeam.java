package Domein.DocentPackage;
import java.util.Map;

import Domein.DomeinController;
import Domein.ProjectPackage.ProjectOpgave;

/**
 *
 * @author Frederik
 */
public class DocentTeam {
    
    private ProjectOpgave projectopgave;
    private int docentTeamId;

    public DocentTeam(int docentteamid, ProjectOpgave po)
    {
        setDocentTeamId(docentteamid);
        setProjectOpgave(po);
        setDocentMetProjectLuikInDitDocentTeam(DomeinController.getInstance().getDocentBeheerder().GetAlleDocentenInEenDocentTeam_ByDocentTeamID(docentTeamId, po.getProjectID(), po.getOpgaveId()));

        setDocentTeamNaam("Team: " + docentteamid);
    }

    private String teamnaam;

    public String getDocentTeamNaam()
    {
        return teamnaam;
    }
    public void setDocentTeamNaam(String value)
    {
        teamnaam = value;
    }


    public DocentTeam(int docentTeamID,int projectOpgaveID,int projectID)
    {
        this(docentTeamID,
        DomeinController.getInstance().getProjectBeheerder().GetOpgaveByProjectID_OpgaveID(projectID,projectOpgaveID));
    }
    public DocentTeam()
    {
    }
    //int = key van projectluik, string=key van docent
    private Map<Integer, String> docentMetProjectLuikInDitDocentTeam;

    public Map<Integer,String> getDocentMetProjectLuikInDitDocentTeam()
    {
        return docentMetProjectLuikInDitDocentTeam;
    }
    public void setDocentMetProjectLuikInDitDocentTeam(Map<Integer,String> value)
    {
        docentMetProjectLuikInDitDocentTeam = value;
    }


    public int getDocentTeamId()
    {
        return docentTeamId;
    }
    public void setDocentTeamId(int value)
    {
        docentTeamId = value;
    }

    public ProjectOpgave getProjectOpgave()
    {
        return projectopgave;
    }
    public void setProjectOpgave(ProjectOpgave value)
    {
    	projectopgave = value;
    }
    
}
