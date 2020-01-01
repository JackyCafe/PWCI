package tw.com.ian.pwci.Interface;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import tw.com.ian.pwci.DAO.DBHelp;

public abstract class DAO {
    protected SQLiteDatabase db;
    private Context context;

    public DAO(Context context)
    {
        db = DBHelp.getDatabase(context);
        this.context = context;
    }
}
