package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Main.*;

public class ClientesDAO {

    private int cvecte;
    private String nomcte;
    private String telcte;
    private String dircte;
    private Statement stmt;

    public int getCvecte() {return cvecte;}

    public void setCvecte(int cvecte) {
        this.cvecte = cvecte;
    }

    public String getNomcte() {
        return nomcte;
    }

    public void setNomcte(String nomcte) {
        this.nomcte = nomcte;
    }

    public String getTelcte() {
        return telcte;
    }

    public void setTelcte(String telcte) {
        this.telcte = telcte;
    }

    public String getDircte() {return dircte;}

    public void setDircte(String dircte) {
        this.dircte = dircte;
    }

    public void INSERTAR(){
        String query = "INSERT INTO tblcliente (nomcte,telcte,dircte) " +
                "VALUES('"+this.nomcte+"','"+this.telcte+"','"+this.dircte+"')";
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

    public void ACTUALIZAR(){
        String query = "UPDATE tblcliente SET nomcte='"+this.nomcte+"',telcte='"+this.telcte+"'," +
                "dircte='"+this.dircte+"' WHERE cvecte = "+this.cvecte;
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

    public void ELIMINAR(){
        String query = "DELETE FROM tblcliente WHERE cvecte = "+this.cvecte;
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
    public ObservableList<ClientesDAO> SELECCIONAR(){

        ObservableList<ClientesDAO> listaC = FXCollections.observableArrayList();
        ClientesDAO objC;
        String query = "SELECT * FROM tblcliente ORDER BY nomcte";
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
            } else if (BaseDeDatos == 2){
                stmt = ConexionMySQL.conexionMySQL.createStatement();
            }
            ResultSet res = stmt.executeQuery(query);
            while( res.next() ){
                objC = new ClientesDAO();
                objC.setCvecte(res.getInt("cvecte"));
                objC.setNomcte(res.getString("nomcte"));
                objC.setTelcte(res.getString("telcte"));
                objC.setDircte(res.getString("dircte"));
                listaC.add(objC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaC;

    } // RECUPERAR TODOS LOS REGISTROS

    public void SELECCIONARBYID(){}
}
