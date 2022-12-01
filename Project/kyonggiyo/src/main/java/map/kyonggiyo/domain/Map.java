package map.kyonggiyo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Map {

    @Id
    private Long id;
    private String name;
    private float x;
    private float y;
    private String des;
    private float star;
    private String sum;
    private Long ab;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Long getAb() {
        return ab;
    }

    public void setAb(Long ab) {
        this.ab = ab;
    }
}
