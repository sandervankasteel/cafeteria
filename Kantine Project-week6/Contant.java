
/**
 * Write a description of class Contant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Contant extends Betaalwijze
{
    private Persoon owner;
    
    public void setOwner(Persoon persoon) {
        this.owner = persoon;
    }
    public Persoon getOwner(){
        return this.owner;
    }
    public void betaal(double tebetalen) throws TeWeinigGeldException
    {
        
     if (tebetalen > super.getSaldo()) {
            throw new TeWeinigGeldException(getOwner().getVoorNaam() + " " + getOwner().getAchterNaam());  
        } else {
         double oudSaldo = super.getSaldo() ;  
         double nieuwSaldo = oudSaldo =- tebetalen;   
         super.setSaldo(nieuwSaldo);
        }
        
    }
}
