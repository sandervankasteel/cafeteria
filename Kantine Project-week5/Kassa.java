import java.util.LinkedList;
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
    private Dienblad dienblad;
    private Betaalwijze BetaalWijze;
    
    public Kassa (KassaRij kassaRij){
        this.rij = kassaRij;
        this.totaalArtikelen = 0;
        this.totaalMoneys = 0;
    }
    /**
     * Rekent een persoon af uit de rij.
     */
    public void afrekenen(Persoon persoon)
    {        
        double kortingsBedrag = 0.0;
        double kortingsPercentage = 0.0;
        boolean heeftMaximum = false;
        double totaalPrijs = 0.0;
        if(persoon instanceof KortingskaartHouder) {
            KortingskaartHouder kaartHouder = (KortingskaartHouder) persoon;
            kortingsPercentage = kaartHouder.geefKortingsPercentage();
            heeftMaximum = kaartHouder.heeftMaximum();
            if(heeftMaximum) {
                kortingsBedrag = kaartHouder.geefMaximum();
            }
        }
        totaalPrijs = getTotaalPrijs(persoon);
        totaalPrijs =- kortingsBedrag;
        //System.out.println("Kortingsbedrag : " + kortingsBedrag);
        //System.out.println("Totaal bedrag zonder korting : " + getTotaalPrijs(persoon));
        //System.out.println("Totaal bedrag met korting : " + totaalPrijs);
        //System.out.println("--------------------------------------------");
        // DEBUG voor saldo na betaling 
        // Betaalwijze betaalwijze2 = persoon.getBetaalWijze();
        // System.out.println("Saldo na betaling : " + betaalwijze2.getSaldo());
        
        if (totaalPrijs > persoon.getBetaalWijze().getSaldo()) {
            System.out.println("Te weinig saldo");
        } else {
            persoon.getBetaalWijze().betaal(totaalPrijs);
            totaalMoneys += getTotaalPrijs(persoon);
            totaalArtikelen += getAantalArtikelen(persoon);
        }
    }
    /**
     * Gooit de hele rij aan mensen leeg en handeld hun aankopen af.
     */
    public void iedereenAfrekenen()
    {
        if(this.rij.erIsEenRij()){ // Overbodig met foreach?
            LinkedList<Persoon> personsInQueue = this.rij.getEntireQueue();
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
    public LinkedList<Persoon> rekenAf(int eersteXPersonen)
    {
        if(this.rij.erIsEenRij()){ // Overbodig met foreach?
            LinkedList<Persoon> personsInQueue = this.rij.getEntireQueue();
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
            LinkedList<Persoon> p = new LinkedList<Persoon>();
            return p;
        }
    }
    public int aantalArtikelen(){
        return totaalArtikelen;
    }
    public double aantalMoneys(){
        return totaalMoneys;
    }
    public void resetKassa(){
        this.totaalMoneys = 0;
        this.totaalArtikelen = 0;
    }
    
    public double getTotaalPrijs(Persoon afrekenedPersoon) {
        double totaalPrijs = 0.0;
        
        Iterator<Artikel> iterator = afrekenedPersoon.getDienblad().getIterator();
        while(iterator.hasNext()) {
            Artikel artikel = iterator.next();
            totaalPrijs =+ artikel.getPrijs();
        }
        return totaalPrijs;
    }
    
    public int getAantalArtikelen(Persoon afrekenedPersoon) {
        int artikelen = 0;
        Iterator<Artikel> iterator = afrekenedPersoon.getDienblad().getIterator();
        while(iterator.hasNext()) {
           iterator.next();
           artikelen++;
        }
        return artikelen;
    }
        
    public KassaRij getKassaRij() {
        return rij;
    }
}

