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
public class Poslovnica {

    public int id;
    public String naziv;
    public String grad;

    public Poslovnica(int id, String naziv, String grad) {
        this.id = id;
        this.naziv = naziv;
        this.grad = grad;
    }

    @Override
    public String toString() {

        return grad + "  " + naziv;
    }
}
