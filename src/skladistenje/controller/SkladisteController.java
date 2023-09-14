/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladistenje.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import skladistenje.model.Baza;
import skladistenje.model.Korisnik;
import skladistenje.model.Skladiste;
import skladistenje.model.artikal;
import skladistenje.model.poslovnicaTrgovac;
import skladistenje.model.stanjeModel;
import skladistenje.model.zahtjev;

/**
 *
 * @author HP
 */
public class SkladisteController {

    @FXML
    Label loginLbl;
    @FXML
    ComboBox<artikal> ArtikalCom;
    @FXML
    TextField kolicinaField;
    @FXML
    TextField cijenaField;
    @FXML
    Button posaljiBtn;
    @FXML
    Label porukaLbl;
    @FXML
    TableView<stanjeModel> tabId;
    @FXML
    TableColumn<stanjeModel, String> nazivId;
    @FXML
    TableColumn<stanjeModel, String> opisId;
    @FXML
    TableColumn<stanjeModel, Number> kolicinaId;
    @FXML
    TableView<zahtjev> zahtjevLbl;
    @FXML
    TableColumn<zahtjev, Number> ZahtjevId;
    @FXML
    TableColumn<zahtjev, String> NaruciteljId;
    @FXML
    TableColumn<zahtjev, String> ArtiklId;
    @FXML
    TableColumn<zahtjev, String> PoslovnicaId;
    @FXML
    TableColumn<zahtjev, Number> KolicinaId;
    @FXML
    TableColumn<zahtjev, String> PorukaId;
    @FXML
    Label odbijLbl;

