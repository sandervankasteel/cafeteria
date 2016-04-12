
/**
 * Abstract class Betaalwijze - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Betaalwijze
{
    protected double saldo;

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public abstract boolean betaal (double tebetalen);
}
