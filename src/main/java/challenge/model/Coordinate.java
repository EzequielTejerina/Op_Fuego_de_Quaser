package challenge.model;

import challenge.enums.SateliteEnum;

public class Coordinate {
    private Float x;
    private Float y;

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{x=" + x + ", y=" + y +'}';
    }

    public Coordinate(SateliteEnum satellite) {
        this.x = satellite.getX();
        this.y = satellite.getY();
    }

    public Coordinate() {
    }
}
