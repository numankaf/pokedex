package Day4.SerilazeExample2;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int id;
    private String department;
    private Course course;

    Student(){

    }
    Student(String name, int id, String department, Course course){
        this.name = name;
        this.id = id;
        this.department = department;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", department='" + department + '\'' +
                ", course=" + course +
                '}';
    }
}
