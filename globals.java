import java.util.*;
import java.io.*;
import java.net.*;

public interface globals
{
   public static List<user> globalUsers = new ArrayList<user>();
   public static List<channel> globalChannels = new ArrayList<channel>();
   
   public static channel sys_channel = new channel("System");
}