package challenge.enums;

public enum SateliteEnum {
    KENOBI(new Float(-500), new Float(-200)),
    SKYWALKER(new Float(100), new Float(-100)),
    SATO(new Float(500), new Float(100));
    
    private Float x;
    private Float y;

    private SateliteEnum(Float x, Float y){
        this.x = x;
        this.y = y;
    }
    public Float getX() {
        return x;
    }
    public Float getY() {
        return y;
    }
    public void setX(Float x) {
        this.x = x;
    }
    public void setY(Float y) {
        this.y = y;
    }
}
