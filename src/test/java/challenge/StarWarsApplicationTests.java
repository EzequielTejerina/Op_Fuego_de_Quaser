package challenge;

import challenge.model.Coordinate;
import challenge.services.CommunicationService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StarWarsApplicationTests {

    @Autowired
    private CommunicationService communicationService;

    @Test
    void getMessage() {
        String[] messages = {"", "Este", " " , "es", "un", "mensaje" , "", ""};
        assertThat(this.communicationService.getMessage(messages)).isEqualTo("Este es un mensaje");
    }
    @Test
    void getLocation(){
        Float[] distances = {100.0F, 150.0F, 200.0F};
        Coordinate coord = this.communicationService.getLocation(distances);
        assertThat(coord).isNotNull();
        assertThat(coord.getX()).isEqualTo(-454.88315F);
        assertThat(coord.getY()).isEqualTo(-289.24387F);
    }

}
