package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import DAO.DAO;
/**
 * Created by Victor on 6-10-2016.
 */
public class QueryUpdater extends DAO {

        public QueryUpdater() throws IllegalAccessException, InstantiationException, SQLException {
            super();

        }

        public void update() throws Exception{

            String query = getQuery();
            execute(query);
        }

        /**
         * @author Victor
         * @param query
         */
        public void execute(String query) {
            if (conn != null) {
                System.out.print("niet null");

                try {
                    Statement stmt = conn.createStatement();
                    boolean result = stmt.execute(query);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        /**
         *
         * @author
         * @return query string.
         */
        public String getQuery() {
            File file = new File("src/update.txt");
            String query = "";
            try {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    query += scanner.nextLine().toString() + " \n";
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return query;
        }
    }


