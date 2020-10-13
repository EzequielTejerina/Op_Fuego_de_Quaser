package challenge.request;

import challenge.model.Satellite;

import java.util.List;

public class TopSecretRequest {
    List<Satellite> satellites;

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }
}
