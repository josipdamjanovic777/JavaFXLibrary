/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladistenje.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import skladistenje.model.Baza;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdministracijaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    BorderPane border_pane;

    @FXML
    Button skladisteBtn;
    @FXML
    Button artikalBtn;
    @FXML
    Button PoslovnicaBtn;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void ucitajSkladista(ActionEvent e) throws IOException {
        Parent skladista = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/SkladistaAdministracija.fxml"));
        border_pane.setCenter(skladista);
    }

    public void ucitajPoslovnice(ActionEvent e) throws IOException {
        Parent poslovnica = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/PoslovnicaAdministracija.fxml"));
        border_pane.setCenter(poslovnica);
    }

    public void ucitajArtikle(ActionEvent e) throws IOException {
        Parent artikal = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/ArtikliAdministracija.fxml"));
        border_pane.setCenter(artikal);
    }
  public void odjava(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/Login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 850, 700));
        stage.show();
        border_pane.getScene().getWindow().hide();
    }
}
