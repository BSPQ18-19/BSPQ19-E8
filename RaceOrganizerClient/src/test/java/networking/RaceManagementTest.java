package networking;

import managment.RaceManagement;
import models.Race;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RaceManagementTest {

    @Test
    void getRaces() {
        Race[] races = RaceManagement.getRaces();

        Assertions.assertTrue(races.length >= 2);
    }

//    @Test
//    void addRace() {
//    }

    @Test
    void getRace() {
        Race race = RaceManagement.getRace(1);

        Assertions.assertEquals(race.getEdition(), "2019 Brookyn's Marathon");
        Assertions.assertEquals(race.getSponsor(), "99th Precinct");
        Assertions.assertEquals(race.getPrice(), 10);
        Assertions.assertEquals(race.getPrize(), 15);
    }


//    @Test
//    void addUserToRace() {
//    }
}