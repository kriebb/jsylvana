package Domein.ProjectPackage;

import Domein.DomeinController;

public class ProjectOpgave
{
	private int opgaveId;

    public int getOpgaveId()
    {
        return opgaveId;
    }
    public void setOpgaveId(int value)
    {
        opgaveId = value;
    }
    private String opgaveTitel;

    private String korteOmschrijving;

    private int aantalStudentenPerGroep;

    private int aantalGroepen;

    private Project project;

    public int getProjectID()
    {
        return project.getProjectId();
    }
    public void setProjectId(int value)
    {
        project = DomeinController.getInstance().getProjectBeheerder().GetProjectenDictionary().get(value);
    }

    public ProjectOpgave(
        int opgaveid, 
        String opgavetitel, 
        String korteomschrijving, 
        int aantalgroepen, 
        int aantalstudentenpergroep,
        int projectid)
    {
    	this(  
      opgaveid,
      opgavetitel,
      korteomschrijving,
      aantalgroepen,
      aantalstudentenpergroep,
      DomeinController.getInstance().getProjectBeheerder().GetProjectenDictionary().get(projectid));
    }        

    public ProjectOpgave(
     int opgaveid,
     String opgavetitel,
     String korteomschrijving,
     int aantalgroepen,
     int aantalstudentenpergroep,
     Project project)
    {
        setOpgaveId(opgaveid);
        setOpgaveTitel(opgavetitel);
        setKorteOmschrijving(korteomschrijving);
        setAantalGroepen(aantalgroepen);
        setAantalStudentenPerGroep(aantalstudentenpergroep);
        setProject(project);
    }

    public ProjectOpgave()
    {
    	this(
        0,
        "",
        "",
        0,
        0,           
        new Project());
    }
	public int getAantalGroepen() {
		return aantalGroepen;
	}
	public void setAantalGroepen(int aantalGroepen) {
		this.aantalGroepen = aantalGroepen;
	}
	public int getAantalStudentenPerGroep() {
		return aantalStudentenPerGroep;
	}
	public void setAantalStudentenPerGroep(int aantalStudentenPerGroep) {
		this.aantalStudentenPerGroep = aantalStudentenPerGroep;
	}
	public String getKorteOmschrijving() {
		return korteOmschrijving;
	}
	public void setKorteOmschrijving(String korteOmschrijving) {
		this.korteOmschrijving = korteOmschrijving;
	}
	public String getOpgaveTitel() {
		return opgaveTitel;
	}
	public void setOpgaveTitel(String opgaveTitel) {
		this.opgaveTitel = opgaveTitel;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public String toString()
	{
		return this.opgaveTitel + " " +this.getProject().getProjectTitel()+ this.getProjectID();	
	}
}
