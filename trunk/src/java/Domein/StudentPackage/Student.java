package Domein.StudentPackage;
public class Student
{
	private int studentNr;

    private String naam;

    private String voornaam;

    private String email;

    private String paswoord;

    private String studiejaar;

    private Boolean geindividualiseerdStudietraject;

    public Student(int studentnr, String naam, String voornaam, String email, String paswoord, String studiejaar, Boolean geindividualiseerdstudietraject)
    {
        setStudentNr(studentnr);
        setNaam(naam);
        setVoornaam(voornaam);
        setEmail(email);
        setPaswoord(paswoord);
        setStudiejaar(studiejaar);
        setGeindividualiseerdStudietraject(geindividualiseerdstudietraject);
    }

    public Student()
    {
        
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getGeindividualiseerdStudietraject() {
		return geindividualiseerdStudietraject;
	}

	public void setGeindividualiseerdStudietraject(
			Boolean geindividualiseerdStudietraject) {
		this.geindividualiseerdStudietraject = geindividualiseerdStudietraject;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}

	public int getStudentNr() {
		return studentNr;
	}

	public void setStudentNr(int studentNr) {
		this.studentNr = studentNr;
	}

	public String getStudiejaar() {
		return studiejaar;
	}

	public void setStudiejaar(String studiejaar) {
		this.studiejaar = studiejaar;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String toString()
	{
		return this.getStudentNr()+" "+this.getNaam();
	}
}
