package models;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class PointUnit extends MapUnit {
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=Festival.class/*, cascade = CascadeType.ALL*/)
    @JoinColumn(name="festival_id") //?
    private Festival festival;

    public PointUnit() {
    }

    public PointUnit(String name, String description, String geometry, String color, Festival festival , String center, double radius) {
        super(name, description, geometry, color, center, radius);
        this.festival = festival;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }
}
