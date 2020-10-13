package challenge.request;

public class TopSecretSplitRequest {
    private Float distance;
    private String[] message;

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
