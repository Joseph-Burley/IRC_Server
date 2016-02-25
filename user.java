//user class
//impliments sockets and stream readers
import java.io.*;
import java.net.*;
import java.util.*;

public class user extends Thread
{
   private String nickName = "";
   private String sentence;
   private String userQuit = "/quit";
   private String changeNick = "/nick";
   private Socket connection;
   private List<channel> channelList;
   private BufferedReader inFromClient;
   private BufferedWriter outToClient;
   
   private boolean running = true;
   
   user(Socket s)
   {
      connection = s;
      channelList = new ArrayList<channel>();
      try
      {
         inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         outToClient = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
      }
      catch(Exception e){
         System.out.println("Cannot create BufferedReader for user");
      }
   }
   
   public String getNickName()
   {
      return nickName;
   }
   
   public void run() 
   {
      while(running)
      {
         //get the sentence from socket for parsing
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
            System.out.println(e);
         }
         
         
         if(sentence.equals(userQuit))
         {
            quit();
         }
         else if(sentence.contains(changeNick))
         {
            String out = nickName + " changed name to: ";
            //find first occurance of a space
            int f = sentence.indexOf(' ');
            //find next occurance of space
            int s = sentence.indexOf(' ', f+1);
            //make nickName a substring
            nickName = sentence.substring(f+1, s);
            out += nickName;
            
            System.out.println(out);
            try{
               outToClient.write(out, 0, out.length());
            }
            catch(Exception e){
               System.out.println("Cannot send message to client\n" + e);
            }
            
         }
         else if(sentence.length() > 0)
         {
            for(int i=0; i<channelList.size(); i++)
            {
               channel c = channelList.get(i);
               
               c.writeUsers(sentence);
               
             
            }
            System.out.println(sentence);
            sentence = "";
         }
      }
      
      try{
         System.out.println("Quitting");
         connection.close();
         //userList.remove(this);
      }
      catch (Exception e){
         System.out.println("Cannot quit user thread for some reason\n"+e);
      }
   }
   
   public void quit() //allows the user to stop the thread
   {
      running = false;
   }

   public void write(String s)
   {
      try{
         outToClient.write(s, 0, s.length());
         outToClient.newLine();
         outToClient.flush();
      }
      catch(Exception e){
         System.out.println("Cannot write to client\n"+e);
      }
   }
}
