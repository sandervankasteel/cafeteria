public class Docent extends Persoon implements KortingskaartHouder
{
    private String afkorting;
    private String afdeling;
    private KortingskaartHouder kortingscard; 
    
    /**
     * Constructor
     */
    public Docent(String afkorting, String afdeling, int bsn, String voornaam, String achternaam, int jaar, int maand, int dag, char geslacht)
    {
        super(bsn, dag, maand, jaar, voornaam, achternaam, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
        
    }
    
    public Docent() {
       super(7845, 5, 8, 1980, "Henk", "Van de Slachthuis", 'm');
       afkorting = "HESL";
       afdeling = "NATUURKUNDE";
    }
    
    public boolean heeftMaximum()
    {
        return true;
    }
    
    public double geefMaximum()
    {
        return 1.0;
    }
    
    public double geefKortingsPercentage()
    {
        return 0.25;
    }
    
    public boolean getKortingsKaart()
    {
        return true;
    }
    
    public void setAfkorting(String afkorting)
    {
        this.afkorting = afkorting;
    }
    
    public void setAfdeling(String afdeling)
    {
        this.afdeling = afdeling;
    }
    
    public String getAfkorting()
    {
        return afkorting;
    }
    
    public String getAfdeling()
    {
        return afdeling;
    }
    
    @Override
    public void drukAf()
    {
        System.out.println("Afkorting:     " + getAfkorting());
        System.out.println("Afdeling:      " + getAfdeling());
    }
}