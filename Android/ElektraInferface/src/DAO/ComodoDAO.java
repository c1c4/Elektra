package DAO;


import java.util.ArrayList;
import java.util.List;


import sqlite.BD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import bean.Comodo;

public class ComodoDAO {

	public SQLiteDatabase db;
	public static ComodoDAO instance = new ComodoDAO();

	public static ComodoDAO getInstance(Context contexto) {
		if (instance.db == null || instance.db.isOpen()) {
			instance.db = new BD(contexto).getWritableDatabase();
		}

		return instance;
	}


	public void incluir(Comodo c) {
		

		db.beginTransaction();

		try {
			ContentValues cv = new ContentValues();
				
			cv.put("_idcomodo", c.getIdComodo());
			
			cv.put("descricao", c.getDescricao());

			db.insert(BD.TBL_COMODO, null, cv);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		
	}
	public String buscar(int id)	
	{
		Cursor cursor = db.query(BD.TBL_COMODO, null, "_idcomodo=?", new String[]{String.valueOf(id)}, null, null, null);
		if(cursor.getCount() > 0)
		{
		cursor.moveToFirst();
		return "Encontro";
		}
		
		return "Nada";
	}

	public void alterar(Comodo c) {
		

		db.beginTransaction();

		try {
			ContentValues cv = new ContentValues();
				
			cv.put("_idcomodo", c.getIdComodo());
			cv.put("descricao", c.getDescricao());
			
			db.update(BD.TBL_COMODO,cv, "_idcomodo=?", new String[]{String.valueOf(c.getIdComodo())});
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		
	}
	

	
	public List<Comodo> listar() {
		List<Comodo> lista = new ArrayList<Comodo>();

		Cursor cursor = db.query(BD.TBL_COMODO, null, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Comodo cmodo = carregarComodo(cursor);
			lista.add(cmodo);
			cursor.moveToNext();
		}

		return lista;
	}
	
	private Comodo carregarComodo(Cursor c) {
		int codigo = c.getInt(c.getColumnIndex("_idcomodo"));
		String descricao = c.getString(c.getColumnIndex("descricao"));
		Comodo comodo = new Comodo(codigo,descricao);
		
		return comodo;
	}

		
	public void excluir() {
		db.beginTransaction();

		try {
			db.delete(BD.TBL_COMODO,null,null);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
}        


	

