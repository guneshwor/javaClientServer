import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Client {

   private Socket clientSocket;
   
   public Client(String serverName, int port) throws IOException  {
      clientSocket = new Socket(serverName, port);
      
   }   
   
   public void run() {
         String serverName = "127.0.0.1";
      int port = 54321;
      
      //connect(serverName, port);
      try {
         	  System.out.println("Connecting to " + serverName + " on port " + port);
      Socket client = new Socket(serverName, port);
      System.out.println("Connected to " + client.getRemoteSocketAddress());
      
      //encrypt("hi");
      
      OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);         		 
         out.writeUTF("Hello from " + client.getLocalSocketAddress());
         
/*         //Enter username and password
         System.out.print("Username: ");
         Scanner input = new Scanner(System.in);
         String username = input.nextLine();
         System.out.print("Password: ");
         Scanner input1 = new Scanner(System.in);
         String pwd = input1.nextLine();
		 //out.writeUTF(username + ":" + pwd);
		 */
		 
		 

         //System.out.println(client.getOutputStream());
         //InputStream inFromServer = client.getInputStream();
         //DataInputStream in = new DataInputStream(inFromServer);
         //closeConnection();
          //System.out.println("Server says \n" + in.readUTF());
          client.close();
         } catch(IOException e) {
         e.printStackTrace();
      }
   }
   
   public static void connect(String serverName, int port) {
   	  System.out.println("Connecting to " + serverName + " on port " + port);
      Socket client = new Socket(serverName, port);
      System.out.println("Connected to " + client.getRemoteSocketAddress());
   }
   /*
   public void closeConnection() {
   	   System.out.println("Server says \n" + in.readUTF());
       client.close();
   }*/
   
   public static String encrypt(String text) throws Exception {
   	  System.out.println("Encrypting user:pass");
   	  String keytext = "bad8deadcafef00d";
   	  byte[] input = text.getBytes();

      SecretKeySpec key = new SecretKeySpec(keytext.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");///ECB/NoPadding");
	  System.out.println(new String(input));

	  // encryption pass
      cipher.init(Cipher.ENCRYPT_MODE, key);

      byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
      int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
      ctLength += cipher.doFinal(cipherText, ctLength);
      System.out.println(new String(cipherText));
      System.out.println(ctLength);
      return new String(cipherText);
   }
   
   public static void main(String [] args) {
      String serverName = "127.0.0.1";
      int port = 54321;
      try {
         //Client c = new Client(serverName, port);
         //c.run(); 
         
         //connect(serverName, port);
         System.out.println("Connecting to " + serverName + " on port " + port);
      	 Socket client = new Socket(serverName, port);
      	 System.out.println("Connected to " + client.getRemoteSocketAddress());
         
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);         		 
         //out.writeUTF("HelloMain from " + client.getLocalSocketAddress());  
         
         //Enter username and password
         System.out.print("Username: ");
         Scanner input = new Scanner(System.in);
         String username = input.nextLine();
         System.out.print("Password: ");
         Scanner input1 = new Scanner(System.in);
         String pwd = input1.nextLine();
         
         //Encrypt and send to server
		 out.writeUTF(encrypt(username + ":" + pwd));
		 
		 //byte[] send = encrypt(username + ":" + pwd);
		 //System.out.println(send);
         
         client.close();   
      }
      catch(IOException e) {
         e.printStackTrace();
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }
}
