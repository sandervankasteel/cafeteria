public class TeWeinigGeldException extends Exception
{
   private String wanBetaler;
   
   public TeWeinigGeldException() {
        System.out.println("Iemand betaalt te weinig!"); 
   }
   public TeWeinigGeldException(Exception e) { new TeWeinigGeldException(); /*placeholder*/ }
   public TeWeinigGeldException(String message) {
        System.out.println(message + " heeft te weinig cash money"); 
   }
}
