
/**
 * Write a description of class Pinpas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pinpas extends Betaalwijze
{
    private double kredietlimiet;
    private Persoon owner;
    
    public void setKreditLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }
    public void setOwner(Persoon persoon) {
        this.owner = persoon;
    }
    public Persoon getOwner(){
        return this.owner;
    }
    /** methode om betalingen af te handelen
     * @param tebetalen
     * @return Boolean om te kijken of er voldoende saldo is
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException{
        if (tebetalen <= super.getSaldo()) {
            kredietlimiet =- tebetalen;
            
        } else {
            throw new TeWeinigGeldException(getOwner().getVoorNaam() + " " + getOwner().getAchterNaam());
        }
    }

}
