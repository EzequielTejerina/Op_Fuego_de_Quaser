package challenge;

import challenge.dto.SatelliteDTO;
import challenge.repositories.SatelliteRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SatelliteMongoRepositoryTest {

    @Autowired
    private SatelliteRepository satelliteRepository;

    @Before
    public void setUp() throws Exception {
        String[] messages1 = {"este", "", "", "mensaje", ""};
        String[] messages2 = {"", "es", "", "", "secreto"};
        String[] messages3 = {"este", "", "un", "", ""};

        SatelliteDTO satellite1= new SatelliteDTO("kenobi", 100.0F, messages1);
        SatelliteDTO satellite2= new SatelliteDTO("skywalker", 115.5F, messages2);
        SatelliteDTO satellite3= new SatelliteDTO("sato", 142.7F, messages3);

        assertNull(satellite1.get_id());
        assertNull(satellite2.get_id());
        assertNull(satellite3.get_id());
        this.satelliteRepository.save(satellite1);
        this.satelliteRepository.save(satellite2);
        this.satelliteRepository.save(satellite3);
        assertNotNull(satellite1.get_id());
        assertNotNull(satellite2.get_id());
        assertNotNull(satellite3.get_id());
    }

    @Test
    public void testFetchData(){
        Iterable<SatelliteDTO> satellites = this.satelliteRepository.findAll();
        int count = 0;
        for(SatelliteDTO p : satellites){
            count++;
        }
        assertEquals(count, 3);
    }
    @Test
    public void testDataUpdate(){
        SatelliteDTO satelliteK = this.satelliteRepository.findByName("kenobi");
        satelliteK.setDistance(40.0F);
        this.satelliteRepository.save(satelliteK);
        SatelliteDTO satelliteK_U= this.satelliteRepository.findByName("kenobi");
        assertNotNull(satelliteK_U);
        assertThat(satelliteK_U.getDistance()).isEqualTo(40.0F);
    }
    @After
    public void tearDown() throws Exception {
        this.satelliteRepository.deleteAll();
    }
}
