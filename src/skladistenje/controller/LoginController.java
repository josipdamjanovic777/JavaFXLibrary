/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladistenje.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import skladistenje.model.Baza;
import skladistenje.model.Korisnik;

public class LoginController implements Initializable {
    
    @FXML
    TextField kimeTxt;
    @FXML
    PasswordField lozinkaTxt;
    @FXML
    Label statusLbl;
    Parent root;
    
    public void provjera() {
        
        Baza baza = new Baza();
        String korisnickoIme = kimeTxt.getText();
        String korisnickaLozinka = lozinkaTxt.getText();
        
        if (korisnickoIme.isEmpty() || korisnickaLozinka.isEmpty()) {
            statusLbl.setText("Molimo popunite sve podatke");
            statusLbl.setTextFill(Color.web("#f73c20"));
        }
        PreparedStatement ps = baza.exec("Select * from korisnik where Ime=? AND Sifra=?");
        
        try {
            ps.setString(1, korisnickoIme);
            ps.setString(2, korisnickaLozinka);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Korisnik.Id = rs.getInt("ID");
                Korisnik.ime = rs.getString("Ime");
                Korisnik.IdObjketa = rs.getInt("ID_Objekta");
                
                ps = baza.exec("SELECT Naziv FROM tip_korisnika where ID=" + rs.getInt("ID_Tip_Korisnika"));
                rs = ps.executeQuery();
                rs.next();
                String Rola = rs.getString("Naziv");
                
                ps = baza.exec("SELECT Naziv FROM objekat where ID=" + Korisnik.IdObjketa);
                rs = ps.executeQuery();
                rs.next();
                Korisnik.imeObjekta = rs.getString("Naziv");
                
                if (Rola.compareTo("Administrator") == 0) {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/Administracija.fxml"));
                } else if (Rola.compareTo("Skladistar") == 0) {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/Skladiste.fxml"));
                } else {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("skladistenje/view/Poslovnica.fxml"));
                }
                
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 850, 700));
                stage.show();
                statusLbl.getScene().getWindow().hide();
                
            } else {
                statusLbl.setText("Korisnicko ime ili lozinka nisu odgovarajuci");
                statusLbl.setTextFill(Color.web("#f73c20"));
            }
        } catch (Exception exc) {
            
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
