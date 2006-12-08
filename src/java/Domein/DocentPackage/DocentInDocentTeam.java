
package Domein.DocentPackage;

import Domein.ProjectPackage.ProjectLuik;

/**
 *
 * @author Frederik
 */
public class DocentInDocentTeam {
    private DocentTeam dt;

    public DocentTeam getDocentTeam()
    {
        return dt;
    }
    public void setDocentTeam(DocentTeam value)
    {
        dt = value;
    }

    private ProjectLuik pl;

    public ProjectLuik getProjectLuik()
    {
        return pl;
    }
    public void setProjectLuik(ProjectLuik value)
    {
        pl = value;
    }
    public String getProjectLuikNaam()
    {
        return pl.getLuikTitel();
    }
    public int getProjectLuikID()
    {
        return pl.getLuikId();
    }

    private Docent docent;

    public Docent getDocent()
    {
        return docent;
    }
    public void setDocent(Docent value)
    {
        docent=value;
    }
    public String getDocentNaam()
    {
        return docent.getNaamVoornaam();
    }

    public String getDocentID()
    {
        return docent.getDocentId();
    }
	
    
    public DocentInDocentTeam(ProjectLuik pl, Docent docent)
    {
        this.setProjectLuik(pl);
        this.setDocent(docent);
    }
}
