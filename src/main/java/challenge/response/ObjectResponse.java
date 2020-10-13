package challenge.response;

import challenge.model.Coordinate;

public class ObjectResponse {
    private Coordinate position;
    private String message;

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
