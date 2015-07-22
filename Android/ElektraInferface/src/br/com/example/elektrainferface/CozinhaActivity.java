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

public class CozinhaActivity extends Activity implements AsyncResponse,
		OnClickListener {

	ToggleButton ligarLampada;
	Button voltar;
	
	ConexaoWS conexao;
	
	int id;
	
	ComodoComponenteDAO comodoComponente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cozinha);
		comodoComponente = ComodoComponenteDAO.getInstance(this);

		ligarLampada = (ToggleButton) findViewById(R.id.btLampadaCozinha);
		ligarLampada.setOnClickListener(this);
		
		
		voltar = (Button) findViewById(R.id.btVoltarCozinha);
		voltar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				finish();
			}
		});

		if (comodoComponente.buscarStatus(4).equals("1")) {
			// esta ativado
			ligarLampada.setChecked(true);
		} else {
			ligarLampada.setChecked(false);
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btLampadaCozinha:
			if (ligarLampada.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "9", "porta", "5");

				id = 9;

			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "9", "porta", "6");

				id = 9;
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
