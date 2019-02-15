package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import java.util.*;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String password;
    private String role;
    private String imagePath;

    @Expose
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = EventPoint.class)
    @JoinTable(name = "users_on_eventpoints",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_point_id")})
    private Set<EventPoint> eventPoints;

    @Expose
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Event.class)
    @JoinTable(name = "users_on_event",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "events_id")})
    private Set<Event> events;

    public User() {
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }


    public User(long id, String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Set<EventPoint> getEventPoints() {
        return eventPoints;
    }

    public void setEventPoints(Set<EventPoint> eventsp) {
        this.eventPoints = eventsp;
    }

    public void addEventPoint(EventPoint event) {
            eventPoints.add(event);
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEventsFromFest(Set<Event> events) {
        this.events = events;
        Set<EventPoint> EventPointSet = new LinkedHashSet<>();
        for (Event eve : events) {
                EventPointSet.add(eve.getEventPoint());
        }
        setEventPoints(EventPointSet);
    }

    public void setEventsFromEPoint(Set<Event> events, EventPoint eventPoint) {
        this.events = events;
        if (events.size() > 0) {
            addEventPoint(eventPoint);
        } else {
            eventPoints.remove(eventPoint);
        }
    }

    public void addEvent(Event event) {
            events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (imagePath != null ? !imagePath.equals(user.imagePath) : user.imagePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }


}
