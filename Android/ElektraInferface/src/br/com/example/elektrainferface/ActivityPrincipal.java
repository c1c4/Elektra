package br.com.example.elektrainferface;

import java.util.Iterator;
import java.util.List;

import DAO.ComodoDAO;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import bean.Comodo;

public class ActivityPrincipal extends Activity implements OnClickListener {

	public int idcomponente;

	ComodoDAO comodoDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_principal);

		((Button) findViewById(R.id.btnQuarto)).setEnabled(false);
		((Button) findViewById(R.id.btnSala)).setEnabled(false);
		((Button) findViewById(R.id.btnCozinha)).setEnabled(false);
		((Button) findViewById(R.id.btnBanheiro)).setEnabled(false);
		((Button) findViewById(R.id.btnQuintal)).setEnabled(false);
		((Button) findViewById(R.id.btnSair)).setOnClickListener(this);

		comodoDAO = ComodoDAO.getInstance(this);
		listarComodo();

	}

	private void listarComodo() {
		List<Comodo> lista = comodoDAO.listar();
		Iterator i = lista.iterator();
		while (i.hasNext()) {
			Comodo comodo = (Comodo) i.next();
			int codigo = comodo.getIdComodo();
			String descricao = comodo.getDescricao();

			switch (descricao) {
			case "Area":
				Resources res = getResources();
				Drawable drawable = res.getDrawable(R.drawable.btnquintal);
			    ((Button) findViewById(R.id.btnQuintal)).setBackgroundDrawable(drawable);
				
				((Button) findViewById(R.id.btnQuintal)).setEnabled(true);
				((Button) findViewById(R.id.btnQuintal)).setOnClickListener(this);
				
				break;
			case "Sala":
				
				Resources res1 = getResources();
				Drawable drawable1 = res1.getDrawable(R.drawable.btnsofa);
			    ((Button) findViewById(R.id.btnSala)).setBackgroundDrawable(drawable1);
				
			    
				((Button) findViewById(R.id.btnSala)).setEnabled(true);
				((Button) findViewById(R.id.btnSala)).setOnClickListener(this);
				break;
			case "Quarto":
				Resources res2 = getResources();
				Drawable drawable2 = res2.getDrawable(R.drawable.btnquarto);
			    ((Button) findViewById(R.id.btnQuarto)).setBackgroundDrawable(drawable2);
				
			    
				((Button) findViewById(R.id.btnQuarto)).setEnabled(true);
				((Button) findViewById(R.id.btnQuarto))
						.setOnClickListener(this);
				break;
			case "Cozinha":
				Resources res3 = getResources();
				Drawable drawable3 = res3.getDrawable(R.drawable.btncozinha);
			    ((Button) findViewById(R.id.btnCozinha)).setBackgroundDrawable(drawable3);
				
				((Button) findViewById(R.id.btnCozinha)).setEnabled(true);
				((Button) findViewById(R.id.btnCozinha))
						.setOnClickListener(this);
				break;
			case "Banheiro":
				
				Resources res4 = getResources();
				Drawable drawable4 = res4.getDrawable(R.drawable.chuveiro);
			    ((Button) findViewById(R.id.btnBanheiro)).setBackgroundDrawable(drawable4);
				
				((Button) findViewById(R.id.btnBanheiro)).setEnabled(true);
				((Button) findViewById(R.id.btnBanheiro))
						.setOnClickListener(this);
				break;

			default:
				break;
			}

		}
	}

	public void onClick(View v) {

		if (v == findViewById(R.id.btnQuarto)) {
			Intent intentQuarto = new Intent(this, QuartoActivity.class);
			startActivity(intentQuarto);
		}
		if (v == findViewById(R.id.btnSala)) {
			Intent intentSala = new Intent(this, SalaActivity.class);
			startActivity(intentSala);
		}
		if (v == findViewById(R.id.btnCozinha)) {
			Intent intentCozinha = new Intent(this, CozinhaActivity.class);
			startActivity(intentCozinha);
		}
		if (v == findViewById(R.id.btnBanheiro)) {
			Intent intentBanheiro = new Intent(this, BanheiroActivity.class);
			startActivity(intentBanheiro);
		}
		if (v == findViewById(R.id.btnQuintal)) {
			Intent intentQuintal = new Intent(this, AreaActivity.class);
			startActivity(intentQuintal);
		}
		if (v == findViewById(R.id.btnSair)) {
			finish();
		}

	}
}
