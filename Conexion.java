/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animatromica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author aland
 */
public class Conexion {
    public static Connection getConexion(){
        String ConexionUrl = "jdbc:sqlserver://localhost:1433;"
                +"database=Animatromica;"+
                "user=Alan99;"+
                "password=15192252;"+
                "loginTimeout=30;";
        try{
           Connection conn = DriverManager.getConnection(ConexionUrl);
            System.out.println("Conexión Correcta");
           return conn;
           
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
}
