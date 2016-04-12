import java.io.*;
import java.util.Scanner;

public class Persoon
{
    private int BSN, geboorteDag, geboorteMaand, geboorteJaar;
    private String voorNaam, achterNaam;
    private char geslacht;
    private Dienblad dienblad;
    private Betaalwijze betaalwijze;
    
    /**
     * BSN mag alleen met nummers geen decimalen
     * Geboortemaand is de maand waarin u geboren bent, numerieke waarde
     * Same shit voor dag
     * Same shit voor jaar
     * Voornaam is wat mensen u noemen
     * Achternaam staat op het blauwe briefje
     * Geslacht is M of V. Niet Man. Niet Vrouw. OK? OK.
     */
    public Persoon(int BSN, int geboorteDag, int geboorteMaand, int geboorteJaar, String voorNaam, String achterNaam, char geslacht){
        if(geslacht == 'M' || geslacht == 'm' || geslacht == 'v' || geslacht == 'V'){
            if(geslacht == 'M' || geslacht == 'm'){
                this.geslacht = 'M';
            }
            else{
                this.geslacht = 'V';
            }
            this.BSN = BSN;
            this.geboorteDag = geboorteDag;
            this.geboorteMaand = geboorteMaand;
            this.geboorteJaar = geboorteJaar;
            this.voorNaam = voorNaam;
            this.achterNaam = achterNaam;
           
        }
        else{
           System.out.println("Geslacht moet M of V zijn, zonder quotes.");
           Scanner user_input = new Scanner( System.in );
           boolean nietgoed = true;
           do{
            if(geslacht == 'M' || geslacht == 'm' || geslacht == 'v' || geslacht == 'V'){
                   nietgoed = false;
                   if(geslacht == 'M' || geslacht == 'm'){
                       this.geslacht = 'M';
                   }
                   else{
                       this.geslacht = 'V';
                   }
                } else {
               System.out.print("Voer je geslacht in: ");
               geslacht = new String(user_input.next( )).charAt(0);
            }
           }while(nietgoed);
           System.out.print("Het mocht even duren.");
               
        }
    }
    public Persoon(){
        this.BSN = 12345;
        this.geboorteDag = 5;
        this.geboorteMaand = 5;
        this.geboorteJaar = 16;
        this.voorNaam = "Wouter";
        this.achterNaam = "houtsma";
        this.geslacht = 'm';
    }
    // Setters
    public void setBSN(int BSN)
    {
        this.BSN = BSN;
    }
    public void setVoorNaam(String voorNaam)
    {
        this.voorNaam = voorNaam;
    }
    public void setAchterNaam(String achterNaam)
    {
        this.achterNaam = achterNaam;
    }
    public void setGeslacht(char geslacht)
    {
        /**
         * Alleen M of V in vullen tussen enkele quotes!! ('M' of 'V')
         */
        if(geslacht == 'M' || geslacht == 'm' || geslacht == 'v' || geslacht == 'V'){
            if(geslacht == 'M' || geslacht == 'm'){
                this.geslacht = 'M';
            }
            else{
                this.geslacht = 'V';
            }
        }
        else{
            System.out.println("Het stond er zo duidelijk, of 'M' of 'V'! D:<");
        }
    }
    public void setGeboorteDatum(int geboorteDag, int geboorteMaand, int geboorteJaar)
    {
        if(geboorteDag > 1 && geboorteMaand >= 1  && geboorteMaand <= 12 && geboorteJaar > 1000 && geboorteJaar < 2100){
            boolean mag = true;
            switch (geboorteMaand){
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    if(geboorteDag > 31){
                        mag = false;
                    }
                    break;
                case 2: 
                    if((geboorteJaar % 4 == 0 && geboorteJaar % 100 != 0) || geboorteJaar % 400 == 0){
                        if(geboorteDag > 29){
                            mag = false;
                        }
                        //System.out.println("Het is een schrikkeljaar yayaya");
                    }
                    else{
                        if(geboorteDag > 28){
                            mag = false;
                            
                        }
                    }
                    break;
                default:
                    if(geboorteDag > 30){
                        mag = false;
                    }   
            }
            if(mag){
                this.geboorteDag = geboorteDag;
                this.geboorteMaand = geboorteMaand;
                this.geboorteJaar = geboorteJaar;
            }
            else{
                System.out.println("Je maakt fouten, hou daar mee op");
                this.geboorteDag = 0;
                this.geboorteMaand = 0;
                this.geboorteJaar = 0;
            }
        }
        else{
            System.out.println("Je deed vast iets fout. Doe het opnieuw maar beter.");
        }
    }
    // Getters
    public String getGeboorteDatum()
    {
        if(geboorteDag == 0){
            return new String("Niet bekend");
        }
        else{
            return new String("" + geboorteDag + "/" + geboorteMaand + "/" + geboorteJaar);
        }
    }
    public String getVoorNaam()
    {
        return voorNaam;
    }
    public String getAchterNaam()
    {
        return achterNaam;
    }
    public String getGeslacht()
    {
        if(geslacht == 'M'){
            return new String("Man");
        } else {
            return new String("Vrouw");
        }
    }
    public Integer getBSN()
    {
        return BSN;
    }
    // Printer
    public void drukAf()
    {
        System.out.println("\nBSN: " + BSN);
        System.out.println("Voornaam: " + voorNaam);
        System.out.println("Achternaam: " + achterNaam);
        if(geslacht == 'M'){
            System.out.println("Geslacht: Man");
        }
        else{
            System.out.println("Geslacht: Vrouw");
        }
        System.out.println("Geboortedatum: " + geboorteDag + "-" + geboorteMaand + "-" + geboorteJaar);
        System.out.println("----------------------------------");
    }
    
    public void pakDienblad(Dienblad dienblad) 
    {
        this.dienblad = dienblad;
    }
    
    public Dienblad getDienblad() 
    {
        return this.dienblad;
    } 
    
    public void pakArtikel(Artikel artikel)
    {
        if(this.dienblad != null) {
            this.dienblad.voegToe(artikel);
        } else{
            System.out.println("Pak eerst een dienblad.");
        }
    }
    public double getTotaalPrijs()
    {
        return this.dienblad.getTotaalPrijs();
    } 
    public int getAantalArtikelen()
    {
        if(this.dienblad != null){
            return this.dienblad.getAantalArtikelen();
        }
        else{
            System.out.println("Pak eerst een dienblad.");
            return 0;
        }
    } 
    
    public boolean equals(Object obj)
    {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Persoon)) {
            return false;
        }
        Persoon p = (Persoon) obj;
        return getBSN().equals(p.getBSN());
    }
    
    public Betaalwijze getBetaalWijze() {
        return betaalwijze;
    }
    
    public void setBetaalWijze(Betaalwijze betaalwijze)
    {
        this.betaalwijze = betaalwijze;
    }
}
