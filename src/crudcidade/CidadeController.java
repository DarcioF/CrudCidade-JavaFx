/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudcidade;

import Objeto.Cidade;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Darcio
 */
public class CidadeController implements Initializable {
    Cidade cid= new Cidade() ;
    int flag=0;
    static Stage stage = new Stage();
    @FXML
    private TextField CidadeTxt;
    @FXML
    private TextField cepTxt;
    @FXML
    private TextField paisTxt;
    @FXML
    private TextField uftxt;
    @FXML
    private Button salvarBtn;
    @FXML
    private Button alterarBtn;
    @FXML
    private Button deletarBtn;
    @FXML
    private TableView<Cidade> tableCidade;
    @FXML
    private TableColumn<Cidade, String> columnCid;
    @FXML
    private TableColumn<Cidade, String> columnCep;
    @FXML
    private TableColumn<Cidade, String> columnUF;
    @FXML
    private TableColumn<Cidade, String> paisUf;
    @FXML
    private Button pesquisarBtn;
    @FXML
    private Button novoBtn;
    @FXML
    private TextField pestxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // selecionando cliene
        tableCidade.setOnMouseClicked((event) -> {
            selecionarCidade();
            deletarBtn.setDisable(false);
        });
       listarCidade();
       bloqueio(false);
       alterarBtn.setDisable(true);
       salvarBtn.setDisable(true);
       deletarBtn.setDisable(true);
      
    }
// abrir tela
    public void abrirTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("cidade.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Cidades");
        stage.setMinWidth(900);
        stage.setMinHeight(685);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }    
    public void bloqueio(Boolean op){
         CidadeTxt.setEditable(op);
         uftxt.setEditable(op);
         paisTxt.setEditable(op);
         cepTxt.setEditable(op);
    
    }
    //pesquisando Cidade
    public void pesquisa(){
         String pes=pestxt.getText();
        columnCid.setCellValueFactory(new PropertyValueFactory<>("des"));
        columnCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        columnUF.setCellValueFactory(new PropertyValueFactory<>("uf"));
        paisUf.setCellValueFactory(new PropertyValueFactory<>("pais"));
        ObservableList<Cidade> obs = FXCollections.observableArrayList(cid.buscarNome(pes));
        tableCidade.setItems(obs);
        pestxt.setText("");
        
    }
    
      // listando cidade
    public void listarCidade() {
            
        columnCid.setCellValueFactory(new PropertyValueFactory<>("des"));
        columnCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        columnUF.setCellValueFactory(new PropertyValueFactory<>("uf"));
        paisUf.setCellValueFactory(new PropertyValueFactory<>("pais"));
        ObservableList<Cidade> obs = FXCollections.observableArrayList(cid.listar());
        tableCidade.setItems(obs);
    }
     // cadastrar Cidade
    @FXML
    public void cadCidade(){
        Cidade cid1= new Cidade();
    if(flag==1){
        cid1.setDes(CidadeTxt.getText());
        cid1.setCep(cepTxt.getText());
        cid1.setUf(uftxt.getText());
        cid1.setPais(paisTxt.getText());
        cid1.cadastrar(cid1);
        limpar();
        novoBtn.setDisable(false);
        salvarBtn.setDisable(true);
    
    }else {
        int cod = tableCidade.getSelectionModel().getSelectedItem().getCodCidade();
        cid1.setDes(CidadeTxt.getText());
        cid1.setCep(cepTxt.getText());
        cid1.setUf(uftxt.getText());
        cid1.setPais(paisTxt.getText());
        cid1.alterar(cid1,cod);
        limpar();
        salvarBtn.setDisable(true);
        novoBtn.setDisable(false);
        deletarBtn.setDisable(true);
    } 
    listarCidade();
        
    }
    
    
    @FXML
    public void flag(){
    flag=1;
    bloqueio(true);
    limpar();
    salvarBtn.setDisable(false);
    novoBtn.setDisable(true);
    alterarBtn.setDisable(true);
    deletarBtn.setDisable(true);
    }
    @FXML
    public void deletar(){
      int cod1 = tableCidade.getSelectionModel().getSelectedItem().getCodCidade();
      cid.deletar(cod1);
      limpar();
      listarCidade();
     deletarBtn.setDisable(true);
     alterarBtn.setDisable(true);
    }

    private void selecionarCidade() {
       int cod = tableCidade.getSelectionModel().getSelectedItem().getCodCidade();
        cid = cid.buscar(cod);

        CidadeTxt.setText(cid.getDes());
        cepTxt.setText(cid.getCep());
        uftxt.setText(cid.getUf());
        paisTxt.setText(cid.getPais());
        alterarBtn.setDisable(false);
       
    }
    public void excluirCidade() {
        int cod = tableCidade.getSelectionModel().getSelectedItem().getCodCidade();
        cid.deletar(cod);
        limpar();
        listarCidade(); 
        deletarBtn.setDisable(false);
        alterarBtn.setDisable(false);
    }
   
    public void flagAlt(){
       flag=0;
       bloqueio(true);
      
       alterarBtn.setDisable(true);
       salvarBtn.setDisable(false);
       novoBtn.setDisable(true);
       }
    
    public void limpar(){
      CidadeTxt.setText("");
      cepTxt.setText("");
      uftxt.setText("");
      paisTxt.setText("");
    }
}
