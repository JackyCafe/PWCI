package tw.com.ian.pwci.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.pwci.Interface.IDAO;
import tw.com.ian.pwci.Object.GAME;
import tw.com.ian.pwci.Object.GameLevel;

public class GameDAO extends IDAO<GAME> {
    static String TABLE_NAME = "GAME_PROGESS";  //遊戲進度
    static String id_column = "_id";
    static String topic_column = "topic";// 題目
    static String status_column = "status"; //進度 0-->歸零，1-->完成
    static String postword_column = "postword" ; //過關後的提示字
    static String process_column = "process"; // 目前過到哪一關

    static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            id_column + " Integer primary key autoincrement, " +
            topic_column + " text," +
            status_column + " integer,"+
            postword_column + " text,"+
            process_column + " integer "+
            " )";

    public GameDAO(Context context){
        super(context);
    }

    public GAME insert(GAME game)
    {
        ContentValues cv = new ContentValues();
        cv.put(topic_column,game.getTopic());  //
        cv.put(status_column,game.getStatus()); //
        cv.put(postword_column,game.getPostword()); // 過關了說什麼話
        cv.put(process_column,game.getProcess()); // 目前哪一關再玩
        long id = db.insert(TABLE_NAME,null,cv);
        game.setId(id);
        return game;
    }

    public boolean delete(long id)
    {
        String where = id_column + " = ? ";
        String[] sid = new String[1] ; sid[0] = String.valueOf(id) ;
        return   db.delete(TABLE_NAME,where,sid) > 0;
    }

    public GAME update(GAME game)
    {
        ContentValues cv = new ContentValues();
        cv.put(topic_column,game.getTopic());  //
        cv.put(status_column,game.getStatus()); //
        cv.put(postword_column,game.getPostword()); // 過關了說什麼話
        cv.put(process_column,game.getProcess()); // 目前哪一關再玩
        String where = id_column + " = ? ";
        String[] sid = new String[1] ; sid[0] = String.valueOf(game.getId()) ;
        db.update(TABLE_NAME, cv, where, sid);
        return game;
    }

    public List<GAME> getAll()
    {
        List<GAME> games = new ArrayList<>();
        Cursor c =  db.query(TABLE_NAME,null,null,null,null,null,id_column);
        while (c.moveToNext()) {
            games.add(getRecord(c));
        }
        return games;

    }


    public GAME get(int id)
    {
       return getAll().get(id);
    }

    public  int getSize()
    {
        return getAll().size();
    }

    protected GAME getRecord(Cursor c) {
        GAME game = new GAME();
        game.setId(c.getLong(c.getColumnIndex(id_column)));
        game.setTopic(c.getString(c.getColumnIndex(topic_column)));
        game.setStatus(c.getInt(c.getColumnIndex(status_column)));
        game.setPostword(c.getString(c.getColumnIndex(postword_column)));
        game.setProcess(c.getInt(c.getColumnIndex(process_column)));
        return  game;
    }

    public GAME getFirstGame(){
        return getAll().get(0);
    }


}
