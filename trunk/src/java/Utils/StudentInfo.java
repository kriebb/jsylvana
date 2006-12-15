import java.util.ArrayList;
import java.util.List;
public class StudentInfo
{
	public StudentInfo()
	{
        setProjecten(new ArrayList<Integer>());
        setTraject(false);
	}

    private int studentnr;
    private String naam;
    private String voornaam;

    private String email;

    private String studiejaar;

    private String paswoord;


    private boolean traject;


    private List<Integer> projecten;

    public int getStudentnr()
    {
        return studentnr;
    }

    public void setStudentnr(int studentnr)
    {
        this.studentnr = studentnr;
    }

    public String getNaam()
    {
        return naam;
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    public String getVoornaam()
    {
        return voornaam;
    }

    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStudiejaar()
    {
        return studiejaar;
    }

    public void setStudiejaar(String studiejaar)
    {
        this.studiejaar = studiejaar;
    }

    public String getPaswoord()
    {
        return paswoord;
    }

    public void setPaswoord(String paswoord)
    {
        this.paswoord = paswoord;
    }

    public boolean isTraject()
    {
        return traject;
    }

    public void setTraject(boolean traject)
    {
        this.traject = traject;
    }

    public List<Integer> getProjecten()
    {
        return projecten;
    }

    public void setProjecten(List<Integer> projecten)
    {        
        this.projecten = projecten;
    }

}
