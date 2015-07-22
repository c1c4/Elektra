package com.Arduino;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import dao.DAOComodoComponente;

public class AtivarComponentes{
	
	public int ligarDesligarComponentes(String idComodoComponente, String porta) {
		/*
		 * Aqui vai o código ou método para chamar o arduino
		 * retorno do status se for ligado = 1 se for desligado = 0;
		 * int status = resposta arduino;
		 */
		String resp= null;
		
		
		
		
		
		
		try {
			Socket s = new Socket();
			s.connect(new InetSocketAddress("192.168.1.105", 81));

			System.out.println("Connected to: 192.168.1.105 on port: 81");

			DataOutputStream outToServer = new DataOutputStream(
					s.getOutputStream());

			BufferedReader inFromServer = new BufferedReader(
					new InputStreamReader(s.getInputStream()));

			outToServer.writeInt(Integer.parseInt(porta));
			resp = inFromServer.readLine();
			//System.out.println(resp);

			s.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		int status = 1;
		
		if (resp.equals("ok")) {
			status = 1;
		}
		else
		{
			status = 0;
		}
				
		int id = Integer.parseInt(idComodoComponente);
		int portas = Integer.parseInt(porta);
		int sucesso = 0;
		
		DAOComodoComponente cc = new DAOComodoComponente();
		int statusBD = cc.recuperaStatus(id, portas);
		
		if(statusBD != status){
			sucesso = cc.updateStatus(id, status);
		}
		
		if(sucesso == 1){
			return status;
		}else {
			return statusBD;
		}
	}

}
