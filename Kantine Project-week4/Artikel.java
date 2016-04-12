public class Artikel
{
    private String naam;
    private double prijs;
    
    public Artikel(String naam, double prijs){
        this.naam = naam;
        this.prijs = prijs;
    }
    // Setters
    public void setNaam(String naam){
        this.naam = naam;
    }
    public void setPrijs(double prijs){
        this.prijs = prijs;
    }
    // Getters
    public String getNaam(){
        return naam;
    }
    
    public double getPrijs(){
        return prijs;
    }
    // Printers
    public void drukAf(){
        System.out.println("Naam: " + naam);
        System.out.println("Prijs: " + prijs);
    }
}
