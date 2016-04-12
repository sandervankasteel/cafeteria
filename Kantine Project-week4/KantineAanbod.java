import java.util.*;

public class KantineAanbod {
    // interne opslag voorraad
    private HashMap<String, ArrayList<Artikel>> aanbod;
    private HashMap<String, Integer> voorraad;
   
    
    /**
     * Constructor. Het eerste argument is een lijst met artikelnamen,
     * het tweede argument is een lijst met prijzen en het derde argument
     * is een lijst met hoeveelheden. Let op: de dimensies van de drie arrays
     * moeten wel gelijk zijn!
     */
    public KantineAanbod(String[] artikelnaam, double[] prijs, int[] hoeveelheid) 
    {
        aanbod=new HashMap<String, ArrayList<Artikel>>();
        voorraad = new HashMap<String, Integer>();
        
        for(int i = 0; i < artikelnaam.length; i++) {
            ArrayList<Artikel> artikelen=new ArrayList<Artikel>();
            for(int j=0;j<hoeveelheid[i];j++) {
                artikelen.add(new Artikel(artikelnaam[i], prijs[i]));
            }
            aanbod.put(artikelnaam[i], artikelen);
            voorraad.put(artikelnaam[i], hoeveelheid[i]);
        }
    }
    /*
     * Private methode om de lijst van artikelen te krijgen op basis van de    
     * naam van het artikel. Retourneert null als artikel niet bestaat.
     */
    private ArrayList<Artikel> getArrayList(String productnaam) {
         return aanbod.get(productnaam); 
    }

    /**
     * Private methode om een Artikel van de stapel artikelen af te pakken. 
     * Retourneert null als de stapel leeg is.
     */
    private Artikel getArtikel(ArrayList<Artikel> stapel) 
    {
        if (stapel==null) { 
            return null;
        }
        if (stapel.size()==0)
        {
           return null;
        }
        else 
        {
            Artikel a=stapel.get(0);
            stapel.remove(0);
            return a;
        }
    }

    /**
     * Publieke methode om een artikel via naam van de stapel te pakken.
     * Retouneert null als artikel niet bestaat of niet op voorraad is.
     * @param naam (van artikel)
     * @return artikel (of null)
     */
    public Artikel getArtikel(String naam)
    {
        return getArtikel(getArrayList(naam));
    }
    
    public HashMap<String, String> getVoorraad()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        Set<String> keys = aanbod.keySet();
        for (String key: keys) {
            map.put(key, "" + aanbod.get(key).size());
        }      
        return map;
    }
    
    /**
     * Vult de voorraad aan tot het begin niveau als deze beneden het minimum niveau is gekomen
     * @param minimale voorraad
     */
    public void vulAanbod(int min)
    {
        Set<String> keys = aanbod.keySet();
        for (String key: keys) {
            ArrayList<Artikel> value = aanbod.get(key);
            if(value.size() < min) {
                int max = voorraad.get(key);
                while(value.size() < max) {
                    value.add(value.get(0));
                }
            }
        }        
    }
}