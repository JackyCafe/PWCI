package tw.com.ian.pwci.Object;

import java.io.Serializable;

public class Department implements Serializable {
    long id;
    String department;
    String doctor;

    public Department() {
    }

    public Department(long id, String department, String doctor) {
        this.id = id;
        this.department = department;
        this.doctor = doctor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", doctor='" + doctor + '\'' +
                '}';
    }
}
