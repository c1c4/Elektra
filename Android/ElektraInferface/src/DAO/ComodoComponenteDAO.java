package DAO;




import sqlite.BD;
import sqlite.BDComodoComponente;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import bean.Comodo;
import bean.ComodoComponente;

public class ComodoComponenteDAO {

	public SQLiteDatabase db;
	public static ComodoComponenteDAO instance = new ComodoComponenteDAO();

	public static ComodoComponenteDAO getInstance(Context contexto) {
		if (instance.db == null || instance.db.isOpen()) {
			instance.db = new BD(contexto).getWritableDatabase();
		}

		return instance;
	}

	public void incluir(ComodoComponente c) {
		

		db.beginTransaction();

		try {
			ContentValues cv = new ContentValues();				
		

			
			cv.put("_idcomodo_componente", c.getIdComodoComponente());
			cv.put("idcomodo", c.getIdcomodo());
			cv.put("idcomponente", c.getIdcomponente());
			cv.put("status", c.getStatus());
			cv.put("sensor", c.getSensor());
			cv.put("temporizador", c.getTemporizador());
			cv.put("data_inicial", (c.getDataInicial() == null ? null : c.getDataInicial().toString()));
			cv.put("data_final", (c.getDataFinal() == null ? null : c.getDataFinal().toString()));
			cv.put("porta", c.getPorta());
			
			db.insert(BD.TBL_COMODOCOMPONENTE, null, cv);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		
	}
	
	public String buscar(int id)	
	{
		Cursor cursor = db.query(BD.TBL_COMODOCOMPONENTE, null, "_idcomodo_componente=?", new String[]{String.valueOf(id)}, null, null, null);
		if(cursor.getCount() > 0)
		{
		cursor.moveToFirst();
		return "Encontro";
		}
		
		return "Nada";
	}
	public String buscarStatus(int id)	
	{
		Cursor cursor = db.query(BD.TBL_COMODOCOMPONENTE, null, "idcomponente=?", new String[]{String.valueOf(id)}, null, null, null);
		if(cursor.getCount() > 0)
		{
		cursor.moveToFirst();
		return carregarStatus(cursor);
		}
		return null;
		
	}
	private String carregarStatus(Cursor c) {
		
		String Status = c.getString(c.getColumnIndex("status"));


		
		
		return Status;
	}

	public void alterar(ComodoComponente c) {
		

		db.beginTransaction();

		try {
			ContentValues cv = new ContentValues();
				
			cv.put("_idcomodo_componente", c.getIdComodoComponente());
			cv.put("idcomodo", c.getIdcomodo());
			cv.put("idcomponente", c.getIdcomponente());
			cv.put("status", c.getStatus());
			cv.put("sensor", c.getSensor());
			cv.put("temporizador", c.getTemporizador());
			cv.put("data_inicial", (c.getDataInicial() == null ? null : c.getDataInicial().toString()));
			cv.put("data_final", (c.getDataFinal() == null ? null : c.getDataFinal().toString()));
			cv.put("porta", c.getPorta());
			
			db.update(BD.TBL_COMODOCOMPONENTE,cv, "_idcomodo_componente=?", new String[]{String.valueOf(c.getIdComodoComponente())});
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
	
public void alterarStatus(int id, int status) {
		

		db.beginTransaction();

		try {
			ContentValues cv = new ContentValues();
				
			
			cv.put("status", status);
			
			
			db.update(BD.TBL_COMODOCOMPONENTE,cv, "_idcomodo_componente=?", new String[]{String.valueOf(id)});
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
}

	

