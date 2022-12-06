package Persistence;

import java.sql.*;

public abstract class DAO {

    //Creation DAO
    protected Connection connection = null;
    protected ResultSet resultSet = null;
    protected Statement statement= null;

    private final String USER = "root";
    private final String PASSWORD = "admin";
    private final String DATABASE = "estancias_exterior";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void connectDataBase()throws ClassNotFoundException, SQLException {
        try{
            Class.forName(DRIVER);
            String urlDataBase = "jdbc:mysql://localhost:3306/" + DATABASE;
            connection = DriverManager.getConnection(urlDataBase,USER,PASSWORD);

        }catch (ClassNotFoundException | SQLException e){
            System.out.println("I don't can connect" );
            e.printStackTrace();
            throw e;

        }

    }

    protected void disconnectDataBase()throws  SQLException{
        try{
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }catch ( SQLException e){
            throw e;
        }catch (Exception d){
            System.out.println("I don't can disconnect");
            d.printStackTrace();
        }
    }

    protected void insertDeleteUpdate(String sqlQuery) throws Exception{
        try {
            connectDataBase();
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);

        }catch (Exception e){
            throw e;
        }finally {
            disconnectDataBase();
        }


    }

    protected void queryDataBase(String sqlQuery) throws Exception{

        try{
            connectDataBase();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        }catch (ClassNotFoundException | SQLException e){
            throw e;
        }
    }



}
