package airports;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AirportServiceTest {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportService airportService;

    @After
    public void clear() {
        airportRepository.deleteAll();
    }

    @Before
    public void populate() {
        Airport ap1 = new Airport();
        Airport ap2 = new Airport();
        Airport ap3 = new Airport();
        
        ap1.setIdentifier("A");
        ap1.setName("ABC");
        airportRepository.save(ap1);
        ap2.setIdentifier("D");
        ap2.setName("DEF");
        airportRepository.save(ap2);
        ap3.setIdentifier("G");
        ap3.setName("GHJ");
        airportRepository.save(ap3);

    }

    @Test
    public void canCreateAirport() throws Throwable {
        long count = airportRepository.count();
        assertTrue("Database should contain" +count+ " items", count == 3);

        airportService.create("GOT", "Batman Airport");
        count = airportRepository.count();
        assertTrue("Database should contain " +count+  "items", count == 4);
    }

    @Test
    public void listsAllAirports() throws Throwable {
        List<Airport> airports = airportService.list();
        int size = airports.size();
        
        assertTrue("Listan koko pitäisi olla 3, nyt oli " + size,  size == 3);
    }

    @Test
    public void noDuplicatesInReposiry() throws Throwable {
        List<Airport> airports = airportService.list();
        int size = airports.size();
        
        assertTrue("Listan koko pitäisi olla 3, nyt oli " + size,  size == 3);
        airportService.create("A", "ABC");

        airports = airportService.list();
        size = airports.size();
        assertTrue("Listan koko pitäisi olla 3, nyt oli " + size,  size == 3);
    }
}
