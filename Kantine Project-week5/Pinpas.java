
/**
 * Write a description of class Pinpas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pinpas extends Betaalwijze
{
    private double kredietlimiet;

    public void setKreditLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /** methode om betalingen af te handelen
     * @param tebetalen
     * @return Boolean om te kijken of er voldoende saldo is
     */
    public boolean betaal(double tebetalen) {
        if (tebetalen <= kredietlimiet) {
            kredietlimiet =- tebetalen;
            return true;
        } else {
        return false;
        }
    }

}
