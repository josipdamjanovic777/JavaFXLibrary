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
public class zahtjev {

    public int Id_zahtjeva;
    public String Narucitelj;
    public String Artikl;
    public String Poslovnica;
    public int Kolicina;
    public String Poruka;
    public int idArtikla;
    public int idObjekta;

    public zahtjev(int Id_zahtjeva, String Narucitelj, String Artikl, String Poslovnica, int kolicina, String Poruka) {
        this.Id_zahtjeva = Id_zahtjeva;
        this.Narucitelj = Narucitelj;
        this.Artikl = Artikl;
        this.Poslovnica = Poslovnica;
        this.Kolicina = kolicina;
        this.Poruka = Poruka;
    }

    public int getId_zahtjeva() {
        return Id_zahtjeva;
    }

    public String getNarucitelj() {
        return Narucitelj;
    }

    public String getArtikl() {
        return Artikl;
    }

    public String getPoslovnica() {
        return Poslovnica;
    }

    public int getKolicina() {
        return Kolicina;
    }

    public String getPoruka() {
        return Poruka;
    }
}
