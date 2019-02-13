package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hot_point")
public class HotPoint extends PointUnit{

    public HotPoint() {
    }

    public HotPoint(String name, String description, String geometry, String color, Festival festival, String center, double radius) {
        super(name, description, geometry, color, festival, center, radius);
    }

}
