/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladistenje.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import skladistenje.model.Baza;
import skladistenje.model.artikal;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ArtikliAdministracijaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField nazivTxt;
    @FXML
    TextField opisTxt;
    @FXML
    Label artikalPorukaLbl;
    @FXML
    TableView<artikal> aritkliTbl;
    @FXML
    TableColumn<artikal, Number> idCol;
    @FXML
    TableColumn<artikal, String> NazivCol;
    @FXML
    TableColumn<artikal, String> OpisCol;
    @FXML
    Label UrediLbl;
    @FXML
    Pane updatePane;
    @FXML
    TextField UrediNazivTxt;
    @FXML
    TextField UrediOpisTxt;
    @FXML
    Label idUpdate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void spremiArtikal(ActionEvent e) {
        String naziv = nazivTxt.getText();
        String opis = opisTxt.getText();

        if (naziv.isEmpty() || opis.isEmpty()) {
            artikalPorukaLbl.setText("Pogreska prilikom unosa");
            return;
        }

        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Insert into artikal(Naziv,Opis)VALUES (?,?);");
        try {
            ps.setString(1, naziv);
            ps.setString(2, opis);
            int rez = ps.executeUpdate();
            if (rez == 1) {
                artikalPorukaLbl.setText("Uspjesno ste unijeli skladiste");
            }

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    public void ukloniArtikal(ActionEvent e) {
        artikal a = aritkliTbl.getSelectionModel().getSelectedItem();
        Baza b = new Baza();
        PreparedStatement ps = b.exec("Delete from artikal where ID=" + a.ID);
        try {
            int rez = ps.executeUpdate();
            if (rez == 1) {
                UrediLbl.setText("Uspjesno ste pobrisali artikal");
                izlistajArtikle();
            }

        } catch (Exception exc) {
            UrediLbl.setText(exc.getMessage());
        }

    }

    public void izlistajArtikle() {
        ObservableList<artikal> artikalList = FXCollections.observableArrayList();
        Baza db = new Baza();
        String selectSQL = "SELECT * from artikal";

        try {
            PreparedStatement ps = db.exec(selectSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                artikal a = new artikal(rs.getInt("ID"), rs.getString("Naziv"), rs.getString("Opis"));
                artikalList.add(a);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NazivCol.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        OpisCol.setCellValueFactory(new PropertyValueFactory<>("opis"));

        aritkliTbl.setItems(artikalList);
    }

    public void azurirajArtikal(ActionEvent e) {
        String naziv = UrediNazivTxt.getText();
        String opis = UrediOpisTxt.getText();
        int id = Integer.parseInt(idUpdate.getText());
        Baza b = new Baza();
        PreparedStatement ps = b.exec("UPDATE artikal SET Naziv = ?, Opis = ? WHERE ID=?;");
        try {
            ps.setString(1, naziv);
            ps.setString(2, opis);
            ps.setInt(3, id);
            int rez = ps.executeUpdate();
            if (rez == 1) {
                UrediLbl.setText("Uspjesno ste azurirali artikal");
                updatePane.setVisible(false);
                izlistajArtikle();
            }

        } catch (Exception exc) {
            UrediLbl.setText(exc.getMessage());
        }
    }

    public void pripremiAzuriranjeArtikla(ActionEvent e) {
        artikal a = aritkliTbl.getSelectionModel().getSelectedItem();
        UrediNazivTxt.setText(a.naziv);
        UrediOpisTxt.setText(a.opis);
        idUpdate.setText(a.ID + "");
        updatePane.setVisible(true);
    }
}
