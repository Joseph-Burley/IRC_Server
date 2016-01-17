//Creates and IRC Server
import java.io.*;
import java.net.*;
import java.util.*; //for lists

public class IRC_Server
{
   public static void main(String args[]) throws Exception
   {
      List<user> clients = new ArrayList<user>();
      String clientSentence;
      String capitalizedSentence;
      String userQuit = "/quit";
      boolean running = true;
      
      //create listener thread that accecpts incoming connections
      
      Listener ears = new Listener(clients);
      ears.start();
      
      
      //DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
      
      int j=0;
      while(true)
      {
         //System.out.println("On loop: " + j);
         for(int i=0; i<clients.size(); i++)
         {
            //System.out.println("On client: " + i);
            clientSentence = clients.get(i).read();
            if(!clientSentence.equals(""))
            {
               System.out.println(clientSentence);
            }
         }
      }
         /*
         clientSentence = inFromClient.readLine();
         if(clientSentence.equals(userQuit))
         {
            System.out.println("The user quit the session");
            break;
         }
         else
         {
            System.out.println("Received: "+clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
         }
         */
      
      
      
      //System.out.println("got here");
   }
}