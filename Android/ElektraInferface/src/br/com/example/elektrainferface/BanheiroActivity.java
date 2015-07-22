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

public class BanheiroActivity extends Activity implements AsyncResponse,
		OnClickListener {

	ToggleButton ligarLampada;
	Button voltar;

	ConexaoWS conexao;

	ComodoComponenteDAO comodoComponente;

	int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banheiro);
		comodoComponente = ComodoComponenteDAO.getInstance(this);

		voltar = (Button) findViewById(R.id.btVoltarBanheiro);

		ligarLampada = (ToggleButton) findViewById(R.id.btLampadaBanheiro);
		ligarLampada.setOnClickListener(this);

		voltar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});

		// Verifica Status do Led
		if (comodoComponente.buscarStatus(5).equals("1")) {

			ligarLampada.setChecked(true);
		} else {
			ligarLampada.setChecked(false);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btLampadaBanheiro:

			if (ligarLampada.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "10", "porta", "3");

				id = 10;
			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "10", "porta", "4");

				id = 10;
			}
			break;

		default:
			break;

		}

	}

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
