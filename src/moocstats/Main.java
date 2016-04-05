package moocstats;

public class Main {

    public static void main(String[] args) throws Exception {
        
        /* 
        id:t
            35 = 2016 dl
            27 = 2015 dl
            18 = 2014
         */
        
        /*
            jsonParser = true käyttää JSONParseria
                         false käyttää HTMLParseria
        */        
        Boolean jsonParser = true; 
        double lapipaasyraja = 0.9;
        int kurssiId = 35;

        MoocStats stats = new MoocStats(kurssiId, lapipaasyraja);
        if (!jsonParser) {
            stats.haeDataHTML();
        } else {
            stats.haeDataJSON();
        }
                
        stats.tulostaKaikki();
    }
    
}
