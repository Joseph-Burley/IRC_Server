//Creates and IRC Server
//added a line to test git commits
import java.io.*;
import java.net.*;
import java.util.*; //for lists

public class IRC_Server implements globals
{
   
   public static void main(String args[]) throws Exception
   {
      Scanner scan = new Scanner(System.in);
      String ServerInput;
      String capitalizedSentence;
      String userQuit = "/quit";
      String userList = "/list";
      boolean running = true;
      
      //create listener thread that accecpts incoming connections
      
      Listener ears = new Listener();
      ears.start();
      
      channel system = new channel("System");
      
      
      //DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
      
      
      do
      {
         System.out.println("/list to list users\n"+
                            "/quit to stop server\n"+
                            "/sys to write to system log");
         ServerInput = scan.nextLine();
         
         if(ServerInput.equals(userList))
         {
            for(int i=0; i<globalUsers.size(); i++)
            {
               System.out.println(globalUsers.get(i).toString());
            }
         }
         else if(ServerInput.equals(userQuit))
         {
            running = false;
            ears.quit();
            ears = null;
            for(int i=0; i<globalUsers.size(); i++)
            {
               globalUsers.get(i).quit();
            }
         }
         else if(ServerInput.contains("/sys"))
         {
            system.writeLog(ServerInput);
         }
         else
         {
            System.out.println("Unrecognized Command");
         }
         
         ServerInput = "";
         
      }while(running);
         
   }
}
