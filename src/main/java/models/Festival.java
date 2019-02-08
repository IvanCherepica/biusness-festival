package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "festival")
public class Festival extends MapUnit{

    public Festival() {
    }

    public Festival(String name, String description, String geometry, String color) {
        super(name, description, geometry, color);
    }
}
