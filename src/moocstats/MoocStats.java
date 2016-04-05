package moocstats;

import java.util.ArrayList;
import java.util.List;

public class MoocStats {

    private List<Opiskelija> opiskelijat;
    private List<Integer> maxPisteet;
    private List<Opiskelija> lapipaasseet;
    private int kurssiId;
    private HTMLParser HTMLParser;
    private JSONParser JSONParser;
    private double lapipaasyraja;

    public MoocStats(int kurssiId, double lapipaasyraja) {
        this.opiskelijat = new ArrayList<>();
        this.maxPisteet = new ArrayList<>();
        this.lapipaasseet = new ArrayList<>();
        this.HTMLParser = new HTMLParser();
        this.JSONParser = new JSONParser();
        this.kurssiId = kurssiId;
        this.lapipaasyraja = lapipaasyraja;

    }

    public void haeDataHTML() throws Exception {
        HTMLParser.HTMLParse(kurssiId);
        maxPisteet = HTMLParser.parseMaxPisteet();
        opiskelijat = HTMLParser.parseOpiskelijat();
    }
    
    public void haeDataJSON() throws Exception {
        JSONParser.JSONParse(kurssiId);
        maxPisteet = JSONParser.parseMaxPisteet();        
        opiskelijat = JSONParser.parseOpiskelijat();
    }
    
    public void tulostaKaikki() {
        for (int i = 1; i <= maxPisteet.size(); i++) {
            failViikko(i);
            tulostaViikko(i);
        }
    }
    
    public void tulostaViikko(int viikko) {
        lapipaasseet.clear();
        for (Opiskelija opiskelija : opiskelijat) {
            if (opiskelija.getLapi() == true) {
                lapipaasseet.add(opiskelija);
            }
        }
        System.out.println("viikko " + viikko + "\t" + lapipaasseet.size());
    }

    public void failViikko(int viikko) {
        for (Opiskelija opiskelija : opiskelijat) {
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
