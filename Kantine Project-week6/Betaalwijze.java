
/**
 * Abstract class Betaalwijze - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Betaalwijze
{
    protected double saldo;
    protected Persoon persoon;
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    public abstract Persoon getOwner();
    
    public abstract void setOwner(Persoon persoon);
    
    public double getSaldo(){
        return saldo;
    }
    public Persoon getPersoon(){
        return persoon;
    }
    public abstract void betaal (double tebetalen) throws TeWeinigGeldException;
}
