package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDComponente  extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "BancoElektra.db";


	public static final String TBL_COMPONENTE = "componente";


	public static final String CREATE_COMPONENTE = "create table if not exists componente "
			+ " ( _idcomponente integer,"
			+ " descricao text not null )";
	
	

	public BDComponente(Context context) {
		super(context, DATABASE_NAME, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		database.execSQL(CREATE_COMPONENTE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE componente");
		onCreate(db);
	}
	

}
