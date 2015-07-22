package com.Arduino;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import beans.NivelAcesso;

import com.Atualizacao.RecuperarObjetos;
import com.Login.ServicoLogin;

public class TesteArduino {

	public static void main(String[] args) throws IOException, Exception {
		AtivarComponentes sL = new AtivarComponentes();
		
		int i = sL.ligarDesligarComponentes("1", "2");
		
		System.out.println(i);
	}
}

