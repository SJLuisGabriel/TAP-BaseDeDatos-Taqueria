package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Main.BaseDeDatos;

public class PlatillosDAO {

    private int cveplatillo;
    private String nomplatillo;
    private double costo;
    private String tamaio;
    private double precio;
    private Statement stmt;

    public int getCveplatillo() {return cveplatillo;}

    public void setCveplatillo(int cveplatillo) {this.cveplatillo = cveplatillo;}

    public String getNomplatillo() {return nomplatillo;}

    public void setNomplatillo(String nomplatillo) {this.nomplatillo = nomplatillo;}

    public double getCosto() {return costo;}

    public void setCosto(double costo) {this.costo = costo;}

    public String getTamaio() {return tamaio;}

    public void setTamaio(String tamaio) {this.tamaio = tamaio;}

    public double getPrecio() {return precio;}

    public void setPrecio(double precio) {this.precio = precio;}

    public void INSERTARPlatilos(){
        String query = "INSERT INTO tblplatillos (nomplatillo,costo,tamaio,precio) " +
                "VALUES('"+this.nomplatillo+"',"+this.costo+",'"+this.tamaio+"',"+this.precio+")";
        try {
            if (BaseDeDatos == 1){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            } else if (BaseDeDatos == 2){
                Statement stmt = ConexionMySQL.conexionMySQL.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZARPlatillos(){
        String query = "UPDATE tblplatillos SET nomplatillo='"+this.nomplatillo+"',costo="+this.costo+"," +
                "tamaio='"+this.tamaio+"',precio="+this.precio+"  WHERE cveplatillo = "+this.cveplatillo;
        try {
            if (BaseDeDatos == 1){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            } else if (BaseDeDatos == 2){
                Statement stmt = ConexionMySQL.conexionMySQL.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINARPlatillos(){
        String query = "DELETE FROM tblplatillos WHERE cveplatillo = "+this.cveplatillo;
        try {
            if (BaseDeDatos == 1){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            } else if (BaseDeDatos == 2){
                Statement stmt = ConexionMySQL.conexionMySQL.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<PlatillosDAO> SELECCIONARPlatillos(){

        ObservableList<PlatillosDAO> listaPl = FXCollections.observableArrayList();
        PlatillosDAO objC;
        String query = "SELECT * FROM tblplatillos ORDER BY nomplatillo";
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
            } else if (BaseDeDatos == 2){
                stmt = ConexionMySQL.conexionMySQL.createStatement();
            }
            ResultSet res = stmt.executeQuery(query);
            while( res.next() ){
                objC = new PlatillosDAO();
                objC.setCveplatillo(res.getInt("cveplatillo"));
                objC.setNomplatillo(res.getString("nomplatillo"));
                objC.setCosto(res.getDouble("costo"));
                objC.setTamaio(res.getString("tamaio"));
                objC.setPrecio(res.getInt("precio"));
                listaPl.add(objC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPl;

    } // RECUPERAR TODOS LOS REGISTROS

    public void SELECCIONARBYID(){}
}
