package Conecta;

import crudcidade.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conecta {

     
    
    //private final String url="jdbc:mysql://10.1.1.150:3306/MercadoBD";
   private final String url="jdbc:mysql://localhost:3306/cidade1";
    //private final String url="jdbc:postgresql://localhost:5432/Cidade";
    private final String usuario = "root";
    private final String senha = "";
   
    
   
    // conexao 
    public Connection conectar(){       
              
        try {
            Connection con = DriverManager.getConnection(url, usuario, senha);
           // JOptionPane.showMessageDialog(null, "Conectado");
            return con;                    

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar:\n" + ex);
        }

        return null;

    }
    
    
    
    
    

}
