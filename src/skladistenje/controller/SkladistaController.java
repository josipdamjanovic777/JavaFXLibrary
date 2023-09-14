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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import skladistenje.model.Baza;
import skladistenje.model.Skladiste;
import skladistenje.model.skladisteSkladistar;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SkladistaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField skladisteUnos;
    @FXML
    TextField gradUnos;
    @FXML
    Label porukaLbL;
    @FXML
    TextField korisnickoImeTxt;
    @FXML
    PasswordField KorisnickaLozinkaTxt;
    @FXML
    Label SkladistarPorukaLbl;
    @FXML
    ComboBox<Skladiste> skladistarList;
    @FXML
    TableView<skladisteSkladistar> skladisteTbl;
    @FXML
    TableColumn<skladisteSkladistar, Number> skladisteIdColl;
    @FXML
    TableColumn<skladisteSkladistar, String> skladisteGradColl;
    @FXML
    TableColumn<skladisteSkladistar, String> skladisteNazivColl;
    @FXML
    TableColumn<skladisteSkladistar, String> skladisteSkladistarColl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void spremiSkladiste(ActionEvent e) {
        String skladiste = skladisteUnos.getText();
        String grad = gradUnos.getText();
        if (skladiste.isEmpty() || grad.isEmpty()) {
            porukaLbL.setText("Pogreska prilikom dodavanja");
            return;
        }
        int tipObjekta = 1; //Skladiste
        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Insert into objekat(ID_Tip_Objekta, Grad, Naziv)VALUES (?,?,?);");
        try {
            ps.setInt(1, tipObjekta);
            ps.setString(2, grad);
            ps.setString(3, skladiste);
            int rez = ps.executeUpdate();
            if (rez == 1) {
                porukaLbL.setText("Uspjesno ste unijeli skladiste");
            }

        } catch (Exception exc) {
        }

    }

    public void spremiSkladistara(ActionEvent e) {
        String korisnickoIme = korisnickoImeTxt.getText();
        String korisnickaLozinka = KorisnickaLozinkaTxt.getText();
        Skladiste s = skladistarList.getValue();

        int tipKorisnika = 2;
        if (korisnickoIme.isEmpty() || korisnickaLozinka.isEmpty() || s == null) {
            SkladistarPorukaLbl.setText("Pogreska prilikom dodavanja");
            return;
        }
        Baza b = new Baza();
        PreparedStatement ps = b.exec("Insert into korisnik(ID_Tip_Korisnika,ID_Objekta, Ime, Sifra) VALUES (?,?,?,?);");
        try {
            ps.setInt(1, tipKorisnika);
            ps.setInt(2, s.id);
            ps.setString(3, korisnickoIme);
            ps.setString(4, korisnickaLozinka);
            int rez = ps.executeUpdate();
            if (rez == 1) {
                SkladistarPorukaLbl.setText("Uspjesno ste unijeli skladistara");
            }
            skladistarList.getItems().clear();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

    }

    public void popuniSkladistare() throws SQLException {
        ObservableList<Skladiste> skladista = FXCollections.observableArrayList();
        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Select * from objekat where ID_Tip_Objekta=1 and objekat.ID not in (select ID_Objekta from korisnik where ID_Objekta)");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            skladista.add(new Skladiste(rs.getInt("ID"), rs.getString("Naziv"), rs.getString("Grad")));
        }

        skladistarList.setItems(skladista);

    }

    public void izlistajSkladista() {

        ObservableList<skladisteSkladistar> skladisteSkladistarList = FXCollections.observableArrayList();
        Baza db = new Baza();
        String selectSQL = "SELECT objekat.ID,Grad,Naziv,Ime from objekat join korisnik on korisnik.ID_Objekta=objekat.ID where ID_Tip_Objekta=1";

        try {
            PreparedStatement ps = db.exec(selectSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                skladisteSkladistar S = new skladisteSkladistar(rs.getInt("ID"), rs.getString("Grad"), rs.getString("Naziv"), rs.getString("Ime"));
                skladisteSkladistarList.add(S);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        skladisteIdColl.setCellValueFactory(new PropertyValueFactory<>("ID"));
        skladisteGradColl.setCellValueFactory(new PropertyValueFactory<>("Grad"));
        skladisteNazivColl.setCellValueFactory(new PropertyValueFactory<>("Naziv"));
        skladisteSkladistarColl.setCellValueFactory(new PropertyValueFactory<>("Skladistar"));
        skladisteTbl.setItems(skladisteSkladistarList);
    }

}
