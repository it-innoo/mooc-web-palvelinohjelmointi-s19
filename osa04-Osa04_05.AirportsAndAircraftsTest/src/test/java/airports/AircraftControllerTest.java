package airports;

import java.util.List;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AircraftControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AircraftRepository mockRepository;

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("aircrafts", "airports"));
    }

    @Test
    public void postIsRedirected() throws Throwable {
        mockMvc.perform(post("/aircrafts")
                .param("name", "HA-LOL"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/aircrafts"));

        List<Aircraft> lista = mockRepository.findAll();
        boolean loytyi = false;
        for (Aircraft ac : lista) {
            if ("HA-LOL".equals(ac.getName())) {
                loytyi = true;
                break;
            }
        }
        assertTrue("HA-LOL not in repository", loytyi);
    }

    @Test
    public void postIsAddedToList() throws Throwable {
        mockMvc.perform(post("/aircrafts")
                .param("name", "XP-55"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/aircrafts"));

        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("aircrafts", hasSize(1)))
                .andExpect(model().attribute("aircrafts", hasItem(
                        allOf(
                                hasProperty("name", is("XP-55"))
                        )
                )));
    }
}
