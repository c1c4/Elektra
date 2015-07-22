package sqlite;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "BancoElektra.db";

	public static final String TBL_COMODO = "comodo";
	public static final String TBL_COMODOCOMPONENTE = "comodoComponente";
	public static final String TBL_COMPONENTE = "componente";


	public static final String CREATE_COMODO = "create table if not exists comodo"
			+ " ( _idcomodo INT, "
			+ " descricao VARCHAR not null )";
	public static final String CREATE_COMPONENTE = "create table if not exists componente "
			+ " ( _idcomponente INT,"
			+ " descricao VARCHAR not null )";
	public static final String CREATE_COMODOCOMPONENTE = "create table if not exists comodoComponente "
			+ " ( _idcomodo_componente integer, "
			+ " idcomodo INT not null, "
			+ " idcomponente INT not null,"
			+ " status INT not null,"
			+ " sensor INT,"
			+ " temporizador INT,"
			+ " data_inicial DATETIME,"
			+ " data_final DATETIME,"
			+  "porta INT not null )";
	   


	public BD(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		database.execSQL(CREATE_COMODO);
		database.execSQL(CREATE_COMPONENTE);
		database.execSQL(CREATE_COMODOCOMPONENTE);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE comodo");
		db.execSQL("DROP TABLE componente");
		db.execSQL("DROP TABLE comodoComponente");
		onCreate(db);
	}
}


	 
	
	  
	
	