package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "festival")
public class Festival extends MapUnit{

    public Festival() {
    }

    public Festival(String name, String description, String geometry, String color, String center, double radius) {
        super(name, description, geometry, color, center, radius);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }
}
