//user class
//impliments sockets and stream readers
import java.io.*;
import java.net.*;

public class user
{
   Socket connection;
   BufferedReader inFromClient;
   
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