    public void PopuniArtikle() {
        loginLbl.setText("Dobrodosli" + " " + Korisnik.ime + " u skladište " + Korisnik.imeObjekta);
        ObservableList<stanjeModel> stanjeArtiklaList = FXCollections.observableArrayList();
        Baza db = new Baza();
        String selectSQL = "SELECT Naziv,Opis,Kolicina FROM `artikal` join stanje on artikal.ID=stanje.ID_Artikla where ID_Objekta=" + Korisnik.IdObjketa;

        try {
            PreparedStatement ps = db.exec(selectSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                stanjeModel a = new stanjeModel(rs.getString("Naziv"), rs.getString("Opis"), rs.getInt("Kolicina"));
                stanjeArtiklaList.add(a);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
        nazivId.setCellValueFactory(new PropertyValueFactory<>("Naziv"));
        opisId.setCellValueFactory(new PropertyValueFactory<>("Opis"));
        kolicinaId.setCellValueFactory(new PropertyValueFactory<>("Kolicina"));
        tabId.setItems(stanjeArtiklaList);
    }

    public void PopuniNarudzbu() throws SQLException {
        ObservableList<artikal> artikli = FXCollections.observableArrayList();
        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Select * from artikal");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            artikli.add(new artikal(rs.getInt("ID"), rs.getString("Naziv"), rs.getString("Opis")));
        }

        ArtikalCom.setItems(artikli);
    }

    public void posaljiNarudzbu(ActionEvent e) throws SQLException {
        int cijena = Integer.parseInt(cijenaField.getText());
        int kolicina = Integer.parseInt(kolicinaField.getText());
        artikal a = ArtikalCom.getValue();
        Baza baza = new Baza();
        PreparedStatement ps = baza.exec("Insert into narudzba(ID_Objekta, ID_Artikla, Kolicina,Cijena)VALUES (?,?,?,?);");
        try {
            ps.setInt(1, Korisnik.IdObjketa);
            ps.setInt(2, a.ID);
            ps.setInt(3, kolicina);
            ps.setInt(4, cijena);

            int rez = ps.executeUpdate();
            if (rez == 1) {
            }

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

        ps = baza.exec("Select * from stanje where ID_Objekta=? and ID_Artikla=?");
        ps.setInt(1, Korisnik.IdObjketa);
        ps.setInt(2, a.ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int ID_Objekta = rs.getInt("ID_Objekta");
            int ID_Artikla = rs.getInt("ID_Artikla");
            int Kolicina = rs.getInt("Kolicina");
            Kolicina += kolicina;
            ps = baza.exec("UPDATE stanje SET Kolicina = ? WHERE ID_Objekta=? AND ID_Artikla=?");
            try {
                ps.setInt(1, Kolicina);
                ps.setInt(2, Korisnik.IdObjketa);
                ps.setInt(3, a.ID);

                int rez = ps.executeUpdate();
                if (rez == 1) {
                    porukaLbl.setText("Uspjesno ste obavili narudzbu");

                }

            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            }

        } else {
            ps = baza.exec("Insert into stanje(ID_Objekta,ID_Artikla,Kolicina)VALUES(?,?,?);");
            try {
                ps.setInt(1, Korisnik.IdObjketa);
                ps.setInt(2, a.ID);
                ps.setInt(3, kolicina);

                int rez = ps.executeUpdate();
                if (rez == 1) {
                    porukaLbl.setText("Uspjesno ste obavili narudzbu");
                }

            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            }
        }
    }

    public void PopuniZahtjev() {
        ObservableList<zahtjev> zahtjevList = FXCollections.observableArrayList();
        Baza db = new Baza();
        String selectSQL = "SELECT artikal.ID,Objekat.ID,Ime,artikal.Naziv, objekat.Naziv , zahtjev.ID,Kolicina,Poruka,Status FROM zahtjev join korisnik on zahtjev.ID_Korisnika=korisnik.ID "
                + "join artikal on zahtjev.ID_Artikla=artikal.ID "
                + "join objekat on zahtjev.ID_Objekta=objekat.ID where status=0";

        try {
            PreparedStatement ps = db.exec(selectSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                zahtjev z = new zahtjev(rs.getInt("zahtjev.ID"), rs.getString("Ime"), rs.getString("artikal.Naziv"), rs.getString("objekat.Naziv"), rs.getInt("Kolicina"), rs.getString("Poruka"));
                z.idArtikla = rs.getInt("artikal.ID");
                z.idObjekta = rs.getInt("Objekat.ID");
                zahtjevList.add(z);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        ZahtjevId.setCellValueFactory(new PropertyValueFactory<>("Id_zahtjeva"));
        NaruciteljId.setCellValueFactory(new PropertyValueFactory<>("Narucitelj"));
        ArtiklId.setCellValueFactory(new PropertyValueFactory<>("Artikl"));
        PoslovnicaId.setCellValueFactory(new PropertyValueFactory<>("Poslovnica"));
        KolicinaId.setCellValueFactory(new PropertyValueFactory<>("Kolicina"));
        PorukaId.setCellValueFactory(new PropertyValueFactory<>("Poruka"));

        zahtjevLbl.setItems(zahtjevList);
    }

    public void prihvatiZahtjev(ActionEvent e) throws SQLException {
        zahtjev z = zahtjevLbl.getSelectionModel().getSelectedItem();
        Baza b = new Baza();
        PreparedStatement ps = b.exec("Select kolicina from stanje where ID_Objekta=" + Korisnik.IdObjketa + " AND ID_Artikla=" + z.idArtikla);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int kolicina = rs.getInt("Kolicina");
            if (kolicina >= z.Kolicina) {
                ps = b.exec("Select * from stanje where ID_Objekta=" + z.idObjekta + " AND ID_Artikla="+z.idArtikla);
                rs=ps.executeQuery();
                if (rs.next()) {
                    int njegovaKolicina = rs.getInt("Kolicina");
                    njegovaKolicina += z.Kolicina;
                    ps = b.exec("Update stanje set kolicina=" + njegovaKolicina + " where ID_Objekta=" + z.idObjekta+" AND ID_Artikla="+z.idArtikla);
                } else {
                    ps = b.exec("INSERT into stanje(ID_Objekta,ID_Artikla,Kolicina) VALUES(?,?,?)");
                    ps.setInt(1, z.idObjekta);
                    ps.setInt(2, z.idArtikla);
                    ps.setInt(3, z.Kolicina);
                    int rez = ps.executeUpdate();
                }
                int mojaKolicina = kolicina - z.Kolicina;
                ps = b.exec("Update stanje set kolicina=" + mojaKolicina + " where ID_Objekta=" + Korisnik.IdObjketa + " AND ID_Artikla=" + z.idArtikla);
                int rez = ps.executeUpdate();
                ps = b.exec("Delete from zahtjev where ID=" + z.Id_zahtjeva);
                rez = ps.executeUpdate();
                PopuniZahtjev();
            }
        }
        try {
            int rez = ps.executeUpdate();
            if (rez == 1) {
                odbijLbl.setText("Uspjesno ste prihvatili zahtjev");
                PopuniZahtjev();
            }

        } catch (Exception exc) {
            odbijLbl.setText(exc.getMessage());
        }

    }

    public void odbijZahtjev(ActionEvent e) {
        zahtjev z = zahtjevLbl.getSelectionModel().getSelectedItem();
        Baza b = new Baza();
        PreparedStatement ps = b.exec("Delete from zahtjev where ID=" + z.Id_zahtjeva);
        try {
            int rez = ps.executeUpdate();
            if (rez == 1) {
                odbijLbl.setText("Uspjesno ste odbili zahtjev");
                PopuniZahtjev();
            }

        } catch (Exception exc) {
            odbijLbl.setText(exc.getMessage());
        }

    }

    public void odjava() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/Login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 850, 700));
        stage.show();
        loginLbl.getScene().getWindow().hide();
    }
}
