package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "event_point")
public class EventPoint extends PointUnit {
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinTable(name = "relations",
            joinColumns = {@JoinColumn(name = "event_point_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private List<User> users;

    public List<User> getUsersFromEvent() {
        return users;
    }

    public void setUsersToEvent(List<User> users) {
        this.users = users;
    }

    public void addUserToEvent(User user) {
        this.users.add(user);
    }

    public EventPoint() {
    }

    public EventPoint(String name, String description, String geometry, String color, Festival festival, String center, double radius) {
        super(name, description, geometry, color, festival, center, radius);
    }
}
