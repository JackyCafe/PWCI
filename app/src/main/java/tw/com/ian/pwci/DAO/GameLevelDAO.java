package tw.com.ian.pwci.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.pwci.Interface.IDAO;
import tw.com.ian.pwci.Object.GameLevel;


public class GameLevelDAO extends IDAO<GameLevel> {
    static String TABLE_NAME = "GAME_LEVEL";  //遊戲進度
    static String id_column = "_id";
    static String stage_id_column = "stage_id";// 題目
    static String topic_column = "topic";
    static String color_column = "color";

    static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            id_column + " Integer primary key autoincrement, " +
            stage_id_column + " integer," +
            topic_column + " text ," +
            color_column + " integer" +
            " )";



    @Override
    public GameLevel insert(GameLevel gameLevel) {

        ContentValues cv = new ContentValues();
        cv.put(stage_id_column,gameLevel.getStage_id()); //
        cv.put(topic_column,gameLevel.getTopic());  //
        cv.put(color_column,gameLevel.getColor());  //

        long id = db.insert(TABLE_NAME,null,cv);
        gameLevel.setId(id);
         return gameLevel;
    }

    public GameLevelDAO(Context context){
        super(context);
    }
    @Override
    public boolean delete(long id) {
        String where = id_column+"=? ";
        String[] whereArg = new String[1] ; whereArg[0] = String.valueOf(id) ;
        return db.delete(TABLE_NAME,where,whereArg) >0;
    }

    @Override
    public GameLevel update(GameLevel gameLevel) {
        ContentValues cv = new ContentValues();
        cv.put(topic_column,gameLevel.getTopic());  //
        cv.put(stage_id_column,gameLevel.getStage_id()); //
        cv.put(topic_column,gameLevel.getTopic()); //
        cv.put(color_column,gameLevel.getColor());  //

        String where = id_column + " = ? ";
        String[] whereArg = new String[1] ; whereArg[0] = String.valueOf(gameLevel.getId()) ;
        db.update(TABLE_NAME, cv, where, whereArg);
        return gameLevel;
    }

    @Override
    public List<GameLevel> getAll() {
        List<GameLevel> result = new ArrayList<>();
        Cursor c  = db.query(TABLE_NAME,null,null,null,null,null,null);
        while (c.moveToNext())
        {
            result.add(getRecord(c));
        }
           return result;
    }

    @Override
    public GameLevel getRecord(Cursor c) {
        GameLevel gl = new GameLevel();
        gl.setId(c.getLong(0));
        gl.setStage_id(c.getInt(1));
        gl.setTopic(c.getString(2));
        gl.setColor(c.getInt(3));
        return gl;
    }

    public GameLevel getFirstGame(){
        return getAll().get(0);
    }

    public List<GameLevel> getStageLevel(int stage_id){
        final List<GameLevel> result = new ArrayList<>();
        String where = stage_id_column +"=?";
        String[] wheraArg= new String[1];wheraArg[0] = Integer.toString(stage_id);
        Cursor c = db.query(TABLE_NAME,null,where,wheraArg,null,null,null);
        while (c.moveToNext()){
            result.add(getRecord(c));
        }
        return result;
    }
}
