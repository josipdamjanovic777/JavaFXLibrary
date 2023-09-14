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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import skladistenje.model.Baza;
import skladistenje.model.Korisnik;
import skladistenje.model.Poslovnica;
import skladistenje.model.PoslovnicaStanje;
import skladistenje.model.artikal;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PoslovnicaController implements Initializable {

    @FXML
    ComboBox<artikal> ArtikalCombo;
    @FXML
    TextField napomenaTxt;
    @FXML
    TextField kolicinaId;
    @FXML
    Label porukaLbl;
    @FXML
    TableView<PoslovnicaStanje> artikliTbl;
    @FXML
    TableColumn<PoslovnicaStanje, String> NazivTXT;
    @FXML
    TableColumn<PoslovnicaStanje, String> OpisTxt;
    @FXML
    TableColumn<PoslovnicaStanje, Number> KolicinaId;
    @FXML
    Label loginMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void SpremiZahtjev(ActionEvent e) {
        int kolicina = 0;
        String napomena = napomenaTxt.getText();
        try {
            kolicina = Integer.parseInt(kolicinaId.getText());
        } catch (Exception exc) {
            porukaLbl.setText("Kolicina mora biti cijeli broj!!!");
        }
        artikal a = ArtikalCombo.getValue();

        if (napomenaTxt.getText().isEmpty() || kolicinaId.getText().isEmpty()) {
            porukaLbl.setText("Molimo popunite sve podatke");
            porukaLbl.setTextFill(Color.web("#f73c20"));
        } else {

            Baza baza = new Baza();
            PreparedStatement ps = baza.exec("Insert into zahtjev (ID_Korisnika,ID_Artikla,ID_Objekta,Kolicina,Poruka,Status)VALUES (?,?,?,?,?,?);");
            try {
                ps.setInt(1, Korisnik.Id);
                ps.setInt(2, a.ID);
                ps.setInt(3, Korisnik.IdObjketa);
                ps.setInt(4, kolicina);
                ps.setString(5, napomena);
                ps.setInt(6, 0);
                int rez = ps.executeUpdate();
                if (rez == 1) {
                    porukaLbl.setText("Uspjesno ste poslali zahtjev");
                }

            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            }

        }

    }

    public void Artikli() {
        loginMsg.setText("Dobrodosli " + Korisnik.ime + " u poslovnicu "+ Korisnik.imeObjekta);
        ObservableList<PoslovnicaStanje> artikalList = FXCollections.observableArrayList();
        Baza db = new Baza();
        String selectSQL = "SELECT Naziv,Opis,Kolicina FROM artikal join stanje on artikal.ID=stanje.ID_Artikla WHERE ID_Objekta="+Korisnik.IdObjketa;

        try {
            PreparedStatement ps = db.exec(selectSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PoslovnicaStanje p = new PoslovnicaStanje(rs.getString("Naziv"), rs.getString("Opis"), rs.getInt("Kolicina"));
                artikalList.add(p);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        KolicinaId.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        NazivTXT.setCellValueFactory(new PropertyValueFactory<>("Naziv"));
        OpisTxt.setCellValueFactory(new PropertyValueFactory<>("Opis"));

        artikliTbl.setItems(artikalList);
    }

    public void Zahtjev() throws SQLException {
        ObservableList<artikal> artikli = FXCollections.observableArrayList();
        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Select * from artikal ");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            artikli.add(new artikal(rs.getInt("ID"), rs.getString("Naziv"), rs.getString("Opis")));
        }

        ArtikalCombo.setItems(artikli);

    }

      public void odjava() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/Login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 850, 700));
        stage.show();
        porukaLbl.getScene().getWindow().hide();
    } 
    

}
