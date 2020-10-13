package challenge.services;

import challenge.enums.SateliteEnum;
import challenge.model.Coordinate;
import challenge.request.TopSecretRequest;
import challenge.response.ObjectResponse;
import challenge.utils.ArrayUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class CommunicationService {

    private Logger logger = LogManager.getLogger(getClass());

    public Object topSecret(String requestBody) {
        Gson gson = new Gson();
        ObjectResponse response = new ObjectResponse();
        TopSecretRequest request = gson.fromJson(requestBody, TopSecretRequest.class);

        return gson.toJson(response);
    }

    public Object topSecretSplit(String satelliteName, String requestBody) {
        //JSONObject jsonResponse = new JSONObject();


        return "";
    }

    public String getMessage(final String... messages){
        String message = "";
        List<String> mappingMessages = Arrays.asList(messages);

        logger.info("Se recibio el mensaje ".concat(ArrayUtil.toString(messages)).concat(" del emisor."));

        Iterator<String> it = mappingMessages.iterator();
        while(it.hasNext()){
            String word = it.next();
            if(!word.isEmpty() && !word.trim().equals("")){
                message = message.concat(" ").concat(word);
            }
        }

        logger.info("El mensaje enviado por el emisor fue: ".concat(message.trim()).concat("."));

        return message.trim();
    }
    public Coordinate getLocation(final Float... distances){
        Coordinate coord = new Coordinate();
        List<Float> mappingDistance = Arrays.asList(distances);
        Coordinate coordKenobi = new Coordinate(SateliteEnum.KENOBI);
        Coordinate coordSkywalker = new Coordinate(SateliteEnum.SKYWALKER);
        Float distanceBetweenKenobiAndSkywalker = this.getDistanceBetweenPoints(coordKenobi, coordSkywalker);
        Float alfa = this.getAngleBetweenLines(distanceBetweenKenobiAndSkywalker, mappingDistance.get(0));
        Float deltaX = SateliteEnum.SKYWALKER.getX() - SateliteEnum.KENOBI.getX();
        Float deltaY = SateliteEnum.SKYWALKER.getY() - SateliteEnum.KENOBI.getY();
        Float g = this.getAngleG(alfa, deltaX, deltaY);
        coord.setX(new Float(SateliteEnum.KENOBI.getX() + Math.sin(g) * mappingDistance.get(0)));
        coord.setY(new Float(SateliteEnum.KENOBI.getY() + Math.cos(g) * mappingDistance.get(0)));
        return coord;
    }
    private Float getDistanceBetweenPoints(Coordinate p1, Coordinate p2){
        return new Float(Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2)));
    }
    private Float getAngleBetweenLines(Float d1, Float d2){
        return new Float(Math.atan((d2 - d1) / (1 + (d1 * d2))));
    }
    private Float getAngleG(Float alfa, Float deltaX, Float deltaY){
        return new Float(180.0 - alfa - Math.atan(deltaX / deltaY));
    }

}

