import java.net.*;
import java.io.*;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Server extends Thread {
   private ServerSocket serverSocket;
   
   public Server(int port) throws IOException  {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(90000);
   }
   
   /*public void encrypt(String plaintext) {
   	  System.out.println("encrypt");	
   }*/
   
   public static String decrypt(String text) throws Exception {
   	  System.out.println("Decrypting user:pass");
   	  String keytext = "bad8deadcafef00d";
   	  byte[] input = text.getBytes();

      SecretKeySpec key = new SecretKeySpec(keytext.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");///ECB/NoPadding");
	  System.out.println(new String(input));

	  // decryption pass
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] plainText = new byte[cipher.getOutputSize(text.length())];
      //int ptLength = cipher.update(input, 0, ctLength, plainText, 0);
      //ptLength += cipher.doFinal(plainText, ptLength);
      System.out.println(new String(plainText));
      //System.out.println(ptLength);
      return new String(plainText);
   }
   /*public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + " ...");
            Socket server = serverSocket.accept();
            System.out.println("Connected to " + server.getRemoteSocketAddress());
           
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());            
            
            //Decrypt and display
            System.out.println(decrypt(in.readUTF()));
            
            //DataOutputStream out = new DataOutputStream(server.getOutputStream());
            //out.writeUTF("Connection to " + server.getLocalSocketAddress() + " Ended\nGoodbye!");
            server.close();
            //encrypt("you");
         }
         catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }
         catch(IOException e) {
            e.printStackTrace();
            break;
         } catch(Exception e) {
         	e.printStackTrace();
         	break;
      	}
      }
   }*/
   public static void main(String [] args)  {
      int port = 54321; //Integer.parseInt(args[0]);
      try {
         //Thread t = new Server(port);
         //t.start();
         System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + " ...");
            Socket server = serverSocket.accept();
            System.out.println("Connected to " + server.getRemoteSocketAddress());
           
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());            
            
            //Decrypt and display
            System.out.println(decrypt(in.readUTF()));
            
            //DataOutputStream out = new DataOutputStream(server.getOutputStream());
            //out.writeUTF("Connection to " + server.getLocalSocketAddress() + " Ended\nGoodbye!");
            server.close();
            //encrypt("you");
         }
         catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            //break;
         }
         catch(IOException e) {
            e.printStackTrace();
            //break;
         } catch(Exception e) {
         	e.printStackTrace();
      	}
         
      
      catch(IOException e) {
         e.printStackTrace();
      }
   }
}
