package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Model.Main;
/**
 * Created by Victor on 6-10-2016.
 */
public class QueryUpdater extends Main {

        public QueryUpdater() throws IllegalAccessException, InstantiationException, SQLException {
            super();

        }

        public void update() {
            String query = getQuery();
            System.out.print(query);
            execute(query);
        }

        /**
         *
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
         * Fetch query from update.txt file.
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


