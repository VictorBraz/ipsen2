package Controller;

import DAO.StudentDAO;
import Model.Student;

import java.util.ArrayList;

public class StudentController{

    private StudentDAO dao;

    public StudentController(){
        try{
            this.dao = new StudentDAO();
            this.dao.prepareStatements();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(Student student){
        dao.deleteStudent(student);
    }

    public ArrayList<Student> selectAllStudents(){
        return dao.selectAllStudents();
    }

    public void addStudent(){
        //Address address = AddressController.addAddress(fields);
        //Note note = NoteController.addNote()
        Student student = new Student();
        //Get input from fields en set student
        //student.setFirstName();
        //student.setLastName();
        //student.setBirthDate();
        //student.setAddress();
        //student.setEmailAddress();
        //student.setStudy();
        //student.setPhoneNumber();
        dao.addStudent(student);
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
        dao.updateStudent(student);
    }

    private void close(){
        dao.close();
    }
}