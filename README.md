# mooc.fi stats

Tämä ohjelma lataa mooc.fi kurssin pistetaulun ja tulostaa montako opiskelijaa on päässyt kultakin viikolta läpi. Viikon läpipääsy edellyttää kaikkien edellisten viikkojen läpipääsyä.


###Toiminta
Lataa mooc.fi pistetaulun HTML sivun jsoup:ia käyttäen ja tallentaa student elementit listaan. Oletuskurssina on id 35 (2016 dl).
Tämän listan ensimmäisestä rivistä haetaan viikottaiset maksimipisteet.
Koko lista käydään läpi ja joka rivistä tehdään opiskelijaolio, jolla on nimi, kaikkien viikkojen arvosanat ja läpi totuusarvo, joka tässä vaiheessa on aina true. Opiskelijaoliot tallennetaan omaan listaan.
Käydään opiskelijaoliolista läpi viikoittain alottaen ensimmäisestä viikosta ja päättyen viimeiseen viikkoon (tämä saadaan maksimipistelistan koosta). Joka läpikäynnissä verrataan opiskelijan sen viikon pisteitä sen viikon maksimipisteisiin ja jos pisteitä ei ole tarpeeks (<90%), muutetaan lapi-muuttuja falseksi. Kun opiskelijalista on käyty läpi tulostetaan kyseessäolevan viikon läpipäässeiden määrä, joka saadaan käymällä opiskelijaoliolista läpi ja laskemalla vain jos lapi == true.


###Käytetyt kirjastot
jsoup


###Esimerkkitulostus
    viikko 1  	   671
    viikko 2         526
    viikko 3  	   439
    viikko 4  	   403
    viikko 5  	   366
    viikko 6  	   339
    viikko 9  	   201
    viikko 7  	   324
    viikko 8  	   313
    viikko 10 	   92
    viikko 11 	   14
