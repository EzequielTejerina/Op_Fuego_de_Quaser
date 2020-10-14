package challenge.enums;

public enum SateliteEnum {
    KENOBI("kenobi", new Float(-500), new Float(-200)),
    SKYWALKER("skywalker", new Float(100), new Float(-100)),
    SATO("sato", new Float(500), new Float(100));

    private String name;
    private Float x;
    private Float y;

    private SateliteEnum(String name, Float x, Float y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
