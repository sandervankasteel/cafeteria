
/**
 * Write a description of class KantineSimulatie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KantineSimulatie
{
    private Kantine kantine;
    //private double[] totalen;
    
    public KantineSimulatie (){
        this.kantine = new Kantine();
    }
    public void simuleer(int dagen){
        if(this.kantine.getArtikelen().size() == 0)
            this.kantine.randomArtikelen(5, 500, 1000, 10);
       
        System.out.println("########## Simulatie gestart! #########");
        for(int i = 0; i < dagen; i++){
            this.kantine.genereerPersonen((10+i), 1, this.kantine.getArtikelen().size());
            this.kantine.zetPersonenInRij();
            this.kantine.verwerkKassaRij();
            System.out.println("Dag " + (int)(i+1) + ": " + this.kantine.hoeveelGeldInKassa() + " - Artikelen verkocht: " + this.kantine.hoeveelArtikelenVerkocht() + " - Klanten geholpen: " + (10+i) );
            this.kantine.resetKassa();
        }
        System.out.println("######## Simulatie beëindigd. #########");
    }
}
