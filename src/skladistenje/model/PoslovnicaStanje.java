/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladistenje.model;

/**
 *
 * @author HP
 */
public class PoslovnicaStanje {

    public String Naziv;
    public String Opis;
    public int kolicina;

    public PoslovnicaStanje(String Naziv, String Opis, int kolicina) {
        this.Naziv = Naziv;
        this.Opis = Opis;
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return this.Naziv;
    }

    public String getOpis() {
        return this.Opis;
    }

    public int getKolicina() {
        return this.kolicina;
    }

}
