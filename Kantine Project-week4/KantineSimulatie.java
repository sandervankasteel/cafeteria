import java.util.*;

public class KantineSimulatie {
    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN=4;

    // artikelen
    private static final String[] artikelnamen=
        new String[] {"Koffie","Broodje hamburger", "Broodje kaas", "Melk"};

    // prijzen
    private static double[] artikelprijzen=
        new double[]{1.50, 2.10, 1.65, 1.65};   

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT=10000;
    private static final int MAX_ARTIKELEN_PER_SOORT=20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG=50;
    private static final int MAX_PERSONEN_PER_DAG=100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON=1;
    private static final int MAX_ARTIKELEN_PER_PERSOON=4;
    
    //quick and dirty ^^ Just the way I like it
    int totaalDagen = 0;
    double[] inKasPerDag;
    int[] verkochtPerDag;

    /**
     * Constructor
     */
    public KantineSimulatie() 
    {
        kantine=new Kantine();
        random=new Random();
        int[] hoeveelheden=getRandomArray(AANTAL_ARTIKELEN,MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod=new KantineAanbod(artikelnamen, artikelprijzen,hoeveelheden); 
        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max 
     * van de gegeven lengte te genereren
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) 
    {
        int[] temp=new int[lengte];
        for(int i=0;i<lengte;i++) {
            temp[i]=getRandomValue(min, max);
        }
        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) 
    {
        return random.nextInt(max-min+1)+min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array 
     * artikelnamen de bijhorende array van artikelnamen te maken
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) 
    {
        String[] artikelen=new String[indexen.length];
        for(int i=0;i<indexen.length;i++) 
        { 
            artikelen[i]=artikelnamen[indexen[i]];
        }
        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen in het verloop van de kantine
     * @param dagen
     */
    public void simuleer(int dagen) 
    {
        inKasPerDag = new double[dagen];
        verkochtPerDag = new int[dagen];
        // for lus voor dagen
        for(int i=0;i<dagen;i++) {
            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = 100;
            int aantalStudenten = 89;
            int aantalDocenten = 10;
            int aantalKantineMedewerkers = 1;
            
            totaalDagen = dagen;
            
            // laat de personen maar komen...
            for(int j=0;j<aantalpersonen;j++) 
            {
               Persoon persoon = (j >= aantalKantineMedewerkers) ? (j >= aantalDocenten ? new Student() : new Docent()) : new KantineMedewerker();
               
               // maak persoon en dienblad aan, koppel ze
               Dienblad dienblad = new Dienblad(); // maak nieuw dienblad
               persoon.pakDienblad(dienblad); // en koppelen maar
                
               // bedenk hoeveel artikelen worden gepakt
               int aantalArtikelen= getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);
    
               // genereer de “artikelnummers”, dit zijn indexen 
               // van de artikelnamen array  
               int[] tepakken=getRandomArray(aantalArtikelen, 0, AANTAL_ARTIKELEN-1);
    
               // vind de artikelnamen op basis van de indexen hierboven
               String[] artikelen=geefArtikelNamen(tepakken);
               
               
               // loop de kantine binnen, pak de gewenste artikelen, sluit aan
               for(int k = 0; k < artikelen.length; k++) {
                   dienblad.voegToe(kantineaanbod.getArtikel(artikelen[k]));
               }
                
               // En nu netjes achteraan aansluiten en niet voordringen he!
               kantine.getKassa().getKassaRij().sluitAchteraan(persoon);
               /* persoon.drukAf(); */
            }
            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            
            verkochtPerDag[i] += kantine.getKassa().aantalArtikelen();
            // druk de dag omzet af en hoeveel personen binnen zijn gekomen
            System.out.println("Totaal aantal verkochte artikelen: "+kantine.getKassa().aantalArtikelen());
            System.out.println("Geld in kas: "+kantine.getKassa().aantalMoneys());
            inKasPerDag[i] = kantine.getKassa().aantalMoneys();
            
            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
            kantineaanbod.vulAanbod(MIN_ARTIKELEN_PER_SOORT);
            System.out.println("Dagomzet is : " + Administratie.berekenGemiddeldeOmzet(inKasPerDag));
        }
    System.out.println("Gemiddelde omzet over ("+ totaalDagen + ") dagen : " + Administratie.berekenGemiddeldeOmzet(inKasPerDag));
    System.out.println("Gemiddelde verkoopcijfers over("+ totaalDagen +") dagen : " + Administratie.berekenGemiddeldAantal(verkochtPerDag)); //Gemiddeld aantal verkochte artikelen
    }
    
}
