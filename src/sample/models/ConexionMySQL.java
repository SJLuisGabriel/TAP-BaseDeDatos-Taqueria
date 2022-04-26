package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {

    private static String server = "127.0.0.1";
    private static String user   = "patron";
    private static String pwd    = "123456";
    private static String bd     = "taqueriadb";

    public static Connection conexionMySQL;
    public static void crearConexionMySQL(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexionMySQL = DriverManager.getConnection(
                    "jdbc:mysql://"+server+":3306/"+bd+
                            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=" +
                            "false&serverTimezone=UTC",user,pwd);
            System.out.println("Conexion a la bd establecida....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}