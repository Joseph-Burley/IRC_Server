//a listener thread that 
//listens for connections
//and puts them in a list
import java.io.*;
import java.net.*;
import java.util.*;


public class Listener extends Thread
{
   List<user> clients;
   int port = 6789;
   ServerSocket welcomeSocket;
   
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
            clients.add(new user(welcomeSocket.accept()));
         }
         catch(Exception e)
         {
            System.out.println("Could not add a Socket to client");
         }
         System.out.println("added client");
      }while(true);
   }
}