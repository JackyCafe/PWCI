package tw.com.ian.pwci.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelp extends SQLiteOpenHelper {
    static SQLiteDatabase database;
    static  String DB_NAME="pwci.db";
    static int VERSION = 1;

    public DBHelp(@Nullable Context context, @Nullable String DB_NAME, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);
    }

    /*取得資料庫*/
    public static SQLiteDatabase getDatabase(Context context)
    {
        /*return true if database is current open*/
        if (database == null ||!database.isOpen()){
            database = new DBHelp(context,DB_NAME,null,VERSION).getWritableDatabase();
        }
        return database;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserProfileDAO.CREATE_TABLE);
        db.execSQL(QuizDAO.CREATE_TABLE);
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
