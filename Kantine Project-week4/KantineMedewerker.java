public class KantineMedewerker extends Persoon
{
    private boolean kassaToegang;
    
    public KantineMedewerker(boolean kassaToegang, int bsn, String voornaam, String achternaam, int jaar, int maand, int dag, char geslacht) {
        super(bsn, dag, maand, jaar, voornaam, achternaam, geslacht);
        this.kassaToegang = kassaToegang;
    }
    
    public KantineMedewerker() {
        super(7854, 24, 05, 1670, "Marie", "Jansen", 'V');
        this.kassaToegang = false;
        
    }
    

    public void setKassaToegang(boolean kassaToegang)
    {
        this.kassaToegang = kassaToegang;
    }
    
    /**
     * Accessor voor kassa toegang
     * @return kassa toegang
     */
    public boolean getKassaToegang()
    {
        return kassaToegang;
    }
    
    /**
     * Drukt kantinemedewerker specifieke informatie af
     */
    @Override
    public void drukAf()
    {
        System.out.println("Kassa toegang:  "+ getKassaToegang());
    }
}