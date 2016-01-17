//Creates and IRC Server
import java.io.*;
import java.net.*;
import java.util.*; //for lists

public class IRC_Server
{
   public static void main(String args[]) throws Exception
   {
      Scanner scan = new Scanner(System.in);
      List<user> clients = new ArrayList<user>();
      String ServerInput;
      String capitalizedSentence;
      String userQuit = "/quit";
      String userList = "/list";
      boolean running = true;
      
      //create listener thread that accecpts incoming connections
      
      Listener ears = new Listener(clients);
      ears.start();
      
      
      //DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
      
      
      do
      {
         System.out.println("/list to list users\n"+
                            "/quit to stop server");
         ServerInput = scan.nextLine();
         
         if(ServerInput.equals(userList))
         {
            for(int i=0; i<clients.size(); i++)
            {
               System.out.println(clients.get(i).toString());
            }
         }
         else if(ServerInput.equals(userQuit))
         {
            running = false;
            ears.quit();
         }
         else
         {
            System.out.println("Unrecognized Command");
         }
         
         ServerInput = "";
         /* don't do anything since users are threaded
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
         */
      }while(running);
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