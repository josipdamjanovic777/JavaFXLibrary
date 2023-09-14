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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import skladistenje.model.Baza;
import skladistenje.model.Poslovnica;
import skladistenje.model.Skladiste;
import skladistenje.model.artikal;
import skladistenje.model.poslovnicaTrgovac;

/**
 * FXML Controller class
 *
 * @author Filip
 */
public class PoslovnicaAdministracijaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField gradUnos;
    @FXML
    TextField nazivUnos;
    @FXML
    Button SpremiPoslovnicuBtn;
    @FXML
    Label porukaLbl;
    @FXML
    TextField korisnickoImeTxt;
    @FXML
    PasswordField KorisnickaLozinkaTxt;
    @FXML
    Button spremiTrgovca;
    @FXML
    Label porukaLbl1;
    @FXML
    ComboBox<Poslovnica> poslovniceList;
    @FXML
    TableView<poslovnicaTrgovac> tabLbl;
    @FXML
    TableColumn<poslovnicaTrgovac, String> gradLbl;
    @FXML
    TableColumn<poslovnicaTrgovac, String> nazivLbl;
    @FXML
    TableColumn<poslovnicaTrgovac, Number> idLbl;
    @FXML
    TableColumn<poslovnicaTrgovac, String> trgovacLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void spremiUredjivanje(ActionEvent e) {
    }

    public void dodajTrgovca(ActionEvent e) {
        String ime = korisnickoImeTxt.getText();
        String sifra = KorisnickaLozinkaTxt.getText();
        Poslovnica p = poslovniceList.getValue();
        if (ime.isEmpty() || sifra.isEmpty() || p == null) {
            porukaLbl1.setText("Molimo popunite sve podatke");
            return;
        }
        int ID_Tip_Korisnika = 3; //poslovnica
        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Insert into korisnik(ID_Tip_Korisnika,ID_Objekta,Ime,Sifra)VALUES (?,?,?,?);");
        try {
            ps.setInt(1, ID_Tip_Korisnika);
            ps.setInt(2, p.id);
            ps.setString(3, ime);
            ps.setString(4, sifra);
            int rez = ps.executeUpdate();
            if (rez == 1) {
                porukaLbl1.setText("Uspjesno ste unijeli trgovca");
            }

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

    }

    public void SpremiPoslovnicu(ActionEvent e) {
        String grad = gradUnos.getText();
        String naziv = nazivUnos.getText();

        if (grad.isEmpty() || naziv.isEmpty()) {
            porukaLbl.setText("Pogreska prilikom dodavanja");
            return;
        }
        int tipObjekta = 2; //poslovnica
        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Insert into objekat(ID_Tip_Objekta, Grad, Naziv)VALUES (?,?,?);");
        try {
            ps.setInt(1, tipObjekta);
            ps.setString(2, grad);
            ps.setString(3, naziv);
            int rez = ps.executeUpdate();
            if (rez == 1) {
                porukaLbl.setText("Uspjesno ste unijeli poslovnicu");
            }

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

    }

    public void popuniTrgovce() throws SQLException {
        ObservableList<Poslovnica> poslovnice = FXCollections.observableArrayList();
        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Select * from objekat where ID_Tip_Objekta=2");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            poslovnice.add(new Poslovnica(rs.getInt("ID"), rs.getString("Naziv"), rs.getString("Grad")));
        }

        poslovniceList.setItems(poslovnice);
    }

    public void izlistajPoslovnice() {
        ObservableList<poslovnicaTrgovac> poslovnicaTrgovacList = FXCollections.observableArrayList();
        Baza db = new Baza();
        String selectSQL = "SELECT objekat.ID , Grad,Naziv,Ime FROM `objekat` join korisnik on objekat.ID=korisnik.ID_Objekta where ID_Tip_Objekta=2";

        try {
            PreparedStatement ps = db.exec(selectSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                poslovnicaTrgovac a = new poslovnicaTrgovac(rs.getInt("ID"), rs.getString("Grad"), rs.getString("Naziv"), rs.getString("Ime"));
                poslovnicaTrgovacList.add(a);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        idLbl.setCellValueFactory(new PropertyValueFactory<>("ID"));
        gradLbl.setCellValueFactory(new PropertyValueFactory<>("Grad"));
        nazivLbl.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        trgovacLbl.setCellValueFactory(new PropertyValueFactory<>("trgovac"));

        tabLbl.setItems(poslovnicaTrgovacList);
    }

}
