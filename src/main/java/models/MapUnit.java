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

    public MapUnit() {
    }

    public MapUnit(String name, String description, String geometry, String color) {
        this.name = name;
        this.description = description;
        this.geometry = geometry;
        this.color = color;
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
}
