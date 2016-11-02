package DAO;

import Model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Gebruiker on 2-11-2016.
 */
public class ProjectDAO extends DAO {

    public ProjectDAO() throws IllegalAccessException, InstantiationException, SQLException {
    }

    public void addProject(Project project) throws SQLException {
        addProjectQuery(project);
    }

    public Project selectProject(int projectID) throws SQLException {
        Project project = selectProjectQuery(projectID);
        return project;
    }

    public ArrayList<Project> selectAllProjects() throws Exception {
        ArrayList<Project> projects;
        projects = selectAllProjectsQuery();
        return projects;
    }

    public void updateProject(int projectid, Project project) throws SQLException {
        updateProjectQuery(projectid, project);
    }

    public void deleteProject(int projectID) throws SQLException {
        deleteProjectQuery(projectID);
    }

    private void addProjectQuery(Project project) throws SQLException {
        String sql = "INSERT INTO project (projectid, projectname, tag) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, project.getProjectID());
        statement.setString(2, project.getProjectName());
        statement.setString(3, project.getTag());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new project was inserted succesfully!");
        }
    }


    private void updateProjectQuery(int projectID, Project project) throws SQLException {
        String sql = "UPDATE project SET projectname=?, tag=? WHERE projectid=?";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ResultSet keyResultSet = statement.getGeneratedKeys();

        while (keyResultSet.next()) {
            if (keyResultSet.equals(projectID)) {
                statement.setString(1, project.getProjectName());
                statement.setString(2, project.getTag());
            }

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing project was updated successfully!");
            }
        }
    }


    private Project selectProjectQuery(int projectID) throws SQLException {
        String sql = "SELECT * FROM project WHERE projectid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.getGeneratedKeys();
        Project project = null;

        while(result.next()) {
            if(result.equals(project)) {
                project = new Project();
                project.setProjectName(result.getString(2));
                project.setTag(result.getString(3));
            }
        }
        return project;
    }


    private ArrayList<Project> selectAllProjectsQuery() {
        ArrayList<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Project project = new Project();
                project.setProjectName(result.getString(2));
                project.setTag(result.getString(3));

                projects.add(project);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return projects;
    }


    private void deleteProjectQuery(int projectID) throws SQLException {
        String sql = "DELETE FROM project WHERE projectid=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet keyResultSet = statement.getGeneratedKeys();

        while (keyResultSet.next()) {
            if(keyResultSet.equals(projectID)) {
                int rowsDeleted = statement.executeUpdate();
                if(rowsDeleted > 0) {
                    System.out.println("A project was deleted succesfully");
                }
            }
        }
    }


}
