package Controller;

import DAO.AddressDAO;
import DAO.StudentDAO;
import Model.Address;
import Model.Student;

import java.util.ArrayList;

public class StudentController{

    private StudentDAO studentDAO;
    private AddressDAO addressDAO;

    public StudentController(){
        try{
            this.studentDAO = new StudentDAO();
            this.studentDAO.prepareStatements();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(Student student){
        studentDAO.deleteStudent(student);
    }

    public ArrayList<Student> selectAllStudents(){
        return studentDAO.selectAllStudents();
    }

    public void addStudent(){
        Student student = new Student(/*fields*/);
        //Get input from fields en set student
        //student.setFirstName();
        //student.setLastName();
        //student.setBirthDate();
        //student.setAddress();
        //student.setEmailAddress();
        //student.setStudy();
        //student.setPhoneNumber();
        addressDAO.addAddress(student.getAddress());

        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student){
        //Get input from fields en set student
        //student.setFirstName();
        //student.setLastName();
        //student.setBirthDate();
        //student.setAddress();
        //student.setEmailAddress();
        //student.setStudy();
        //student.setPhoneNumber();
        studentDAO.updateStudent(student);
    }

    private void close(){
        studentDAO.close();
    }
}