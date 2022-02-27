package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    final private static Logger logger = Logger.getLogger(Database.class.getName());
    private static Connection con = null;
    ArrayList<String> data = Database.retrieveSMSData("");

    // Database Connection Establishment Function
    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/celsodb?useTimezone=true&serverTimezone=UTC", "root", "admin");
            logger.info("Connected");
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Connection not established.", e);
        }
    }

    // Database Connection Termination Function
    public static void disconnect() {
        try {
            if (con != null) {
                con.close();
                logger.info("Disconnected");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Connection not terminated.", e);
        }
    }

    // Data Insertion to Database Function
    public static void insertData(String data) {
        PreparedStatement statement = null;
        int result = 0;

        try {
            statement = con.prepareStatement(data);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }

        logger.log(Level.INFO, "Inserted : {0}", result);
    }

    // Data Retrieval from SMS Table Function
    public static ArrayList<String> retrieveSMSData(String selectQuery) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = con.prepareStatement(selectQuery);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                logger.log(Level.INFO, resultSet.getString(1) + ", "
                        + resultSet.getString(2) + ", "
                        + resultSet.getString(3) + ", "
                        + resultSet.getString(4) + ", "
                        + resultSet.getString(5) + ", "
                        + resultSet.getString(6) + ", "
                        + resultSet.getString(7) + "\n");
                result.add(resultSet.getString(1) + ", "
                        + resultSet.getString(2) + ", "
                        + resultSet.getString(3) + ", "
                        + resultSet.getString(4) + ", "
                        + resultSet.getString(5) + ", "
                        + resultSet.getString(6) + ", "
                        + resultSet.getString(7));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    statement.close();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        String presentResult = "";
        for (int i = 0; i < result.size(); i++) {
            presentResult = presentResult + "Record " + (i + 1) + ": " + result.get(i) + "\n";
        }
        logger.log(Level.INFO, "Retrieved : \n{0}", presentResult);

        return result;
    }

    // Data Retrieval from Promo Table Function
    public static ArrayList<String> retrievePromoData(String selectQuery) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = con.prepareStatement(selectQuery);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                logger.log(Level.INFO, resultSet.getString(1) + ", "
                        + resultSet.getString(2) + ", "
                        + resultSet.getString(3) + ", "
                        + resultSet.getString(4) + ", "
                        + resultSet.getString(5) + "\n");
                result.add(resultSet.getString(1) + ", "
                        + resultSet.getString(2) + ", "
                        + resultSet.getString(3) + ", "
                        + resultSet.getString(4) + ", "
                        + resultSet.getString(5));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    statement.close();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        String presentResult = "";
        for (int i = 0; i < result.size(); i++) {
            presentResult = presentResult + "Record " + (i + 1) + ": " + result.get(i) + "\n";
        }
        logger.log(Level.INFO, "Retrieved : \n{0}", presentResult);

        return result;
    }

    // Data Retrieval from Promo List
    public static ArrayList<String> retrievePromoList(String selectQuery) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = con.prepareStatement(selectQuery);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                logger.log(Level.INFO, resultSet.getString(1) + "\n");
                result.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    statement.close();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        String presentResult = "";
        for (int i = 0; i < result.size(); i++) {
            presentResult = presentResult + "Record " + (i + 1) + ": " + result.get(i) + "\n";
        }
        logger.log(Level.INFO, "Retrieved : \n{0}", presentResult);

        return result;
    }


}
