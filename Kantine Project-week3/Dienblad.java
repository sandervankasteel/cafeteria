import java.util.Stack;
import java.util.Iterator;
/**
 * Write a description of class Dienblad here.
 * 
 * @author Sander van Kasteel
 * @version 1.0
 */
public class Dienblad
{
    private Stack<Artikel> artikelen;
    private Artikel voegToe2;
    private int aantalArtikelen;
    private double totaalPrijs;
    /**
     * Constructor for objects of class Dienblad
     */
    public Dienblad()
    {
        // initialise instance variables
        artikelen = new Stack<Artikel>();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void voegToe(Artikel voegToe2)
    {
      this.artikelen.add(voegToe2);
    }
    
    public int getAantalArtikelen()
    {
        aantalArtikelen = artikelen.size();
        return aantalArtikelen;
    }
    
    public double getTotaalPrijs()
    { 
        totaalPrijs = 0;
        for (Artikel uitLijst : artikelen ) {
            double var6;
            var6 = uitLijst.getPrijs();
            totaalPrijs = totaalPrijs + var6;
        } 
        return totaalPrijs;
    }   
    
    public Iterator<Artikel> getIterator() {
        return artikelen.iterator();
    }    
}