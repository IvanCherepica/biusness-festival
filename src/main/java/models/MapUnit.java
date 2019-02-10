package models;

import javax.persistence.*;

@MappedSuperclass
abstract class MapUnit  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "geometry")
    private String geometry; //(JSON)

    @Column(name = "color")
    private String color;

    @Column(name = "center")
    private String center;

    @Column(name = "radius")
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
}
