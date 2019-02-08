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

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }
}
