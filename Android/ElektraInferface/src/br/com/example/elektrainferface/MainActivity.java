package br.com.example.elektrainferface;

import Service.AsyncResponse;
import Service.ConexaoWS;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,
		AsyncResponse {

	ConexaoWS conexao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button) findViewById(R.id.btnEntrar)).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText senha = ((EditText) findViewById(R.id.etSenha));
		EditText nome = ((EditText) findViewById(R.id.etNome));

		if (nome.length() == 0 && senha.length() == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Acesso Negado");
			builder.setMessage("Digite o login e a sua senha!");
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// ação do botão "sim"
						}
					});
			builder.show();

		} else {
			conexao = new ConexaoWS();
			conexao.delegate = this;
			conexao.execute("http://Login.com", "verificaUsuario",
					"ServicoLogin?wsdl", "http://Login.com/verificaUsuario",
					"login", nome.getText().toString(), "senha", senha
							.getText().toString());
		}
	}

	@Override
	public void processoFinalizado(String retorno) {
		// TODO Auto-generated method stub
		if (retorno.equals("0")) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Acesso Negado");
			builder.setMessage("Login ou Senha invalido!");
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// ação do botão "sim"
						}
					});
			builder.show();

		} else if (retorno.equals("ok")) {
			Toast.makeText(getApplicationContext(), "Bem Vindo",Toast.LENGTH_SHORT).show();
			Intent intentTela2 = new Intent(this, ActivityPrincipal.class);

			startActivity(intentTela2);
		} else if (retorno.equals("Falhou")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Acesso Negado");
			builder.setMessage("Não existe itens para esse usuário!");
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// ação do botão "sim"
						}
					});
			builder.show();
		} else if(retorno.equals("Falha")) {
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

			conexao = new ConexaoWS();
			conexao.delegate = this;
			conexao.cnt = this;
			conexao.idusuario = Integer.parseInt(retorno);
			conexao.execute("http://Atualizacao.com", "recuperaUsuario",
					"RecuperarObjetos?wsdl",
					"http://Atualizacao.com/recuperaUsuario", "idUsuario",
					retorno);
		
		}
	}
}
