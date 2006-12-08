
package Domein.DocentPackage;

/**
 *
 * @author Frederik
 */
public class Docent {
    private String docentId;

    public String getDocentId()
    {
        return docentId;
    }
    public void setDocentId(String value)
    {
    	docentId = value;
    }
    
    private String naam;

    public String getNaam()
    {
    	return naam;
    }
    public void setNaam(String value)
    {
        naam = value;
    }
    
    private String voornaam;

    public String getVoornaam()
    {
        return voornaam;
    }
    public void setVoornaam(String value)
    {
        voornaam = value;
    }
    
    private String email;

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String value)
    {
        email = value;
    }
    
    private Boolean admin;

    public Boolean getAdmin()
    {
        return admin;
    }
    public void setAdmin(Boolean value)
    {
        admin = value;
    }
    
    private String paswoord;

    public String getPaswoord()
    {
        return paswoord;
    }
    public void setPaswoord(String value)
    {
        paswoord = value;
    }
    
    public String getNaamVoornaam()
    {
        return naam + " " + voornaam;
    }
    public Docent(String docentid, String naam, String voornaam, String email, String paswoord, Boolean admin)
    {
        setDocentId(docentid);
        setNaam(naam);
        setVoornaam(voornaam);
        setEmail(email);
        setPaswoord(paswoord);
        setAdmin(admin);
    }

    public Docent()
    {
    }
}
