public class Student extends Persoon
{
    private int studentNr;
    private String studieRichting;
    
    /**
     * Constructor
     */
    public Student(int studentNr, String studieRichting, int bsn, String voornaam, String achternaam, int jaar, int maand, int dag, char geslacht)
    {
        super(bsn, dag, maand, jaar, voornaam, achternaam, geslacht);
        this.studentNr = studentNr;
        this.studieRichting = studieRichting;
    }
    
    public Student() {
        super(765454, 12, 12, 1970, "Sander", "fgjkdf", 'V');
        this.studentNr = 75845;
        this.studieRichting = "ICT";  
    }
    
    public void setStudentNr(int studentNr)
    {
        this.studentNr = studentNr;
    }
    
    public void setStudieRichting(String studieRichting)
    {
        this.studieRichting = studieRichting;
    }
    
    public int getstudentNrr()
    {
        return studentNr;
    }
    
    public String getStudieRichting()
    {
        return studieRichting;
    }
    
    @Override
    public void drukAf()
    {
        System.out.println("Student nummer: "+ getstudentNrr());
        System.out.println("Studierichting:" + getStudieRichting());
    }
}