import java.util.ArrayList;
import java.util.Iterator;
/**
 * Write a description of class Kassa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kassa
{
    private KassaRij rij;
    private int totaalArtikelen;
    private double totaalMoneys;
    
    public Kassa (KassaRij kassaRij){
        this.rij = kassaRij;
        this.totaalArtikelen = 0;
        this.totaalMoneys = 0;
    }
    /**
     * Rekent een persoon af uit de rij.
     */
    private void afrekenen(Persoon persoon){
        // Get artikelen
        int a = persoon.getAantalArtikelen();
        this.totaalArtikelen += a;
        // Get Prijs
        double p = persoon.getTotaalPrijs();
        this.totaalMoneys += p;
        
    }
    /**
     * Gooit de hele rij aan mensen leeg en handeld hun aankopen af.
     */
    public void iedereenAfrekenen(){
        if(this.rij.erIsEenRij()){ // Overbodig met foreach?
            ArrayList<Persoon> personsInQueue = this.rij.getEntireQueue();
            Iterator<Persoon> i = personsInQueue.iterator();
            while(i.hasNext()){
                Persoon persoon = i.next();
                this.afrekenen(persoon);
                i.remove();
            }
        }
    }
    /**
     * Rekent de hoeveel mensen af die je meegeeft aan eersteXPersonen. De mensen worden vervolgens uit de rij verwijderd.
     * @param eersteXPersonen De hoeveel mensen wiens artikelen moeten worden afgerekend.
     */
    public ArrayList<Persoon> rekenAf(int eersteXPersonen){
        if(this.rij.erIsEenRij()){ // Overbodig met foreach?
            ArrayList<Persoon> personsInQueue = this.rij.getEntireQueue();
            Iterator<Persoon> i = personsInQueue.iterator();
            int customIterator = 0;
            while(i.hasNext()){
                Persoon persoon = i.next();
                if(customIterator < eersteXPersonen){
                    this.afrekenen(persoon);
                    i.remove();
                }
                customIterator++;
            }
            return personsInQueue;
        }
        else{
            ArrayList<Persoon> p = new ArrayList<Persoon>();
            return p;
        }
    }
    public int aantalArtikelen(){
        return this.totaalArtikelen;
    }
    public double aantalMoneys(){
        return this.totaalMoneys;
    }
    public void resetKassa(){
        this.totaalMoneys = 0;
        this.totaalArtikelen = 0;
    }
}

