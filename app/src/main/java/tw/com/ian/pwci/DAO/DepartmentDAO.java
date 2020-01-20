package tw.com.ian.pwci.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.pwci.Interface.DAO;
import tw.com.ian.pwci.Interface.IDAO;
import tw.com.ian.pwci.Object.Department;

public class DepartmentDAO extends IDAO<Department> {

    static String TABLE_NAME = "DEPARTMENT";  //遊戲進度
    static String id_column = "_id";
    static String department_column = "topic";// 科別
    static String doctor_column = "status"; //醫師

    static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            id_column + " Integer primary key autoincrement, " +
            department_column + " text," +
            doctor_column + " text "+
            " )";

    public DepartmentDAO(Context context) {
        super(context);
    }

    @Override
    public Department insert(Department department) {
        ContentValues cv = new ContentValues();
        cv.put(department_column,department.getDepartment());
        cv.put(doctor_column,department.getDoctor());
        long id = db.insert(TABLE_NAME,null,cv);
        department.setId(id);
        return department;
    }

    @Override
    public boolean delete(long id) {
        String where = id_column + " = ? ";
        String[] sid = new String[1] ; sid[0] = String.valueOf(id) ;
        return   db.delete(TABLE_NAME,where,sid) > 0;

    }

    @Override
    public Department update(Department department) {
        ContentValues cv = new ContentValues();
        cv.put(department_column,department.getDepartment());
        cv.put(doctor_column,department.getDoctor());
        String where = id_column + " = ? ";
        String[] sid = new String[1] ; sid[0] = String.valueOf(department.getId()) ;
        db.update(TABLE_NAME, cv, where, sid);
        return department;
    }

    @Override
    public List<Department> getAll() {
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<Department> deparements = new  ArrayList<Department>();
        while(c.moveToNext())
        {
            deparements.add(getRecord(c));
        }
        return deparements;
    }

    @Override
    protected Department getRecord(Cursor c) {
        Department department = new Department();
        department.setId(c.getLong(0));
        department.setDepartment(c.getString(c.getColumnIndex(department_column)));
        department.setDoctor(c.getString(c.getColumnIndex(doctor_column)));
        return department;
    }

    public List<String> getDepartments()
    {
        List<String> strings = new ArrayList<>();
        Cursor c = db.query(true,TABLE_NAME,new String[]{department_column},null,null,null,null,null,null);
        while (c.moveToNext())
        {
            strings.add(c.getString(c.getColumnIndex(department_column)));
        }
        return  strings;
    }

    public List<String> getDoctors()
    {
        List<String> strings = new ArrayList<>();
        Cursor c = db.query(true,TABLE_NAME,new String[]{doctor_column},null,null,null,null,null,null);
        while (c.moveToNext())
        {
            strings.add(c.getString(c.getColumnIndex(doctor_column)));
        }
        return  strings;
    }
}
