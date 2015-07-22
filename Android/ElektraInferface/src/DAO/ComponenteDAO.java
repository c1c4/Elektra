package DAO;



import sqlite.BD;
import sqlite.BDComponente;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import bean.Comodo;
import bean.Componente;

public class ComponenteDAO {

	public SQLiteDatabase db;
	public static ComponenteDAO instance = new ComponenteDAO();

	public static ComponenteDAO getInstance(Context contexto) {
		if (instance.db == null || instance.db.isOpen()) {
			instance.db = new BD(contexto).getWritableDatabase();
		}

		return instance;
	}

	public void incluir(Componente c) {
		

		db.beginTransaction();

		try {
			ContentValues cv = new ContentValues();
				
			cv.put("_idcomponente", c.getIdComponente());
			cv.put("descricao", c.getDescricao());

			db.insert(BD.TBL_COMPONENTE, null, cv);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		
	}
	public String buscar(int id)	
	{
		Cursor cursor = db.query(BD.TBL_COMPONENTE, null, "_idcomponente=?", new String[]{String.valueOf(id)}, null, null, null);
		if(cursor.getCount() > 0)
		{
		cursor.moveToFirst();
		return "Encontro";
		
		}
		
		return "Nada";
	}
	
	

	public void alterar(Componente c) {
		

		db.beginTransaction();

		try {
			ContentValues cv = new ContentValues();
				
			
			cv.put("_idcomponente", c.getIdComponente());
			cv.put("descricao", c.getDescricao());
			
			db.update(BD.TBL_COMPONENTE,cv, "_idcomponente=?", new String[]{String.valueOf(c.getIdComponente())});			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
}
