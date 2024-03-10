## Planen for appen: 

Minimum to interagerande klasser: 
- FUnksjonalitet utover ren datalagring --> prisøkning basert på antall personer som skal overnatte. 
    Hvor mange dager du er der. Dyrere for to dager enn en. 
- Huske å benytte comparator/comparable, eller iterator/iterable.

Lagring --> se mine bookinger

1. Beksrivelse av appen: 
-  Kort om spillet/appen, hva skal de gjøre? hva er målet med spillet? 

- Booking system for et hotell. 
- Den skal kunne legge inn dato, og sted. 
- Se tilgjengelighet 
- Kunne bestille, ved å legge antall rom, personer og netter. 
    - Fylle inn skjema; navn, tlf, epost, fødselsdato.
- Lagre bestilling - skjemaet bli lagret, samt rom, personer og netter. --> Filbehandling. 
- Se bestilling --> Lese filen 
- Endre eller kansellere bestilling. (??)

Målet med appen er å booke hotellrom for ferie destinasjon i Norges storbyer.

2. Grunnklasser: 
- Fortelle kort hva de to (minst to, kan ha flere) grunnklassene skal inneholde, og hvilke klasse som skal ha noen form for kalkulasjoner eller annen logikk. 

- App.java 
- Controller.java 
- Betaling.java --> interface (Husk validering)
    - navn()
    - phoneNumber() 
    - epost()  
- endreBestilling.java --> implementerer betaling.java
    - betaling.java
    - remove() 
- sletteBestilling.java --> implement
- Booking.java 
    Sjekke tilgjengeligheten her. 
    - totalPrice() - regne totalpris 
    - dato() --> netter
    - sted() 
    - rom() 
    - personer()
    - Lagre informasjon fra booking for å videreføre til betaling. (??)
- seBestilling.java --> Lese fra fil 
- Hotell oversikt --> en liste i en fil? med steds rom og tilgjenglighet. ["navn", "rom nr", "tilgjengelig"] 
    

3. Filbehandling: 
- kort om hvilke informasjon som skal leses fra og skrives til fil 

- Hotelloversikt fil: navn, romnr og tilgjenglighet  
- Bestillingsfil: ditt navn, tlf, epost, hotell, romnr, netter, pris

4. Testing:
- Kort om hva som skal testes i appen. Merk: i denne delen av prosjektet som går på testing, krever vi at den viktigste funksjonaliteten dekkes av tester, og ønsker derfor at dere tenker over dette allerede nå 

<<<<<<< HEAD
- Teste om det er mulig å se, endre og slette bestilling. 
=======


Prosjektet er delt opp i følgende deler:
1. Planlegging av prosjektet
2. Grunnklasser og brukergrensesnitt
3. Lagring og filhåndtering
4. Feilhåndtering
5. Testing
6. Dokumentasjon

- vise en enkel plan til studass før vi begynner for at han kan se om den er gjennomførbar: 

Planen kan for eksempel inneholde noe i denne duren:
- Beskrivelse av appen: Kort om spillet/appen, hva skal den gjøre? Hva er målet med spillet?
- Grunnklasser: Fortelle kort hva de to (minimum to, kan ha flere) grunnklassene skal inneholde, og hvilken klasse som skal ha noen form for kalkulasjoner eller annen logikk.
- Filbehandling: Kort om hvilken informasjon som skal leses fra og skrives til fil.
- Testing: Kort om hva som skal testes i appen. Merk: I den delen av prosjektet som går på testing, krever vi at den viktigste funksjonaliteten dekkes av tester, og ønsker derfor at dere tenker over dette allerede nå.

En fungerende app med:
- minimum to interagerende klasser (en med funksjonalitet og en med brukergrensesnitt/interface)
- innkapsling og validering av tilstanden i alle klassene, der det er relevant
- brukergrensesnitt i javaFX, med tilhørende Controller- og App-klasser, anbefalt å bruke FXML for å definere brukergrensesnittet
- organiseres etter Model-View-Controller prinsippet
- mulighet for lesing fra og skriving til fil (lager ny klasse som håndterer dette), må inneholde noe som gir mening å lagre (tilstand i spill, varer, innlegg i dagbok…)
- implementert feilhåndtering hensiktsmessig
- et sett JUnit5-tester som sjekker at funksjonaliteten i appen fungerer som tenkt (minimum 6 tester, hvorav en er fillagring, dokumenter valg)
- dokumentasjon (hvordan appen fungerer, koden skal dokumenteres, hvordan de tekniske kravene er tilfredsstilt ved bruk av  begreper og terminologi i emnet)
>>>>>>> fd401e5f31f3301b9cbe5a7c880ab57f30ea78c2
