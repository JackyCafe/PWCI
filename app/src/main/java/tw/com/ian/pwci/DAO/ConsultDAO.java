package tw.com.ian.pwci.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tw.com.ian.pwci.Interface.IDAO;
import tw.com.ian.pwci.Object.Consult;
import tw.com.ian.pwci.Util.Misc;

public class ConsultDAO extends IDAO<Consult> {
    static String TABLE_NAME = "CONSULT";  //回診
    static String id_column = "_id";
    static String department_column = "department";
    static String doctor_column = "doctor";
    static String date1_column = "date";// 回診日
    static String number_column = "number"; //回診號碼
    static String date2_column = "date2"; //
    static String date3_column = "date3"; //

    static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            id_column + " Integer primary key autoincrement, " +
            department_column + " text," +
            doctor_column + " text," +
            date1_column + " text," +
            number_column + " integer," +
            date2_column + " text," +
            date3_column + " text " +
            " )";

    public ConsultDAO(Context context) {
        super(context);
    }

    @Override
    public Consult insert(Consult consult) {
        ContentValues cv = new ContentValues();
        cv.put(department_column, consult.getDepartment());
        cv.put(doctor_column, consult.getDoctor());
        cv.put(date1_column, consult.getDate1());
        cv.put(number_column, consult.getNumber());
        cv.put(date2_column, consult.getDate2());
        cv.put(date3_column, consult.getDate3());
        long id = db.insert(TABLE_NAME, null, cv);
        consult.setId(id);
        return consult;
    }

    @Override
    public boolean delete(long id) {
        String where = id_column + " = ? ";
        String[] sid = new String[1];
        sid[0] = String.valueOf(id);
        return db.delete(TABLE_NAME, where, sid) > 0;
    }

    @Override
    public Consult update(Consult consult) {
        ContentValues cv = new ContentValues();
        cv.put(department_column, consult.getDepartment());
        cv.put(doctor_column, consult.getDoctor());
        cv.put(date1_column, consult.getDate1());
        cv.put(number_column, consult.getNumber());
        cv.put(date2_column, consult.getDate2());
        cv.put(date3_column, consult.getDate3());
        String[] sid = new String[1];
        sid[0] = String.valueOf(consult.getId());
        String where = id_column + " = ? ";
        db.update(TABLE_NAME, cv, where, sid);
        return consult;
    }

    @Override
    public List<Consult> getAll() {
        List<Consult> consults = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {
            consults.add(getRecord(c));
        }


        return consults;
    }

    @Override
    protected Consult getRecord(Cursor c) {

        Consult consult = new Consult();
        consult.setDepartment(c.getString(c.getColumnIndex(department_column)));
        consult.setDoctor(c.getString(c.getColumnIndex(doctor_column)));
        consult.setDate1(c.getString(c.getColumnIndex(date1_column)));
        consult.setNumber(c.getInt(c.getColumnIndex(number_column)));
        consult.setDate2(c.getString(c.getColumnIndex(date2_column)));
        consult.setDate3(c.getString(c.getColumnIndex(date3_column)));

        return consult;
    }
}
