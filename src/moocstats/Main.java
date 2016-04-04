package moocstats;

public class Main {

    public static void main(String[] args) throws Exception {

        /* 
        id:t
            35 = 2016 dl
            27 = 2015 dl
            18 = 2014
         */
        double lapipaasyraja = 0.9;
        int kurssiId = 35;

        MoocStats stats = new MoocStats(kurssiId, lapipaasyraja);
        stats.haeData();
        stats.tulostaKaikki();

    }
}
