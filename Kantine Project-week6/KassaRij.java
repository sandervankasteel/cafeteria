import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Write a description of class KassaRij here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KassaRij
{
    private LinkedList<Persoon> personen;
    public KassaRij(){
        personen = new LinkedList<Persoon>();
    }
    
    /**
     * sluitAchteraan == Nieuw persoon toevoegen aan de rij
     * @param persoon Het persoon dat je toevoegd.
     */
    public void sluitAchteraan(Persoon persoon){
        this.personen.add(persoon);
    }
    public void mimicPersoon(){
        Dienblad dienblad = new Dienblad();
        Persoon p = new Persoon();
        p.pakDienblad(dienblad);
        this.personen.add(p);
        p = null;
    }
    
    public Persoon eerstePersoonInRij(){
        if(personen.size() > 0) {
            Persoon eersteInRij = personen.get(0);
            personen.remove(0);
            return eersteInRij;
        }
        else {
            return null;
        }
    }
    
    public int aantalPersonen(){
        return this.personen.size();
    }
    public LinkedList<Persoon> getEntireQueue(){
        return this.personen;
    }
    
    public boolean erIsEenRij(){
        if(this.personen.size() == 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    
}
