package models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class MapUnit  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @SerializedName("id")
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    @SerializedName("name")
    private String name;

    @Column(name = "description")
    @SerializedName("description")
    private String description;

    @Column(name = "geometry",  columnDefinition="TEXT")
    @SerializedName("geometry")
    private String geometry; //(JSON)

    @Column(name = "color")
    @SerializedName("color")
    private String color;

    @Column(name = "center")
    @SerializedName("center")
    private String center;

    @Column(name = "radius")
    @SerializedName("radius")
    private double radius;

    public MapUnit() {
    }


    public MapUnit(String name, String description, String geometry, String color, String center , double radius) {
        this.name = name;
        this.description = description;
        this.geometry = geometry;
        this.color = color;
        this.center = center;
        this.radius = radius;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getGeometry() {
        return geometry;
    }

    public String getColor() {
        return color;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "MapUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", geometry='" + geometry + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapUnit mapUnit = (MapUnit) o;
        return id == mapUnit.id &&
                Double.compare(mapUnit.radius, radius) == 0 &&
                Objects.equals(name, mapUnit.name) &&
                Objects.equals(description, mapUnit.description) &&
                Objects.equals(geometry, mapUnit.geometry) &&
                Objects.equals(color, mapUnit.color) &&
                Objects.equals(center, mapUnit.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, geometry, color, center, radius);
    }
}
