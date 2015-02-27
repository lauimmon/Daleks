**Aihe:** Daleks-peli

**Kuvaus:** Daleks on peli, jossa pelaajan tavoitteena on päästä dalekeja karkuun ja saada dalekit törmäämään toisiinsa. Dalekit kuolevat törmätessään toisiinsa tai kuolleisiin dalekeihin. Pelaaja voittaa jos kaikki dalekit kuolevat, ja häviää jos dalek saa pelaajan kiinni. Pelissä on tietynkokoinen pelilauta, jolla pelaaja ja dalekit liikkuvat.

Pelaaja voi liikkua kahdeksaan suuntaan (ylös, alas, oikealle, vasemalle ja sivuttain), ja jokaisen siirron jälkeen dalekit liikkuvat yhden ruudun pelaajaa päin. Liikkumisen sijaan pelaaja voi käyttää siirtonsa pysymällä paikallaan, räjäyttämällä pommin tai teleporttaamalla. Teleporttaamalla pelaaja liikkuu satunnaiseen ruutuun laudalla ja pommilla pelaaja voi räjäyttää kaikki lähimmissä ruuduissa olevat dalekit. Teleportteja ja pommeja on pelaajan käytössä rajattu määrä.

**Ohjelman rakenne:** Ohjelmassa on luokka Peli pelilogiikalle. Peli sisältää pelilaudan, pelaajan ja useita dalekeja. Pelaaja ja Dalek -luokat perivät Liikkuva-luokan, jossa on toteutettu näiden liikkuminen. Liikkuvilla on ruutu, joka kuvaa Liikkuvan sijaintia pelilaudalla, sekä tyyppi. Pelilauta-luokassa voidaan tarkastella pelilaudalla sijaitsevia ruutuja.

Peli-luokassa hoidetaan kaikki pelin toiminnot: Liikkuvien liikuttaminen, pommin räjäytys, pelaajan teleporttaus, liikkuvien lisäys ja poisto, sekä ruudun tarkistaminen useampien liikkuvien varalta ja tästä seuraavat toimenpiteet.

Käyttöliittymässä on jatkuvasti käynnissä uudelleenpiirto. Kun käyttäjä painaa näppäimiä, tapahtuu peli-oliossa muutoksia. Jos muutokset johtavat häviöön tai voittoon, kierroksen pisteet lisätään pistesaldoon ja käyttäjälle ilmoitetaan häviöstä/voitosta ja uusi kierros alkaa.
