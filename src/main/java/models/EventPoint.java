package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "event_point")
public class EventPoint extends PointUnit {
    @Expose
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinTable(name = "users_on_eventpoints",
            joinColumns = {@JoinColumn(name = "event_point_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean addUserToEvent(User user) {
        if (users.contains(user)){return false;}
        else {
            users.add(user);
            return true;
        }
    }




    public EventPoint() {
    }

    public EventPoint(String name, String description, String geometry, String color, Festival festival, String center, double radius) {
        super(name, description, geometry, color, festival, center, radius);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

}
