//user class
//impliments sockets and stream readers
import java.io.*;
import java.net.*;

public class user extends Thread
{
   private Socket connection;
   private BufferedReader inFromClient;
   private String sentence;
   private String userQuit = "/quit";
   private boolean running = true;
   
   user(Socket s)
   {
      connection = s;
      try
      {
         inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      }
      catch(Exception e){
         System.out.println("Cannot create BufferedReader for user");
      }
   }
   
   public void run()
   {
      while(running)
      {
         try{
            if(inFromClient.ready())
            {
               sentence = inFromClient.readLine();
            }else{
               sentence = "";
            }
         }catch (Exception e){
         }
         
         if(sentence.equals(userQuit))
         {
            System.out.println("Quitting");
            quit();
         }
         else if(!sentence.equals(""))
         {
            System.out.println(sentence);
            sentence = "";
         }
      }
   }
   
   public void quit() //allows the user to stop the thread
   {
      running = false;
   }
   
   public String read()throws Exception
   {
      if(inFromClient.ready())
      {
         return inFromClient.readLine();
      }
      else
         return ""; 
   }
}