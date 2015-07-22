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

public class QuartoActivity extends Activity implements AsyncResponse,
		OnClickListener {

	Button voltar;
	ToggleButton ligarLampada;
	ToggleButton LigarVentilador;
	
	ConexaoWS conexao;
	
	ComodoComponenteDAO comodoComponente;
	
	int id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quarto);
		comodoComponente = ComodoComponenteDAO.getInstance(this);

		ligarLampada = (ToggleButton) findViewById(R.id.btLampadaQuarto);
		ligarLampada.setOnClickListener(this);
		LigarVentilador = (ToggleButton) findViewById(R.id.btVentilador);
		LigarVentilador.setOnClickListener(this);
		voltar = (Button) findViewById(R.id.btVoltarQuarto);

		voltar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {

				finish();
			}

		});

		// Status LED Quarto
		if (comodoComponente.buscarStatus(3).equals("1")) {
			ligarLampada.setChecked(true);

		} else {
			ligarLampada.setChecked(false);
		}

		if (comodoComponente.buscarStatus(10).equals("1")) {
			// esta ativado
			LigarVentilador.setChecked(true);

		} else {
			LigarVentilador.setChecked(false);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btLampadaQuarto:
			if (ligarLampada.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "7", "porta", "7");
				id = 7;

			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "7", "porta", "8");

				id = 7;
			}
			break;

		case R.id.btVentilador:
			if (LigarVentilador.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "8", "porta", "16");
				id = 8;

			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "8", "porta", "17");
				id = 8;


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
