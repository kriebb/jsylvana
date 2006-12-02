package Domein.ProjectPackage;

import java.util.Date;

public class Project
{
	private int projectId;

    public int getProjectId()
    {
        return projectId;
    }
    public void setProjectId(int value)
    {
        projectId = value;
    }
    private String projectTitel;

    public String getProjectTitel()
    {
        return projectTitel;
    }
    public void setProjectTitel(String value)
    {
        projectTitel = value;
    }
    private int studiejaar;

    public int getStudiejaar()
    {
        return studiejaar;
    }
    public void setStudiejaar(int value)
    {
        studiejaar = value;
    }
    
    private Date inschrijvingVan;

    public Date getInschrijvingVan()
    {
        return inschrijvingVan;
    }
    public void setInschrijvingVan(Date value)
    {
        inschrijvingVan = value;
    }
    private Date inschrijvingTot;

    public Date getInschrijvingTot()
    {
        return inschrijvingTot;
    }
    public void setInschrijvingTot(Date value)
    {
        inschrijvingTot = value;
    }



//KRS
    public Boolean inschrijvingBegonnen()
    {
        if (getInschrijvingVan() == null)
        {
            return false;
        }
        else
        {
            return (new Date().after(getInschrijvingVan()));
        }
    }
    public Boolean inschrijvingGedaan()
    {
        if (getInschrijvingTot() == null)
        {
            return true;
        }
        else
        {
            return (new Date().after(getInschrijvingTot()));
        }
    }

//KRS

    public Project(int projectid, String projecttitel, int studiejaar, Date inschrijvingvan, Date inschrijvingtot)
    {
        setProjectId(projectid);
        setProjectTitel(projecttitel);
        setStudiejaar(studiejaar);
        setInschrijvingVan(inschrijvingvan);
        setInschrijvingTot(inschrijvingtot);
    }

    public Project()
    {

    }
    public Project(int projectid)
    {
        setProjectId(projectid);
    }
    public String toString()
    {
        return getProjectTitel();
    }
}
