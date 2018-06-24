/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Descripción:
 * Fecha: 13/12/2016
 * Autor: Carlos A. García M.
 */
package examenes.quimica.db;

import examenes.quimica.util.ConstantesUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

    private static final String URL_DB = "jdbc:sqlite:" + ConstantesUtil.UNIDAD_RAIZ + "examenes_quimica.db";
    private static final String DB_USR = "";
    private static final String DB_PASS = "";
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    
    private Connection connection;
    
    public BaseDAO(Connection con) {
        this.connection = con;
    }
    
    public BaseDAO() {
        
    }
    
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                return DriverManager.getConnection(URL_DB, DB_USR, DB_PASS);
            } catch (ClassNotFoundException | SQLException e) {
                return null;
            }
        } else {
            return connection;
        }
    }
    
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar la conexion");
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
