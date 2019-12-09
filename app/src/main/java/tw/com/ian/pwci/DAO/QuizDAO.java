package tw.com.ian.pwci.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.pwci.Object.Quiz;


public class QuizDAO {
    public static String TABLE_NAME= "Quiz";
    static String id_column = "_id";
    static String question_column ="question";
    private SQLiteDatabase db;

    static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            id_column + " Integer primary key autoincrement, " +
            question_column + " text " +
            " )";

    public QuizDAO(Context context) {
        db = DBHelp.getDatabase(context);
    }

    public Quiz insert(Quiz q)
    {
        ContentValues cv = new ContentValues();
        cv.put(question_column,q.getQuestion());
        long id  = db.insert(TABLE_NAME,null,cv);
        q.setId(id);
        return q;
    }

    public boolean delete(long id)
    {
        String where = id_column + " = "+id;
        return    db.delete(TABLE_NAME,where,null)>0;

    }

    public Quiz get(int id){
        Quiz q = new Quiz();
        String sql = "Select * from " + TABLE_NAME + " Where _id= "+id+" ";
        Cursor c = db.rawQuery(sql ,null );
        c.moveToFirst();
        return  getRecords(c);
    }

    public List<Quiz> getAll()
    {
        List<Quiz> quizzes = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        while(c.moveToNext())
        {
            quizzes.add(getRecords(c));
        }
        return  quizzes;

    }

    private Quiz getRecords(Cursor c) {
        Quiz q = new Quiz();
        q.setId(c.getLong(c.getColumnIndex(id_column)));
        q.setQuestion(c.getString(c.getColumnIndex(question_column)));
        return  q;
    }

}
