# mooc.fi stats

Tämä ohjelma lataa mooc.fi kurssin pistetaulun ja tulostaa montako opiskelijaa on päässyt kultakin viikolta läpi. Viikon läpipääsy edellyttää kaikkien edellisten viikkojen läpipääsyä.


###Toiminta

Lataa mooc.fi kurssin pistetaulukon joko HTML:stä (jsoup) tai JSON:sta (gson).    
Tästä datasta tehdään kurssin viikottaiset maksimipisteet sisältävä lista ja lista opiskelijaolioista, jossa jokaisella opiskelijalla on nimi ja viikottaiset pistemäärät.   
Opiskelijan viikottaisia pistemääriä ja viikottaisia maksimipisteitä verrataan joka viikolta ja tulostetaan jokaisen viikon läpipäässeiden (90% viikon maksimipisteistä) opiskelijoiden määrä.


###Käytetyt kirjastot
jsoup
Gson

###Esimerkkitulostus
![Esimerkkitulostus] (http://puu.sh/o4Lsi/6f5ae3fb9a.png)
