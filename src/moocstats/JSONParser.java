package moocstats;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.net.URL;

public class JSONParser {

    private Map data;

    public void JSONParse(int id) throws Exception {
        Gson gson = new Gson();
        String url = "https://tmc.mooc.fi/mooc/courses/" + id + "/points.json?api_version=7";
        Reader lukija = new InputStreamReader(new URL(url).openStream());
        data = gson.fromJson(lukija, Map.class);
    }

    public List<Integer> parseMaxPisteet() {
        List<Integer> maxPisteet = new ArrayList<>();
        List maxPisteData = (ArrayList) data.get("sheets");
        for (Object object : maxPisteData) {
            Map<String, Double> maxPiste = (Map) object;
            maxPisteet.add(maxPiste.get("total_available").intValue());
        }
        return maxPisteet;
    }

    public List<Opiskelija> parseOpiskelijat() {
        List<Opiskelija> opiskelijat = new ArrayList<>();
        Map<String, Map> opiskelijaData = (Map) data.get("awarded_for_user_and_sheet");
        for (String nimi : opiskelijaData.keySet()) {
            ArrayList<Integer> arvosanat = new ArrayList<>();
            Map<String, Double> arvosanaData = opiskelijaData.get(nimi);
            for (int i = 1; i <= arvosanaData.keySet().size(); i++) {
                Double arvosana = arvosanaData.get(Integer.toString(i));
                if (arvosana != null) {
                    arvosanat.add(arvosana.intValue());
                } else {
                    arvosanat.add(0);
                }
            }
            opiskelijat.add(new Opiskelija(nimi, arvosanat));
        }
        return opiskelijat;
    }

}
