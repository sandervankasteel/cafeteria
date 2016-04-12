import java.util.ArrayList;
//import java.util.LinkedList;
/**
 * Write a description of class KassaRij here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KassaRij
{
    private ArrayList<Persoon> personen;
    //private LinkedList<Persoon> personen;
    public KassaRij(){
        personen = new ArrayList<Persoon>();
        //personen = new LinkedList<Persoon>();
    }
    
    /**
     * sluitAchteraan == Nieuw persoon toevoegen aan de rij
     * @param persoon Het persoon dat je toevoegd.
     */
    public void sluitAchteraan(Persoon persoon){
        this.personen.add(persoon);
    }
    public void mimicPersoon(){
        Persoon p = new Persoon();
        p.pakDienblad();
        this.personen.add(p);
        p = null;
    }
    
    public Persoon eerstePersoonInRij(){
        return this.personen.get(0);
    }
    
    public int aantalPersonen(){
        return this.personen.size();
    }
    public ArrayList<Persoon> getEntireQueue(){
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
