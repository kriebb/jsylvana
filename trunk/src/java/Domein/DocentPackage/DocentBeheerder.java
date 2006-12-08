
package Domein.DocentPackage;

import Domein.DomeinController;
import Persistentie.SqlDocentProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public class DocentBeheerder {
    public DocentBeheerder()
    {
        
    }

    public Map<Integer, DocentTeam> GetDocentTeam(int opgaveid)
    {
        Map<Integer, DocentTeam> docentteam_opgaveid;// = (Dictionary<int,DocentTeam>)HttpContext.Current.Cache["docentteam_opgaveid=" + opgaveid];
        //if (docentteam_opgaveid == null)
        {
            docentteam_opgaveid = SqlDocentProvider.getInstance().GetDocentTeamsByProjectOpgave(opgaveid);
            //HttpContext.Current.Cache["docentteam_opgaveid=" + opgaveid]=docentteam_opgaveid;
        }
        return docentteam_opgaveid;
    }

    public Collection<DocentTeam> GetDocentTeam_ValueCollection(int opgaveid)
    {
        return this.GetDocentTeam(opgaveid).values();
    }
    public void MakeDocentTeam(DocentTeam dt)
    {
        //Helpers.PurgeCache("docentteam_opgaveid="+dt.ProjectOpgave.OpgaveId);
        SqlDocentProvider.getInstance().InsertDocentTeam(dt);
    }
    public void DeleteDocentTeam(DocentTeam dt)
    {
        //Helpers.PurgeCache("docentteam_opgaveid=" + dt.ProjectOpgave.OpgaveId);
        SqlDocentProvider.getInstance().DeleteDocentTeam(dt.getDocentTeamId());
    }

    public Boolean ControleerLogin(String email, String paswoord)
    {
        return SqlDocentProvider.getInstance().controleerLogin(email, paswoord);
    }
    public Map<String,Docent> GetDocentenDictionary()
    {
        Map<String,Docent> docenten;// = (Dictionary<string,Docent>)HttpContext.Current.Cache["getAllDocenten"];
        //if (docenten == null)
        {
            docenten = SqlDocentProvider.getInstance().GetDocenten();
           // HttpContext.Current.Cache["getAllDocenten"] = docenten;
        }
        return docenten;
    }
    public Collection<Docent> GetDocentenValues()
    {
        return this.GetDocentenDictionary().values();
    }
    public void UpdateDocentInDocentTeam(DocentTeam docentinteam)
    {
        //TODO:Cachen?
        //Map.Entry<Integer, String> tst = docentinteam.getDocentMetProjectLuikInDitDocentTeam().entrySet();            
        //while (tst.MoveNext())
        for (Map.Entry<Integer, String> tst : docentinteam.getDocentMetProjectLuikInDitDocentTeam().entrySet())
        {
            SqlDocentProvider.getInstance().UpdateDocentInDocentTeam(docentinteam.getDocentTeamId(),tst.getKey(),tst.getValue());       
        }
        
    }
    public Map<Integer, String> GetAlleDocentenInEenDocentTeam_ByDocentTeamID(int docentTeamID, int projectID, int opgaveID)
    {
        Map<Integer, String> alleDocentenVanEenTeam;// = (Dictionary<int, string>)HttpContext.Current.Cache["alleDocentenVanEenTeam_docentTeamID=" + docentTeamID];
        //if (alleDocentenVanEenTeam == null)
        {
            alleDocentenVanEenTeam = SqlDocentProvider.getInstance().GetDocentInDocentTeam_ByDocentTeamIDEnProjectID(docentTeamID, projectID, opgaveID);
            //HttpContext.Current.Cache["alleDocentenVanEenTeam_docentTeamID=" + docentTeamID] = alleDocentenVanEenTeam;
        }
        return alleDocentenVanEenTeam;
    }


    public List<DocentInDocentTeam> GetLuikenEnDocenten(int docentTeamID, int projectID, int opgaveID)
    {
        //Map<Integer, String>.Enumerator advet = this.GetAlleDocentenInEenDocentTeam_ByDocentTeamID(docentTeamID, projectID, opgaveID).GetEnumerator();
        List<DocentInDocentTeam> lijst = new ArrayList<DocentInDocentTeam>();

        //while (advet.MoveNext())
        
        for (Map.Entry<Integer, String> advet : GetAlleDocentenInEenDocentTeam_ByDocentTeamID(docentTeamID, projectID, opgaveID).entrySet())
        {
            if (advet.getKey() != null)
            {
                if (advet.getValue() == null)
                {
                    Docent d = new Docent();
                    d.setDocentId("-1");
                    d.setNaam("Geen Docent Toegewezen");
                    lijst.add(new DocentInDocentTeam(DomeinController.getInstance().getProjectBeheerder().GetProjectLuikByProjectID(projectID).get(advet.getKey()), d));

                }
                else
                {
                    lijst.add(new DocentInDocentTeam(DomeinController.getInstance().getProjectBeheerder().GetProjectLuikByProjectID(projectID).get(advet.getKey()),
                    this.GetDocentenDictionary().get(advet.getValue())));
                }
            }
        }
        return lijst;
    }
    
}
