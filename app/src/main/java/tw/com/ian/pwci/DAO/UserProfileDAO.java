package tw.com.ian.pwci.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.pwci.Object.Userprofile;

public class UserProfileDAO   {

    static String TABLE_NAME = "USER_PROFILE";  //table 名稱
    /*欄位*/
    static String id_column = "_id";
    static String user_column ="user";
    static String card_id_column = "card_id";
    static String name_column = "name";
    static String birthday_column ="birthday";
    static String blood_column ="blood";
    static String address_column ="address";
    static String tel_column = "tel";
    static String mobile_column ="mobile";
    static String contact_column ="contact";
    static String contact_tel_column ="contact_tel";
    static String contact_relation_column ="contact_relation";
    static String first_career_column="first_career";
    static String last_career_column ="last_career";
    static String room_column="room" ;
    static String isStaff_column="isStaff" ;

    private SQLiteDatabase db;
    //create SQL語法
    static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            id_column + " Integer primary key autoincrement, " +
            user_column + " text," +
            card_id_column + " text," +
            name_column + " text," +
            birthday_column + " text," +
            blood_column + " text," +
            address_column + " text," +
            tel_column + " text," +
            mobile_column + " text," +
            contact_column + " text," +
            contact_tel_column + " text," +
            contact_relation_column + " text," +
            first_career_column + " text," +
            last_career_column + " text," +
            room_column + " text," +
            isStaff_column + " text " +
            " )";


    public UserProfileDAO(Context context)
    {
        db = DBHelp.getDatabase(context);
    }

    /**
     *
     * @param u  UserProfile 物件
     * @return Insert 後的UserProfile 物件
     */
    public Userprofile insert(Userprofile u )
    {

         ContentValues cv = new ContentValues();
//        cv.put(id_column ,u.getId() ) ;
        cv.put(user_column ,u.getUser());
        cv.put(card_id_column ,u.getCard_id()) ;
        cv.put(name_column , u.getName()) ;
        cv.put(birthday_column , u.getBirthday());
        cv.put(blood_column ,u.getBlood());
        cv.put(address_column ,u.getAddress());
        cv.put(tel_column , u.getTel());
        cv.put(mobile_column ,u.getMobile());
        cv.put(contact_column , u.getContact());
        cv.put(contact_tel_column ,u.getContact_tel());
        cv.put(contact_relation_column , u.getContact_relation());
        cv.put(first_career_column, u.getFirst_career());
        cv.put(last_career_column , u.getLast_career());
        cv.put(room_column, u.getRoom() );
        cv.put(isStaff_column,u.getIsStaff()) ;
        long id  = db.insert(TABLE_NAME,null,cv);
        return u;
    }

    /**
     *
     * @param id 要刪除的資料序號
     * @return 是否成功刪除
     */
    public boolean delete(long id)
    {
        String where = id_column + " = "+id;
        return    db.delete(TABLE_NAME,where,null)>0;

    }

    /**
        *  尋訪資料庫所有資料後，將資料以List 的方式傳回
        * @return
        */
    public List<Userprofile> getAll()
    {
        List<Userprofile> userprofiles = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        while(c.moveToNext())
        {
             userprofiles.add(getRecords(c));
        }
        return  userprofiles;

    }

    public Userprofile update(Userprofile u) {

        return u;
    }
    private Userprofile getRecords(Cursor c) {
        Userprofile u = new Userprofile();
        u.setId(c.getLong(0));
        u.setUser(c.getString(c.getColumnIndex(user_column)));
        u.setCard_id(c.getString(c.getColumnIndex(card_id_column)));
        u.setName(c.getString(c.getColumnIndex(name_column)));
        u.setBirthday(c.getString(c.getColumnIndex(birthday_column)));
        u.setBlood(c.getString(c.getColumnIndex(blood_column)));
        u.setAddress(c.getString(c.getColumnIndex(address_column)));
        u.setTel(c.getString(c.getColumnIndex(tel_column)));
        u.setMobile(c.getString(c.getColumnIndex(mobile_column)));
        u.setContact(c.getString(c.getColumnIndex(contact_column)));
        u.setContact_tel(c.getString(c.getColumnIndex(contact_tel_column)));
        u.setContact_relation(c.getString(c.getColumnIndex(contact_relation_column)));
        u.setFirst_career(c.getString(c.getColumnIndex(first_career_column)));
        u.setLast_career(c.getString(c.getColumnIndex(last_career_column)));
        u.setRoom(c.getString(c.getColumnIndex(room_column)));
        u.setIsStaff(c.getString(c.getColumnIndex(isStaff_column)));
        return u;
    }
}
