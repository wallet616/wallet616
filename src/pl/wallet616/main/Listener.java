package pl.wallet616.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Listener {
	
	private static Socket socket;
	private static Boolean repeat = false;
	 
    public static boolean run(String command)
    {
        try
        {
            String host = "wallet616.pl";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
 
            // Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(Encode.encode(DataRead.readData(0) + ":" + command) + "\n");
            bw.flush();
            
            // Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            if ((Decode.decode(message)).equals("VaildSucces")) {
            	repeat = true;
            } else {
            	repeat = false;
            }
            System.out.println("Message received from the server : " +Decode.decode(message));
        }
        catch (Exception e)
        {
        	repeat = false;
        }
        finally
        {
            // Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
		return repeat;
    }

}
