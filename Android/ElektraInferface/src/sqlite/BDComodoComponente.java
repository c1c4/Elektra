package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDComodoComponente extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "BancoElektra.db";


	public static final String TBL_COMODOCOMPONENTE = "comodoComponnte";
	
	
	
	public static final String CREATE_COMODOCOMPONENTE = "create table if not exists comodoComponente "
			+ " ( _idcomodo_componente integer, "
			+ " idcomodo integer not null, "
			+ " idcomponente integer not null,"
			+ " status integer not null,"
			+ " sensor integer,"
			+ " temporizador integer,"
			+ " data_inicial data not null,"
			+ " data_final data not null,"
			+  "porta int not null )";
	   

	public BDComodoComponente(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		database.execSQL(CREATE_COMODOCOMPONENTE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE comodoComponente");
		onCreate(db);
	}
	
}
