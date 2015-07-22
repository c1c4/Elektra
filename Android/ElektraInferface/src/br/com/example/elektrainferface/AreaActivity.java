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

public class AreaActivity extends Activity implements AsyncResponse,
		OnClickListener {

	ToggleButton ligarLampada;
	ToggleButton ligarSensorTemp;
	ToggleButton ligarSensorChuva;
	ToggleButton LigarSensorLuminosidade;
	Button voltar;

	ComodoComponenteDAO comodoComponente;

	ConexaoWS conexao;

	int id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area);

		ligarLampada = (ToggleButton) findViewById(R.id.btLampadaArea);
		ligarLampada.setOnClickListener(this);
		ligarSensorTemp = (ToggleButton) findViewById(R.id.btSensorTemperatura);
		ligarSensorTemp.setOnClickListener(this);
		ligarSensorChuva = (ToggleButton) findViewById(R.id.btSensorChuva);
		ligarSensorChuva.setOnClickListener(this);
		LigarSensorLuminosidade = (ToggleButton) findViewById(R.id.btSensorLuminosidade);
		LigarSensorLuminosidade.setOnClickListener(this);

		comodoComponente = ComodoComponenteDAO.getInstance(this);

		voltar = (Button) findViewById(R.id.btVoltarArea);
		voltar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {

				finish();
			}

		});

		comodoComponente = ComodoComponenteDAO.getInstance(this);

		// verifica luz
		if (comodoComponente.buscarStatus(1).equals("1")) {
			ligarLampada.setChecked(true);

		} else {
			ligarLampada.setChecked(false);
		}

		// verifica Sensor temperatura
		if (comodoComponente.buscarStatus(7).equals("1")) {
			ligarSensorTemp.setChecked(true);

		} else {

			ligarSensorTemp.setChecked(false);
		}

		// verifica Sensor Chuva
		if (comodoComponente.buscarStatus(8).equals("1")) {

			ligarSensorChuva.setChecked(true);

		} else {
			ligarSensorChuva.setChecked(false);
		}

		// Verifica Sensor Luminosidade
		if (comodoComponente.buscarStatus(9).equals("1")) {

			LigarSensorLuminosidade.setChecked(true);
			ligarLampada.setEnabled(false);
			ligarLampada.setChecked(false);
			DesligarLed();
		} else {
			LigarSensorLuminosidade.setChecked(false);
			ligarLampada.setEnabled(true);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btLampadaArea:
			if (ligarLampada.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "1", "porta", "1");

				id = 1;

			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "1", "porta", "2");

				id = 1;
			}
			break;

		case R.id.btSensorTemperatura:

			if (ligarSensorTemp.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "2", "porta", "20");
				id = 2;

			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "2", "porta", "21");
				id = 2;
			}

			break;

		case R.id.btSensorChuva:

			if (ligarSensorChuva.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "3", "porta", "18");
				id = 3;

			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "3", "porta", "19");
				id = 3;
			}

			break;

		case R.id.btSensorLuminosidade:
			if (LigarSensorLuminosidade.isChecked()) {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "4", "porta", "11");

				
				DesligarLed();
				ligarLampada.setEnabled(false);
				
				ligarLampada.setChecked(false);
				id = 4;

			} else {
				conexao = new ConexaoWS();
				conexao.delegate = this;
				conexao.execute("http://Arduino.com",
						"ligarDesligarComponentes", "AtivarComponentes?wsdl",
						"http://Arduino.com/ligarDesligarComponentes",
						"idComodoComponente", "4", "porta", "12");
				ligarLampada.setEnabled(true);
				id = 4;
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

	public void DesligarLed() {
		conexao = new ConexaoWS();
		conexao.delegate = this;
		conexao.execute("http://Arduino.com", "ligarDesligarComponentes",
				"AtivarComponentes?wsdl",
				"http://Arduino.com/ligarDesligarComponentes",
				"idComodoComponente", "1", "porta", "2");

	}
}
