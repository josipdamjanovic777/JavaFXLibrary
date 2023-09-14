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
public class artikal {

    public int ID;
    public String naziv;
    public String opis;
    
    public artikal(int ID, String naziv,String opis) {
        this.ID =ID ;
        this.naziv = naziv;
        this.opis = opis;
    }
    
    
    public int getID(){
        return this.ID;
    }
    public String getNaziv(){
        return this.naziv;
    }
    public String getOpis(){
        return this.opis;
    }
    @Override
    public String toString(){
        return naziv;
    }
}
