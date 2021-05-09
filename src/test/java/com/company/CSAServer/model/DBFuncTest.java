package com.company.CSAServer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.company.CSAServer.model.DBFunc.*;
import static org.junit.jupiter.api.Assertions.*;

class DBFuncTest {

    @Test
    @DisplayName("Laden der Kunden aus der DB")
    void getKundenTest() {
        try {
            Kunde neuerKunde = new Kunde("max", "1234", "Musermann", "Max", 12345, "Berlin", "Deutschland", "Musterstraße 12");
            insertKundePS(neuerKunde);

            ArrayList<Kunde> kunden = new ArrayList<>();
            getKunden(kunden);

            assertEquals(kunden.get(kunden.size()-1).getUsername(), neuerKunde.getUsername());
            assertEquals(kunden.get(kunden.size()-1).getPassword(), neuerKunde.getPassword());
            assertEquals(kunden.get(kunden.size()-1).getName(), neuerKunde.getName());
            assertEquals(kunden.get(kunden.size()-1).getVorname(), neuerKunde.getVorname());
            assertEquals(kunden.get(kunden.size()-1).getPlz(), neuerKunde.getPlz());
            assertEquals(kunden.get(kunden.size()-1).getOrt(), neuerKunde.getOrt());
            assertEquals(kunden.get(kunden.size()-1).getLand(), neuerKunde.getLand());
            assertEquals(kunden.get(kunden.size()-1).getStrasseHnr(), neuerKunde.getStrasseHnr());
        } catch (Exception e) {
            fail("Fehler beim Laden der Kunden aus der DB.");
        }
    }

    @Test
    @DisplayName("Laden der Artikel aus der DB")
    void getArtikelTest() {
        try {
            Artikel neuerArtikel = new Artikel("Springseit 1000m", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTwVKYsODCXTcPjbbmibIAkvaBGXLIrmX2H0peDgRAEhGQbsw0V6KZoQ6s4TAmqvlLi2qQ51Q&usqp=CAc", 399.95);
            insertArtikelPS(neuerArtikel);

            ArrayList<Artikel> artikel = new ArrayList<>();
            getArtikel(artikel);

            assertEquals(artikel.get(artikel.size()-1).getBezeichnung(), neuerArtikel.getBezeichnung());
            assertEquals(artikel.get(artikel.size()-1).getBildURL(), neuerArtikel.getBildURL());
            assertEquals(artikel.get(artikel.size()-1).getPreis(), neuerArtikel.getPreis());
        } catch (Exception e) {
            fail("Fehler beim Laden der Artilel aus der DB.");
        }
    }

    @Test
    @DisplayName("Akrualisieren der Entitäten der DB")
    void updateEntityTest() {
        try {
            Artikel neuerArtikel = new Artikel("Springseit 1000m", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTwVKYsODCXTcPjbbmibIAkvaBGXLIrmX2H0peDgRAEhGQbsw0V6KZoQ6s4TAmqvlLi2qQ51Q&usqp=CAc", 399.95);
            insertArtikelPS(neuerArtikel);

            Kunde neuerKunde = new Kunde("max", "1234", "Musermann", "Max", 12345, "Berlin", "Deutschland", "Musterstraße 12");
            insertKundePS(neuerKunde);

            ArrayList<Artikel> artikel = new ArrayList<>();
            getArtikel(artikel);
            ArrayList<Kunde> kunden = new ArrayList<>();
            getKunden(kunden);

            Artikel zuAendernderArtikel = artikel.get(artikel.size()-1);
            Kunde zuAendernderKunde = kunden.get(kunden.size()-1);

            zuAendernderArtikel.setBezeichnung("Neues Springseil");
            zuAendernderArtikel.setBildURL("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRekLluLNOl7tZcrjNdYbo-1esk5xxIUQZz8Ulma7O33sWp5QAOrT4kkFlM3JMT8ymL3SMFI0YFYQ&usqp=CAc");
            zuAendernderArtikel.setPreis(299.96);

            zuAendernderKunde.setVorname("Maxim");
            zuAendernderKunde.setName("Menschler");

            updateEntity(zuAendernderArtikel);
            updateEntity(zuAendernderKunde);

            artikel = new ArrayList<>();
            getArtikel(artikel);

            kunden = new ArrayList<>();
            getKunden(kunden);

            Artikel geaenderterArtikel = artikel.get(artikel.size()-1);
            Kunde geaenderterKunde = kunden.get(kunden.size()-1);

            assertEquals(geaenderterArtikel.getBezeichnung(), zuAendernderArtikel.getBezeichnung());
            assertEquals(geaenderterArtikel.getBildURL(), zuAendernderArtikel.getBildURL());
            assertEquals(geaenderterArtikel.getPreis(), zuAendernderArtikel.getPreis());

            assertEquals(geaenderterKunde.getName(), zuAendernderKunde.getName());
            assertEquals(geaenderterKunde.getVorname(), zuAendernderKunde.getVorname());

        } catch (Exception e) {
            fail("Fehler beim Aktualisieren der Entitäten der DB");
        }
    }

    @Test
    @DisplayName("Einloggen der Kunden")
    void loginKundeTest() {
        try {
            Kunde eingeloggterKunde = loginKunde("anna", "1234");
            Kunde nichtExistierenderKunde = loginKunde("", "");

            assertNotNull(eingeloggterKunde);
            assertNull(nichtExistierenderKunde);

        } catch (Exception e) {
            fail("Fehler beim Laden der Artilel aus der DB.");
        }
    }
}