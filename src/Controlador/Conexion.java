/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author David
 */
public class Conexion {
    private Connection conn = null;
    public Connection CrearBD(){
            createTable("Clientes", "nombre varchar(50), ape1 varchar(50), ape2 varchar(50), direccion varchar(40)");
            createTable("Clientes2", "nombre varchar(50), ape1 varchar(50), ape2 varchar(50), direccion varchar(40)");
            return conn;
  }
    
     public Connection AccederBD(){
       try{
         //obtenemos el driver de para mysql
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         //obtenemos la conexión
         conn = DriverManager.getConnection("jdbc:derby:.\\DB\\Derby.DB");
         if (conn!=null){
            JOptionPane.showMessageDialog(null,"OK base de datos listo");
         }
      }catch(SQLException e){
       JOptionPane.showMessageDialog(null,e.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
      }catch(ClassNotFoundException e){
         JOptionPane.showMessageDialog(null,e.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
      }
       return conn;
  }
     
      public void cerracon (){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
      
      private void createTable(String tableName, String columns){
      try{
              //obtenemos el driver de para mysql
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
              columns = (columns.startsWith("(")) ? columns.trim():"("+columns.trim();
              columns = (columns.endsWith("))")) ? columns.trim():columns.trim()+")";
              //obtenemos la conexión
              conn = DriverManager.getConnection("jdbc:derby:.\\DB\\Derby.DB;create=true");
              if (conn!=null){
                 JOptionPane.showMessageDialog(null,"OK base de datos listo");
                 String creartabla="create table "+tableName+columns;
                 String desc="disconnect;";
                 try {
                 PreparedStatement pstm = conn.prepareStatement(creartabla);
                 pstm.execute();
                 pstm.close();

                 PreparedStatement pstm2 = conn.prepareStatement(desc);
                 pstm2.execute();
                 pstm2.close();

                JOptionPane.showMessageDialog(null,"BD Creada correctamente");
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
             }
         }
      }catch(SQLException e){
       JOptionPane.showMessageDialog(null,e.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
      }catch(ClassNotFoundException e){
         JOptionPane.showMessageDialog(null,e.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
      }
      }
          
}
