package Domein.ProjectPackage;

import Domein.DomeinController;

public class ProjectLuik
{
	private int luikId;

    private String luikTitel;

    private int percentage;

    private Project project;
    
    public String toString()
    {
    	return luikTitel;
    }

    public ProjectLuik(int luikid, String luiktitel, int percentage, Project project)
    {
        setLuikId(luikid);
        setLuikTitel(luiktitel);
        setPercentage(percentage);
        setProject(project);
    }
    
    public ProjectLuik(int luikid, String luiktitel, int percentage, int projectID)
    {this(
        luikid,
        luiktitel,
        percentage,
        DomeinController.getInstance().getProjectBeheerder().GetProjectenDictionary().get(projectID));
    }
    public ProjectLuik()
    {
    }

	public int getLuikId() {
		return luikId;
	}

	public void setLuikId(int luikId) {
		this.luikId = luikId;
	}

	public String getLuikTitel() {
		return luikTitel;
	}

	public void setLuikTitel(String luikTitel) {
		this.luikTitel = luikTitel;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
