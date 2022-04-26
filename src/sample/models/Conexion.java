package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static String server = "127.0.0.1";
    private static String user   = "gabosj";
    private static String pwd    = "852741";
    private static String bd     = "taqueriadb";

    public static Connection conexion;
    public static void crearConexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://"+server+":3306/"+bd,user,pwd);
            System.out.println("Conexion a la bd establecida....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
