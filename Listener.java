//a listener thread that 
//listens for connections
//and puts them in a list
import java.io.*;
import java.net.*;
import java.util.*;


public class Listener extends Thread
{
   private List<user> clients;
   private int port = 6789;
   private ServerSocket welcomeSocket;
   private boolean running = true;
   
   Listener(List<user> c) //constructor
   {
      clients = c;
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
         try{
            clients.add(new user(welcomeSocket.accept())); //create the user and add them
            
            //problem lies here
            clients.get(clients.size()-1).run(); //run the user/
         }
         catch(Exception e)
         {
            System.out.println("Could not add a Socket to client");
         }
         System.out.println("added client");
      }while(running);
   }
   
   public void quit() //something to stop the thread
   {
      running = false;
   }
}