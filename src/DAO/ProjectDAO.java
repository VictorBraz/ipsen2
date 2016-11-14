package DAO;

import model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Negin Nafissi on 2-11-2016.
 */
public class ProjectDAO extends DAO {

    /**
     * Instantiates a new Project dao.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     * @throws SQLException           the sql exception
     */
    public ProjectDAO() throws IllegalAccessException, InstantiationException, SQLException {
    }

    /**
     * Add project.
     *
     * @param project the project
     * @throws SQLException the sql exception
     */
    public void addProject(Project project) throws SQLException {
        addProjectQuery(project);
    }

    /**
     * Select project project.
     *
     * @param projectID the project id
     * @return the project
     * @throws SQLException the sql exception
     */
    public Project selectProject(int projectID) throws SQLException {
        Project project = selectProjectQuery(projectID);
        return project;
    }

    /**
     * Select all projects array list.
     *
     * @return the array list
     * @throws Exception the exception
     */
    public ArrayList<Project> selectAllProjects() throws Exception {
        ArrayList<Project> projects;
        projects = selectAllProjectsQuery();
        return projects;
    }

    /**
     * Update project.
     *
     * @param projectid the projectid
     * @param project   the project
     * @throws SQLException the sql exception
     */
    public void updateProject(int projectid, Project project) throws SQLException {
        updateProjectQuery(projectid, project);
    }

    /**
     * Delete project.
     *
     * @param projectID the project id
     * @throws SQLException the sql exception
     */
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

            }
        }
    }


}
