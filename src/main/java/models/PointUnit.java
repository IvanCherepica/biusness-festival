package models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class PointUnit extends MapUnit {
    @ManyToOne
    @JoinColumn(name="festival_id") //?
    private Festival festival;

    public PointUnit() {
    }

    public PointUnit(String name, String description, String geometry, String color, Festival festival) {
        super(name, description, geometry, color);
        this.festival = festival;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }
}
