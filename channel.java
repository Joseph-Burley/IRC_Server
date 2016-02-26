//this is the channel class
//this will communicate with user objects
//and will log channel communications
import java.util.*; //lists, scanner
import java.io.File; //file
import java.io.FileWriter;

public class channel implements globals
{
   private List<user> userList;
   private String name;
   private FileWriter log_out;
	
   
   channel(String n)
   {
      name = n;
      try{
         log_out = new FileWriter(new File("channelLogs/"+name+".txt"), true);
         log_out.write("it works!!!\n");
         log_out.flush();
      }
      catch(Exception e){
         System.out.println(e);
      }
      
      userList = new ArrayList<user>();
   }
   
   public void addUser(user u)
   {
      userList.add(u);
      writeLog("User: " + u.getNickName() +" was added\n");
   }
   
   public String getName()
   {
      return name;
   }
   
   public void writeUsers(String s)
   {
      for(int i=0; i< userList.size(); i++)
      {
         user u = userList.get(i);
         u.write(s);
      }
      writeLog(s);
   }
   public void writeLog(String s)
   {
      try{
         log_out.write(s);
         log_out.flush();
      }
      catch(Exception e){
         System.out.println(e);
      }
   }
}
