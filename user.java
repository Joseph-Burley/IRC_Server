//user class
//impliments sockets and stream readers
import java.io.*;
import java.net.*;
import java.util.*;

public class user extends Thread
{
   private Socket connection;
   private List<user> userList;
   private BufferedReader inFromClient;
   private String sentence;
   private String userQuit = "/quit";
   private boolean running = true;
   
   user(Socket s, List<user> L)
   {
      connection = s;
      userList = L;
      try
      {
         inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      }
      catch(Exception e){
         System.out.println("Cannot create BufferedReader for user");
      }
   }
   
   public void run() //for connection.close();
   {
      while(running)
      {
         try{
            if(inFromClient.ready())
            {
               sentence = inFromClient.readLine();
            }
            else{
               sentence = "";
            }
         }
         catch (Exception e){
         }
         
         if(sentence.equals(userQuit))
         {
            quit();
         }
         else if(!sentence.equals(""))
         {
            System.out.println(sentence);
            sentence = "";
         }
      }
      
      try{
         System.out.println("Quitting");
         connection.close();
         userList.remove(this);
      }catch (Exception e){
         System.out.println("Cannot quit user thread for some reason\n"+e);
      }
   }
   
   public void quit() //allows the user to stop the thread
   {
      running = false;
   }
}