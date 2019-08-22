/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

import crudcidade.*;
import Conecta.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Darcio
 */
public class Cidade {
    Conecta con = new Conecta();
    private int codCidade;

    public int getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }
    private String des;
    private String cep;
    private String uf;
    private String pais;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    // cadastrar cliente
    public void cadastrar(Cidade cid) {
        System.out.println("Cliente: "+cid.getDes());
        try {
            PreparedStatement cad = con.conectar().prepareStatement("insert into cidade (des,cep,uf,pais) values (?,?,?,?)");

            cad.setString(1, cid.getDes());
            cad.setString(2, cid.getCep());
            cad.setString(3, cid.getUf());
            cad.setString(4, cid.getPais());
                        
            cad.execute();
            JOptionPane.showMessageDialog(null, "Cidade cadastrado!");
            
             } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Cidade:\n" + ex, "Erro", 0);
        }

    }
    // excluir
    public void deletar(int cod) {
        int op = JOptionPane.showConfirmDialog(null, "Deseja excluir?", "Excluir", JOptionPane.OK_CANCEL_OPTION);

        try {
            PreparedStatement excluir = con.conectar().prepareStatement("delete from Cidade where idCidade=" + cod);

            if (op == 0) {
                excluir.execute();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir:\n" + ex);
        }

    }
    // alterar
    public void alterar(Cidade cid, int cod) {
        try {
            PreparedStatement altera = con.conectar().prepareStatement("update Cidade set des = ?, cep = ?, uf =?, pais = ? where idCidade = " + cod);
            altera.setString(1, cid.getDes());
            altera.setString(2, cid.getCep());
            altera.setString(3,cid.getUf());
            altera.setString(4, cid.getPais());
            
            altera.execute();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar dados da Cidade!:\n" + ex);
        }

    }
    // buscando Cidade pelo nome
    public ArrayList<Cidade> buscarNome(String des) {
        ArrayList<Cidade> listar = new ArrayList<>();
        try {
            PreparedStatement buscar = con.conectar().prepareStatement("select * from Cidade where des like '" + des + "%'");
            ResultSet res = buscar.executeQuery();

            while (res.next()) {
                Cidade c = new Cidade();
                c.setCodCidade(res.getInt(1));
                c.setDes(res.getString(2));
                c.setCep(res.getString(3));
                c.setUf(res.getString(4));
                c.setPais(res.getString(5));
                listar.add(c);

            }
            return listar;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar a cidade:\n" + ex, "Erro", 0);
        }
        return null;
    }
     // listando cidade
    public ArrayList<Cidade> listar() {
        ArrayList<Cidade> listar = new ArrayList<>();
        try {
            PreparedStatement list = con.conectar().prepareStatement("select * from cidade");
            ResultSet res = list.executeQuery();

            while (res.next()) {
                Cidade c = new Cidade();
                c.setCodCidade(res.getInt(1));
                c.setDes(res.getString(2));
                c.setCep(res.getString(3));
                c.setUf(res.getString(4));
               c.setPais(res.getString(5));
               
                listar.add(c);
            }

            return listar;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar cliente:\n" + ex);
        }

        return null;

    }
     // buscando Cidade Por Codigo
    public Cidade buscar(int cod) {
        try {
            PreparedStatement buscar = con.conectar().prepareStatement("select * from cidade where idCidade = " + cod);
            ResultSet res = buscar.executeQuery();

            while (res.next()) {
                Cidade cidade = new Cidade();
                cidade.setCodCidade(res.getInt(1));
                cidade.setDes(res.getString(2));
                cidade.setCep(res.getString(3));
                cidade.setUf(res.getString(4));
                cidade.setPais(res.getString(5));
                
                return cidade;
            }

            Cidade cidade2 = new Cidade();
            cidade2.setDes("Sem Cidade");
            return cidade2;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente:\n" + ex, "Erro", 0);
        }
        return null;
    }
   
}
