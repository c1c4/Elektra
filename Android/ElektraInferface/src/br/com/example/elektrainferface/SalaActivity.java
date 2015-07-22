package br.com.example.elektrainferface;

import DAO.ComodoComponenteDAO;
import Service.AsyncResponse;
import Service.ConexaoWS;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

public class SalaActivity extends Activity implements AsyncResponse,
		OnClickListener {

	Button voltar;
	ConexaoWS conexao;
	ToggleButton ligarLampada;
	ToggleButton LigarSensorMovimento;
	ToggleButton LigarSensorLuminosidade;
	ComodoComponenteDAO comodoComponente;
	int id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sala);
		comodoComponente = ComodoComponenteDAO.getInstance(this);

		ligarLampada = (ToggleButton) findViewById(R.id.btLampadaSala);
		ligarLampada.setOnClickListener(this);
		LigarSensorMovimento = (ToggleButton) findViewById(R.id.btSensorMovimento);
		LigarSensorMovimento.setOnClickListener(this);

		voltar = (Button) findViewById(R.id.btVoltarSala);

		voltar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {

				finish();
			}

		});

		if (comodoComponente.buscarStatus(2).equals("1")) {

			ligarLampada.setChecked(true);

		} else {
			ligarLampada.setChecked(false);
		}

		if (comodoComponente.buscarStatus(6).equals("1")) {
			
			LigarSensorMovimento.setChecked(true);

		} else {
			LigarSensorMovimento.setChecked(false);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btLampadaSala:
			if (ligarLampada.isChecked()) {

				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "5", "porta", "9");
				id = 5;

			
			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "5", "porta", "10");
				
				id= 5;
			}
			break;

		case R.id.btSensorMovimento:

			if (LigarSensorMovimento.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "6", "porta", "13");
				id=6;
				
			} else {

				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "6", "porta", "14");
				
				id=6;
			}
			break;

		default:
			break;

		}

	}

	@Override
	public void processoFinalizado(String retorno) {
		// TODO Auto-generated method stub
		if(retorno.equals("Falha")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Sem Internet");
			builder.setMessage("Verfique sua conexão com internet!");
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// ação do botão "sim"
						}
					});
			builder.show();
		}
		else {

			comodoComponente.alterarStatus(id, Integer.parseInt(retorno));
			id = 0;
		
		}

	}
}
