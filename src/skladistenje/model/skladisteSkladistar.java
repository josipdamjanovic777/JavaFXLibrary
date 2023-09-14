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
public class skladisteSkladistar {

    public int ID;
    public String Grad;
    public String Naziv;
    public String Skladistar;

    public skladisteSkladistar(int ID, String Grad, String Naziv, String Skladistar) {
        this.ID = ID;
        this.Grad = Grad;
        this.Naziv = Naziv;
        this.Skladistar = Skladistar;
    }

    public int getID() {
        return this.ID;
    }

    public String getGrad() {
        return this.Grad;
    }

    public String getNaziv() {
        return this.Naziv;
    }

    public String getSkladistar() {
        return this.Skladistar;
    }

}
