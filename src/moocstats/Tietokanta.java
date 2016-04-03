package moocstats;

import java.util.ArrayList;

public class Tietokanta {

    private ArrayList<Opiskelija> lista;
    private ArrayList<Integer> maxPisteet;
    private ArrayList<String> luetut;
    private ArrayList<Opiskelija> lapipaasseet;
    private int kurssiId;
    private HTMLParser HTMLParser;
    private double lapipaasyraja;

    public Tietokanta(int kurssiId, double lapipaasyraja) {
        this.lista = new ArrayList<>();
        this.maxPisteet = new ArrayList<>();
        this.luetut = new ArrayList<>();
        this.lapipaasseet = new ArrayList<>();
        this.HTMLParser = new HTMLParser();
        this.kurssiId = kurssiId;
        this.lapipaasyraja = lapipaasyraja;

        //System.out.println(luetut);
        //this.lukija = new Lukija("mooc2016.txt");
        //maxPisteet.addAll(Arrays.asList(34, 35, 37, 40, 34, 31, 30, 32, 34, 32, 33)); // 2016
        //maxPisteet.addAll(Arrays.asList(34, 35, 36, 36, 32, 34, 26, 25, 29, 28, 30, 27, 29, 29)); // 2015
        //maxPisteet.addAll(Arrays.asList(29, 35, 33, 31, 30, 35, 25, 29, 28, 30, 27, 30)); // 2014
    }

    public void haeData() throws Exception {
        HTMLParser.HTMLParse(luetut, kurssiId);
        maxPisteet();
        parseOpiskelijat();
    }

    public void maxPisteet() {
        String rivi = luetut.get(0);
        String[] palat = rivi.split(" ");
        for (int i = 2; i < palat.length; i++) {
            String pala = palat[i];
            for (int j = 0; j < pala.length(); j++) {
                if (pala.charAt(j) == '/') {
                    int apu = j + 1;
                    String maxPiste = "";
                    while (true) {
                        if (apu >= pala.length()) {
                            break;
                        } else {
                            maxPiste = maxPiste + pala.charAt(apu);
                        }
                        apu++;
                    }
                    maxPisteet.add(Integer.parseInt(maxPiste));
                }
            }
        }
        maxPisteet.remove(maxPisteet.size() - 1);
    }

    public void tulostus(int viikko) {
        lapipaasseet.clear();
        for (Opiskelija opiskelija : lista) {
            if (opiskelija.getLapi() == true) {
                lapipaasseet.add(opiskelija);
            }
        }
        System.out.println("viikko " + viikko + "\t" + lapipaasseet.size());
    }

    public void fail(int viikko) {
        for (Opiskelija opiskelija : lista) {
            if (opiskelija.getLapi() == false) {
                continue;
            }
            ArrayList<Integer> tulokset = opiskelija.getTulokset();
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

    public void parseOpiskelijat() {
        for (String luettu : luetut) {
            String[] palat = luettu.split(" ");
            String nimi = palat[1];
            ArrayList tulokset = new ArrayList<>();
            for (int i = 2; i < palat.length - 1; i++) {
                String[] pala = palat[i].split("/");
                try {
                    tulokset.add(Integer.parseInt(pala[0]));
                } catch (Exception NumberFormatException) {
                    /*
                    Jos nimessä on väli, tämä lisää toisen nimen. Toisen välin
                    jälkeiset sanat (nimet) ignorataan
                     */
                    nimi = palat[1] + " " + palat[2];
                }
            }
            lista.add(new Opiskelija(nimi, tulokset));
        }
    }

    public int getViikot() {
        return this.maxPisteet.size();
    }

}
