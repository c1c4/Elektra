package Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Envia_Arduino {

	public String EnviaARd(int msg) {
		String resp = null;
		
		try {
			Socket s = new Socket();
			s.connect(new InetSocketAddress("192.168.1.105", 81));

			// System.out.println("Connected to: 192.168.7.105 on port: 80");

			DataOutputStream outToServer = new DataOutputStream(
					s.getOutputStream());

			BufferedReader inFromServer = new BufferedReader(
					new InputStreamReader(s.getInputStream()));

			outToServer.writeInt(msg);
			resp = inFromServer.toString();
			// System.out.println(resp);

			
			s.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return resp;
	}
}
