/**
 * Write a description of class Administratie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Administratie {
    private static final int DAGEN_IN_WEEK = 7;

    private Administratie() {

    }
    
    public static double berekenGemiddeldAantal(int[] aantal) {
        if (aantal.length != 0) {
            double totaal = 0.0;
            for (int i=0; i < aantal.length; i++) {
                totaal += (double) aantal[i];    
            }
            
            return totaal / aantal.length;
        } else {
            return 0.0;
        }
    }
    
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        if (omzet.length != 0) {
            double totaalOmzet = 0.0;
            for (int i = 0; i < omzet.length; i++) {
                totaalOmzet += omzet[i];
            }
            return totaalOmzet / omzet.length;
        } else {
            return 0.0; 
        }

    }
    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp=new double[DAGEN_IN_WEEK];
        for(int i = 0; i < DAGEN_IN_WEEK; i++)
        {
            int j = 0;
            while((DAGEN_IN_WEEK * j) + i < omzet.length)
            {
                temp[i]+=omzet[(DAGEN_IN_WEEK * j) + i];
                j++;
            }
        }
        return temp;
    }

}
