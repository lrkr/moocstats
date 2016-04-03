package moocstats;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class HTMLParser {    
    
    public void HTMLParse (ArrayList luetut, int id) throws Exception {        
        String url = "https://tmc.mooc.fi/mooc/courses/"+ id + "/points?sort_by=total_points";
        Document doc = Jsoup.connect(url).maxBodySize(10*1024*1024).get();
        Elements students = doc.getElementsByClass("student");
        for (Element student : students) {
            luetut.add(student.text());
        }
    }
}
