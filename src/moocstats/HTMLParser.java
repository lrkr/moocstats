package moocstats;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class HTMLParser {

    private List<String> luetut;
    
    public HTMLParser() {
        this.luetut = new ArrayList<>();
    }
    
    public void HTMLParse(int id) throws Exception {
        String url = "https://tmc.mooc.fi/mooc/courses/" + id + "/points?sort_by=total_points";
        Document doc = Jsoup.connect(url).maxBodySize(10 * 1024 * 1024).get();
        Elements students = doc.getElementsByClass("student");
        for (Element student : students) {
            this.luetut.add(student.text());
        }
    }
    
    public List<Opiskelija> parseOpiskelijat() {
        ArrayList opiskelijat = new ArrayList<>();
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
            opiskelijat.add(new Opiskelija(nimi, tulokset));
        }
        return opiskelijat;
    }
    
    public List<Integer> parseMaxPisteet() {
        List<Integer> maxPisteet = new ArrayList<>();
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
        return maxPisteet;
    }

}
