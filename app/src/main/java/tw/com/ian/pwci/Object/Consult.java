package tw.com.ian.pwci.Object;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consult implements Serializable {
        Long id;
        String Department ;
        String Doctor;
        String date1;// 回診日期
        int number;
        String date2;
        String date3;

    public Consult() {
    }

    public Consult(Long id, String department, String doctor, String date1, int number, String date2, String date3) {
        this.id = id;
        Department = department;
        Doctor = doctor;
        this.date1 = date1;
        this.number = number;
        this.date2 = date2;
        this.date3 = date3;

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getDate3() {
        return date3;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }



    @Override
    public String toString() {
        return "Consult{" +
                "id=" + id +
                ", Department='" + Department + '\'' +
                ", Doctor='" + Doctor + '\'' +
                ", date1='" + date1 + '\'' +
                ", number=" + number +
                ", date2='" + date2 + '\'' +
                ", date3='" + date3 + '\'' +
                '}';
    }
}
