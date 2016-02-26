//a listener thread that 
//listens for connections
//and puts them in a list
import java.io.*;
import java.net.*;
import java.util.*;


public class Listener extends Thread implements globals
{
   private int port = 6789;
   private ServerSocket welcomeSocket;
   private boolean running = true;
   
   Listener() //constructor
   {
      try
      {
         welcomeSocket = new ServerSocket(port); //create new socket and bind it to port 6789
      }
      catch(Exception e)
      {
         System.out.println("Could not create a listener socket");
      }
   }
   
   public void run()
   {
      do
      {
         if(running)
         {
            try{
               globalUsers.add(new user(welcomeSocket.accept())); //create the user and add them
               globalUsers.get(globalUsers.size()-1).start(); //run the user
               System.out.println("added client");
            }
	    catch(SocketException s)
	    {
		
	    }
            catch(Exception e)
            {
               System.out.println("Could not add a Socket to client");
               System.out.println(e);
            }
            
         }
      }while(running);
   }
   
   public void quit()throws Exception //something to stop the thread
   {
      running = false;
      welcomeSocket.close();
   }
}
