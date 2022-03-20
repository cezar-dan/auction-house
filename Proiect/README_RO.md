# README

Proiectul implementeaza simularea unei case de licitatii. Detaliile exacte referitoare la modul de functionare a proiectului se pot gasi in diagrama de stare UML atasata.

## DETALII DE IMPLEMENTARE

Explicatiile referitoare la functionarea metodelor se pot gasi in JavaDoc, in sectiunea urmatoare fiind prezentate doar obiectivele atinse de mine in rezolvarea proiecutlui.

## Multithreading

  - Pentru multithreading, am implementat problema Producer-Consumer.
  - Astfel, am preluat lista sincronizata scrisa in laborator si am modificat-o astfel incat sa se potriveasca in proiect.
  -  Rolul consumatorului este preluat de catre broker, care poate sterge produse din lista de produse (si de client oarecum, acesta putand vizualiza produsele), iar rolul de producator este preluat de catre administrator care adauga produse in lista.
  - Asadar, simularea licitatiilor se desfasoare pe doua fire de executie, unul al administratorului care tot incearca sa adauge produse, iar unul al simularii in sine.
  -  Licitatiile nu pot avea loc concomitent, si de aceea am ales sa rulez tot procesul de licitatie pe un singur thread.

## Design patterns

### Singleton

  Am folosit Singleton atat pentru CasaDeLicitatii cat si pentru clasa Simulare, intrucat o singura instanta de Simulare si o singura instanta de casa ar trebui sa existe in proiect. Aplicatia nu va gestiona mai multe case de licitatii simultan, si astfel nu vor fi necesare mai multe simulari.

### Builder

  Am folosit Builder in constructia produselor. Produsele fiind de mai multe feluri, am apelat la un Builder de tip abstract, care sa se potriveasca pe toate tipurile de produse, pentru a evita codul duplicat. Builder a fost implementat pentru a facilita procesul de constructie a produselor diferite.

### Strategy

  Am folosit Strategy in calculul functiei de comision, pentru a implementa usor diferite formule de calcul pentru acelasi algoritm.

### Adapter

  Am folosit Adapter pentru citirea produselor din fisier. Desi programul meu citeste doar fisiere de tip CSV, aplicarea acestui design pattern permite extinderea usoara si eleganta a aplicatiei si pentru tipuri diferite de input.

### Facade

  Am aplicat un wrapper peste ArrayList, Lock si Condition si am creat clasa ListaSincronizata. Aceasta clasa ofera o interfata simplificata, mascand detaliile de implementare. Clientul poate astfel utiliza clasa fara sa inteleaga neaparat ce se intampla "sub capota".

## Genericitate

  Genericitatea a fost folosita atat in clasa BuilderProdus, cat si in clasa ListaSincronizata. Genericitatea a fost folosita in BuilderProdus pentru a putea extinde usor Builder-ul in cazul adaugarii unui nou tip de produs, iar in ListaSincronizata pentru a permite utilizatorului sa creeze liste de orice tip de date dorit.

## Testarea aplicatiei

  Aplicatia mea, ruland o simulare, este randomizata pentru a produce un alt rezultat de fiecare data. Asta nu inseamna insa ca are un comportament haotic, simularea respectand regulile impuse in cerinta. Asadar, pentru cele 10 teste, am rulat algoritmul de 10 ori si am obtinut 10 outputuri diferite. Se poate observa ca participantii liciteaza corect, acestia nedepasind bugetul propriu, iar produsele sunt vandute doar daca este depasit pretul minim. Se poate observa totodata din output cum Administatorul adauga produse, iar Brokerul le sterge. De asemenea, la vanzarea unui produs, brokerii isi pastreaza un comision, formula de calcul fiind aplicata corect.

## Conceptele POO

  In dezvoltarea aplicatiei am atins toate cele 4 concepte de baza ale POO:

  ### Incapsulare
  - campuri si metode private, datele fiind protejate.
  ### Mostenire
  - exista diferite tipuri de produse/clienti/angajati concreti care mostenesc o clasa abstracta.
  ### Abstractizare
  - utilizarea interfetelor si a claselor abstracte.
  ### Polimorfism
  - listele de produse sau clienti contin diferite subclase ale tipurilor din listele respective (Produs -> Tablou/Mobila/Bijuterie).

## Coding style

  Codul este modularizat, numele variabilelor sunt intuitive, iar metodele sunt relativ scurte, usor de inteles si de urmarit. Metodele si clasele respecta princippile SOLID, in special SRP.

## Functionalitati moderne

  In proiect sunt utilizate stream-uri pentru gasirea de elemente in liste.

## Comunicare client-broker-casa

  Dupa cum se observa si din diagrama de stare atasata, clientul nu comunica cu casa decat cand solicita inceperea unei licitatii. In rest, brokerul apare ca un intermediar intre casa si clienti.
