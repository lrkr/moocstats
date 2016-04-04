package moocstats;

import java.util.ArrayList;
import java.util.List;

public class MoocStats {

    private List<Opiskelija> lista;
    private List<Integer> maxPisteet;
    private List<Opiskelija> lapipaasseet;
    private int kurssiId;
    private HTMLParser HTMLParser;
    private double lapipaasyraja;

    public MoocStats(int kurssiId, double lapipaasyraja) {
        this.lista = new ArrayList<>();
        this.maxPisteet = new ArrayList<>();
        this.lapipaasseet = new ArrayList<>();
        this.HTMLParser = new HTMLParser();
        this.kurssiId = kurssiId;
        this.lapipaasyraja = lapipaasyraja;

    }

    public void haeData() throws Exception {
        HTMLParser.HTMLParse(kurssiId);
        maxPisteet = HTMLParser.parseMaxPisteet();
        lista = HTMLParser.parseOpiskelijat();
    }
    
    public void tulostaKaikki() {
        for (int i = 1; i <= maxPisteet.size(); i++) {
            failViikko(i);
            tulostaViikko(i);
        }
    }
    
    public void tulostaViikko(int viikko) {
        lapipaasseet.clear();
        for (Opiskelija opiskelija : lista) {
            if (opiskelija.getLapi() == true) {
                lapipaasseet.add(opiskelija);
            }
        }
        System.out.println("viikko " + viikko + "\t" + lapipaasseet.size());
    }

    public void failViikko(int viikko) {
        for (Opiskelija opiskelija : lista) {
            if (opiskelija.getLapi() == false) {
                continue;
            }
            List<Integer> tulokset = opiskelija.getTulokset();
            /*
            Jos jostain syystä opiskelijalla ei ole tarpeeksi arvosanoja. Voi 
            tapahtua jos html parser ei jostain syystä saa kaikkia rivejä ja
            viimeinen jää vajaaksi.
             */
            if (tulokset.size() < viikko) {
                opiskelija.fail();
                continue;
            }
            for (int i = 0; i < viikko; i++) {
                if ((double) tulokset.get(i) / maxPisteet.get(i) < lapipaasyraja) {
                    opiskelija.fail();
                    break;
                }
            }
        }
    }
    
}
