/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladistenje.model;

/**
 *
 * @author Filip
 */
public class stanjeModel {

    public String Naziv;
    public String Opis;
    public int Kolicina;

    public stanjeModel(String naziv, String opis, int kolicina) {
        this.Naziv = naziv;
        this.Opis = opis;
        this.Kolicina = kolicina;
    }

    public String getNaziv() {
        return Naziv;
    }

    public String getOpis() {
        return Opis;
    }

    public int getKolicina() {
        return Kolicina;
    }
}
