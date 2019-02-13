package models;

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

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = EventPoint.class)
    @JoinTable(name = "users_on_eventpoints",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_point_id")})
    private Set<EventPoint> eventPoints;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;
        return role.equals(user.role);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
