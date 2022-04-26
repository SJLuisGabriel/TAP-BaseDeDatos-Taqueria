package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Main.BaseDeDatos;

public class EmpleadosDAO {
    private int claveemp;
    private String nomemp;
    private String apematemp;
    private String apepatemp;
    private String rfcemp;
    private double sueldo;
    private String telfono;
    private String nssemp;
    private String emailemp;
    private Statement stmt;

    public int getClaveemp() {return claveemp;}

    public void setClaveemp(int claveemp) {this.claveemp = claveemp;}

    public String getNomemp() {return nomemp;}

    public void setNomemp(String nomemp) {this.nomemp = nomemp;}

    public String getApematemp() {return apematemp;}

    public void setApematemp(String apematemp) {this.apematemp = apematemp;}

    public String getApepatemp() {return apepatemp;}

    public void setApepatemp(String apepatemp) {this.apepatemp = apepatemp;}

    public String getRfcemp() {return rfcemp;}

    public void setRfcemp(String rfcemp) {this.rfcemp = rfcemp;}

    public double getSueldo() {return sueldo;}

    public void setSueldo(double sueldo) {this.sueldo = sueldo;}

    public String getTelfono() {return telfono;}

    public void setTelfono(String telfono) {this.telfono = telfono;}

    public String getNssemp() {return nssemp;}

    public void setNssemp(String nssemp) {this.nssemp = nssemp;}

    public String getEmailemp() {return emailemp;}

    public void setEmailemp(String emailemp) {this.emailemp = emailemp;}

    public void INSERTAREmpleados(){
        String query = "INSERT INTO tblempleados (nomemp,apematemp,apepatemp,rfcemp,sueldo,telfono,nssemp,emailemp)" +
                "VALUES('"+this.nomemp+"','"+this.apematemp+"','"+this.apematemp+"','"+this.rfcemp+"',"+
                this.sueldo+",'"+this.telfono+"','"+this.nssemp+"','"+this.emailemp+"')";
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

    public void ACTUALIZAREmpleados(){
        String query = "UPDATE tblempleados SET nomemp='"+this.nomemp+"',apematemp='"+this.apematemp+"'," +
                "apepatemp='"+this.apepatemp+"', rfcemp='"+this.rfcemp+"',sueldo="+this.sueldo+"," +
                "telfono='"+this.telfono+"',nssemp='"+this.nssemp+"',emailemp='"+this.emailemp+"'  " +
                "WHERE claveemp = "+this.claveemp;
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

    public void ELIMINAREmpleados(){
        String query = "DELETE FROM tblempleados WHERE claveemp = "+this.claveemp;
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
    public ObservableList<EmpleadosDAO> SELECCIONAREmpleados(){

        ObservableList<EmpleadosDAO> listaEmp = FXCollections.observableArrayList();
        EmpleadosDAO objEmp;
        String query = "SELECT * FROM tblempleados ORDER BY claveemp";
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
            } else if (BaseDeDatos == 2){
                stmt = ConexionMySQL.conexionMySQL.createStatement();
            }
            ResultSet res = stmt.executeQuery(query);
            while( res.next() ){
                objEmp = new EmpleadosDAO();
                objEmp.setClaveemp(res.getInt("claveemp"));
                objEmp.setNomemp(res.getString("nomemp"));
                objEmp.setApematemp(res.getString("apematemp"));
                objEmp.setApepatemp(res.getString("apepatemp"));
                objEmp.setRfcemp(res.getString("rfcemp"));
                objEmp.setSueldo(res.getDouble("sueldo"));
                objEmp.setTelfono(res.getString("telfono"));
                objEmp.setNssemp(res.getString("nssemp"));
                objEmp.setEmailemp(res.getString("emailemp"));
                listaEmp.add(objEmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmp;
    } // RECUPERAR TODOS LOS REGISTROS

}
