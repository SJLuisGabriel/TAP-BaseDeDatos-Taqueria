package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Main.BaseDeDatos;

public class OrdenesDAO {

    private int cveorden;
    private double montoorden;
    private String fechaorden;
    private int claveemp;
    private int cvecte;
    private Statement stmt;

    public int getCveorden() {return cveorden;}

    public void setCveorden(int cveorden) {this.cveorden = cveorden;}

    public double getMontoorden() {return montoorden;}

    public void setMontoorden(double montoorden) {this.montoorden = montoorden;}

    public int getClaveemp() {return claveemp;}

    public void setClaveemp(int claveemp) {this.claveemp = claveemp;}

    public int getCvecte() {return cvecte;}

    public void setCvecte(int cvecte) {this.cvecte = cvecte;}

    public String getFechaorden() {return fechaorden;}

    public void setFechaorden(String fechaorden) {this.fechaorden = fechaorden;}

    public void INSERTAROrdenes(){
        String query = "INSERT INTO tblordenes (montoorden,fechaorden,claveemp,cvecte) " +
                "VALUES("+this.montoorden+",'"+this.fechaorden+"',"+this.claveemp+","+this.cvecte+")";
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

    public void ACTUALIZAROrdenes(){
        String query = "UPDATE tblordenes SET montoorden="+this.montoorden+",fechaorden='"+this.fechaorden+"'," +
                "claveemp="+this.claveemp+", cvecte="+this.cvecte+" WHERE cveorden = "+this.cveorden;
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

    public void ELIMINAROrdenes(){
        String query = "DELETE FROM tblordenes WHERE cveorden = "+this.cveorden;
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
    public ObservableList<OrdenesDAO> SELECCIONAROrdenes(){

        ObservableList<OrdenesDAO> listaO = FXCollections.observableArrayList();
        OrdenesDAO objO;
        String query = "SELECT * FROM tblordenes ORDER BY cveorden";
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
            } else if (BaseDeDatos == 2){
                stmt = ConexionMySQL.conexionMySQL.createStatement();
            }
            ResultSet res = stmt.executeQuery(query);
            while( res.next() ){
                objO = new OrdenesDAO();
                objO.setCveorden(res.getInt("cveorden"));
                objO.setMontoorden(res.getInt("montoorden"));
                objO.setFechaorden(res.getString("fechaorden"));
                objO.setClaveemp(res.getInt("claveemp"));
                objO.setCvecte(res.getInt("cvecte"));
                listaO.add(objO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaO;

    } // RECUPERAR TODOS LOS REGISTROS
}
