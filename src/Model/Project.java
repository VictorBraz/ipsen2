package Model;


import DAO.ProjectDAO;

import java.util.ArrayList;

/**
 * Created by Negin Nafissi on 2-11-2016.
 */
public class Project {

    private int projectID;
    private Student student;
    private Client client;
    private Company company;

    private String projectName;
    private ArrayList<Relation> relationList;
    private String tag;
    private ProjectDAO dao;

    public Project(){
    }

    public Project(int projectID){
        this.projectID = projectID;
        this.projectName = null;
        this.relationList = null;
        this.tag = null;
    }

    public Project(String projectName, ArrayList<Relation> relationList, String tag){
      this.projectName = projectName;
      this.relationList = relationList;
      this.tag = tag;
    }

    public Project(int projectID, int studentID, String projectName, ArrayList<Relation> relationList, String tag){
        this.projectID = projectID;
        this.projectName = projectName;
        this.relationList = relationList;
        this.tag = tag;
        this.student =  new Student(studentID);

    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<Relation> getRelationList() {
        return relationList;
    }

    public void setRelationList(ArrayList<Relation> relationList) {
        this.relationList = relationList;
    }

    public ProjectDAO getDao() { return dao; }

    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }

    public String getTag() { return tag; }

    public void setTag(String tag) { this.tag = tag; }
}
