package challenge.services;

import challenge.enums.SateliteEnum;
import challenge.exception.ServiceException;
import challenge.model.Coordinate;
import challenge.model.Satellite;
import challenge.request.TopSecretRequest;
import challenge.response.ObjectResponse;
import challenge.utils.ArrayUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class CommunicationService {

    private Logger logger = LogManager.getLogger(getClass());

    public Object topSecret(String requestBody) throws ServiceException {
        Gson gson = new Gson();
        ObjectResponse response = new ObjectResponse();
        TopSecretRequest request = gson.fromJson(requestBody, TopSecretRequest.class);
        if(StringUtils.isEmpty(request.getSatellites())){
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Request incorrecto");
        }
        response.setPosition(this.getLocation(this.getArrayDistances(request.getSatellites())));
        response.setMessage(this.getFinalMessage(request.getSatellites()));
        return gson.toJson(response);
    }

    public Object topSecretSplit(String satelliteName, String requestBody) {
        //JSONObject jsonResponse = new JSONObject();


        return "";
    }

    public String getMessage(final String... messages){
        String message = "";
        List<String> mappingMessages = Arrays.asList(messages);
        Iterator<String> it = mappingMessages.iterator();
        while(it.hasNext()){
            String word = it.next();
            if(!word.isEmpty() && !word.trim().equals("")){
                message = message.concat(" ").concat(word);
            }
        }
        return message.trim();
    }
    public Coordinate getLocation(final Float... distances) {
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

    private Float getDistanceBetweenPoints(final Coordinate p1, final Coordinate p2){
        return new Float(Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2)));
    }
    private Float getAngleBetweenLines(Float d1, Float d2){
        return (float) Math.atan((d2 - d1) / (1 + (d1 * d2)));
    }
    private Float getAngleG(final Float alfa, final Float deltaX, final Float deltaY){
        return new Float(180.0 - alfa - Math.atan(deltaX / deltaY));
    }
    private Float[] getArrayDistances(final List<Satellite> satellites) throws ServiceException {
        Float[] distances = new Float[satellites.size()];
        for(int i = 0; i < satellites.size(); i++){
            logger.info("El emisor esta a una distancia de [".concat(satellites.get(i).getDistance() != null? satellites.get(i).getDistance().toString() : "null").concat("] del satelite ".concat(satellites.get(i).getName())));
            if(satellites.get(i).getDistance() != null){
                distances[i] = satellites.get(i).getDistance();
            }else{
                throw new ServiceException(HttpStatus.NOT_FOUND, "No se puede determinar la posicion");
            }
        }
        return distances;
    }
    private String getFinalMessage(final List<Satellite> satellites) throws ServiceException {
        Map<Integer, String> messages = new HashMap<Integer, String>();
        Integer lengthArrayMessages = null;
        for(Satellite sat : satellites){
            lengthArrayMessages = lengthArrayMessages != null? lengthArrayMessages : sat.getMessage().length;
            logger.info("El satelite ".concat(sat.getName()).concat(" recibio el mensaje ").concat(ArrayUtil.toString(sat.getMessage())).concat(" del emisor."));
            if(lengthArrayMessages != sat.getMessage().length){
                logger.error("El mensaje no se pudo determinar.");
                throw new ServiceException(HttpStatus.NOT_FOUND, "No se puede determinar el mensaje");
            }
            for(int i = 0; i < sat.getMessage().length; i++){
                if(messages.containsKey(i)){
                    if(messages.get(i).isEmpty() && !sat.getMessage()[i].trim().isEmpty()){
                        messages.put(i, sat.getMessage()[i].trim());
                    }
                }else{
                    messages.put(i, sat.getMessage()[i].trim());
                }
            }
        }
        String[] arrayMessages = new String[messages.size()];
        for (Map.Entry<Integer, String> entry : messages.entrySet()) {
            arrayMessages[entry.getKey()] = entry.getValue();
        }
        return this.getMessage(arrayMessages);
    }
}

