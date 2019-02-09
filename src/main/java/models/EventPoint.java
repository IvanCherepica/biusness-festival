package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "event_point")
public class EventPoint extends PointUnit {

    public EventPoint() {
    }

    public EventPoint(String name, String description, String geometry, String color, Festival festival) {
        super(name, description, geometry, color, festival);
    }
}
