package model;


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

    /**
     * Instantiates a new Project.
     */
    public Project(){
    }

    /**
     * Instantiates a new Project.
     *
     * @param projectID the project id
     */
    public Project(int projectID){
        this.projectID = projectID;
        this.projectName = null;
        this.relationList = null;
        this.tag = null;
    }

    /**
     * Instantiates a new Project.
     *
     * @param projectName  the project name
     * @param relationList the relation list
     * @param tag          the tag
     */
    public Project(String projectName, ArrayList<Relation> relationList, String tag){
      this.projectName = projectName;
      this.relationList = relationList;
      this.tag = tag;
    }

    /**
     * Instantiates a new Project.
     *
     * @param projectID    the project id
     * @param studentID    the student id
     * @param projectName  the project name
     * @param relationList the relation list
     * @param tag          the tag
     */
    public Project(int projectID, int studentID, String projectName, ArrayList<Relation> relationList, String tag){
        this.projectID = projectID;
        this.projectName = projectName;
        this.relationList = relationList;
        this.tag = tag;
        this.student =  new Student(studentID);

    }

    /**
     * Gets project id.
     *
     * @return the project id
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     * Sets project id.
     *
     * @param projectID the project id
     */
    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    /**
     * Gets project name.
     *
     * @return the project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets project name.
     *
     * @param projectName the project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Gets relation list.
     *
     * @return the relation list
     */
    public ArrayList<Relation> getRelationList() {
        return relationList;
    }

    /**
     * Sets relation list.
     *
     * @param relationList the relation list
     */
    public void setRelationList(ArrayList<Relation> relationList) {
        this.relationList = relationList;
    }

    /**
     * Gets dao.
     *
     * @return the dao
     */
    public ProjectDAO getDao() { return dao; }

    /**
     * Sets dao.
     *
     * @param dao the dao
     */
    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public String getTag() { return tag; }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(String tag) { this.tag = tag; }
}
