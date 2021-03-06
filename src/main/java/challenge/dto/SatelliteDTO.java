package challenge.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "satellites")
public class SatelliteDTO {

    @Id
    private ObjectId _id;
    private String name;
    private Float distance;
    private String[] message;

    public SatelliteDTO(){}

    public SatelliteDTO(String name, Float distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }
}
