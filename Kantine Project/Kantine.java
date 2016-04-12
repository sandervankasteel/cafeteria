import java.util.ArrayList;
/**
 * Write a description of class Kantine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kantine
{   
    private Kassa kassa;
    private KassaRij kr;
    private char[] alphabet;
    private ArrayList<Artikel> a;
    private ArrayList<Persoon> p;
    
    public Kantine(){
        this.kr = new KassaRij();
        this.kassa = new Kassa(kr);
        generateAlphabet();
        this.a = new ArrayList<Artikel>();
        this.p = new ArrayList<Persoon>();
    }
    
    /**
     * Zet gegenereerde personen in de rij.
     */
    public void zetPersonenInRij(){
        if(this.p.size() == 0){
            genereerPersonen(5, 1, 3);
        }
        for(Persoon persoon : this.p){
            this.kr.sluitAchteraan(persoon);
        }
    }
    public void resetKassa(){
        this.kassa.resetKassa();
    }
    public double hoeveelGeldInKassa(){
        return this.kassa.aantalMoneys();
    }
    public int hoeveelArtikelenVerkocht(){
        return this.kassa.aantalArtikelen();
    }
    public void verwerkKassaRij(){
        this.kassa.iedereenAfrekenen();
    }
    /**
     * Genereer personen.
     * @param hoeveelheidPersonen Hoeveel personen je aanmaakt
     * @param minArtikelen De minimale hoeveelheid artikelen die een persoon pakt
     * @param maxArtikelen De maximale hoeveelheid artikelen die een persoon pakt
     */
    public void genereerPersonen(int hoeveelheidPersonen, int minArtikelen, int maxArtikelen){
        this.p = new ArrayList<Persoon>();
        if(this.a.size() < maxArtikelen){
           this.a = randomArtikelen(maxArtikelen, 500.0, 1000.0, 5); 
        }
        for(int y = 0; y < hoeveelheidPersonen; y++){
            this.p.add(new Persoon());
            this.p.get(y).pakDienblad();
            int hoeveelheidArtikelen = randomInt(minArtikelen, maxArtikelen);
            for(int z = 0; z < hoeveelheidArtikelen; z++){
                int artikelSize = this.a.size();
                int artikel = randomInt(0, (artikelSize - 1));
                this.p.get(y).pakArtikel(this.a.get(z));
            }
        }
    }
    /**
     * Maak random artikelen aan.
     * @param amount De hoeveelheid artikelen
     * @param pricelow De laagste prijs die gegenereerd kan worden.
     * @param pricehigh De hoogste prijs die gegenereerd kan worden.
     * @param nameLength De lengte die de willekeurige naam moet hebben.
     */
    public ArrayList<Artikel> randomArtikelen(int amount, double pricelow, double pricehigh, int nameLength){
        this.a = new ArrayList<Artikel>();
        for(int i = 0; i < amount; i++){
            String name = "";
            //double priceDifference = pricehigh - pricelow;
            double price = randomDouble(pricelow, pricehigh);
            for(int x =0; x < nameLength; x++){
                name = name + String.valueOf(alphabet[(randomInt(1,26) - 1)]);
            }
            this.a.add(new Artikel(name, price));
        }
        return this.a;
    }
    public ArrayList<Artikel> getArtikelen(){
        return this.a;
    }
    private int randomInt(int low, int high){
        return low + (int)(Math.random() * ((high - low) + 1)); // anti double fix
    }
    private double randomDouble(double low, double high){
        return Math.floor(Math.random()*(high-low)) + low;
    }

    private void generateAlphabet(){
        // Dit moet beter kunnen
        alphabet = new char[26];
        alphabet[0] = 'a';
        
        alphabet[1] = 'b';
        alphabet[2] = 'c';
        alphabet[3] = 'd';
        alphabet[4] = 'e';
        alphabet[5] = 'f';
        
        alphabet[6] = 'g';
        alphabet[7] = 'h';
        alphabet[8] = 'i';
        alphabet[9] = 'j';
        alphabet[10] = 'k';
        
        alphabet[11] = 'l';
        alphabet[12] = 'm';
        alphabet[13] = 'n';
        alphabet[14] = 'o';
        alphabet[15] = 'p';
        
        alphabet[16] = 'q';
        alphabet[17] = 'r';
        alphabet[18] = 's';
        alphabet[19] = 't';
        alphabet[20] = 'u';
        
        alphabet[21] = 'v';
        alphabet[22] = 'w';
        alphabet[23] = 'x';
        alphabet[24] = 'y';
        alphabet[25] = 'z';
    }
}