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
public class poslovnicaTrgovac {

    public int ID;
    public String Grad;
    public String Naziv;
    public String Trgovac;

    public poslovnicaTrgovac(int ID, String grad, String naziv, String trgovac) {

        this.ID = ID;
        this.Grad = grad;
        this.Naziv = naziv;
        this.Trgovac = trgovac;

    }

    public int getID() {
        return ID;
    }

    public String getGrad() {
        return Grad;
    }

    public String getNaziv() {
        return Naziv;
    }

    public String getTrgovac() {
        return Trgovac;

    }
}
