package moocstats;


import java.util.ArrayList;

public class Opiskelija {
    private String nimi;
    private ArrayList<Integer> tulokset;
    private boolean lapi;
    
    public Opiskelija(String nimi, ArrayList<Integer> tulokset) {
        this.nimi = nimi;
        this.tulokset = tulokset;
        this.lapi = true;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public ArrayList<Integer> getTulokset() {
        return this.tulokset;
    }
    
    public boolean getLapi() {
        return this.lapi;
    }
    
    public void fail() {
        this.lapi = false;
    }
    
    public String toString() {
        return this.nimi + "\t" + this.tulokset;
    }
    
}
