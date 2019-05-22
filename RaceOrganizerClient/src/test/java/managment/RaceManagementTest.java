package managment;

import managment.AuthManagement;
import managment.RaceManagement;
import models.Race;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

class RaceManagementTest {

    @BeforeAll
    static void setUp() {
        AuthManagement.login("a.santiago", "test");
    }

    @AfterAll
    static void tearDown() {
        AuthManagement.logout();
    }

    @Test
    void getRaces() {
        Race[] races = RaceManagement.getRaces();

        Assertions.assertTrue(races.length >= 3);
    }

    @Test
    void addRace() {

        Assertions.assertTrue(
                RaceManagement.addRace("Test", "Test", "Test", new Date(), 10, 10));
    }

    @Test
    void getRace() {
        Race race = RaceManagement.getRace(1);

        Assertions.assertEquals(race.getEdition(), "2019 Brookyn's Marathon");
        Assertions.assertEquals(race.getSponsor(), "99th Precinct");
        Assertions.assertEquals(race.getPrice(), 10);
        Assertions.assertEquals(race.getPrize(), 75);
    }


    @Test
    void addUserToRace() {

        Assertions.assertTrue(RaceManagement.addUserToRace("jim.smith", 1, 1));
        Assertions.assertTrue(RaceManagement.addUserToRace("a.santiago", 3, 2));
    }
}