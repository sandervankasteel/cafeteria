
/**
 * Write a description of class Contant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Contant extends Betaalwijze
{
    public boolean betaal(double tebetalen) {
        
     if (tebetalen >= super.getSaldo()) {
         return false;    
        } else {
         double oudSaldo = super.getSaldo() ;  
         double nieuwSaldo = oudSaldo =- tebetalen;   
         super.setSaldo(nieuwSaldo);
         return true;
        }
        
    }
}
