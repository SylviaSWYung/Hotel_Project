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

- Teste om det er mulig å se, endre og slette bestilling. 
