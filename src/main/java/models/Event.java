package models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
	
	private long id;
	private String name;
	private String description;
	private EventPoint eventPoint;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@SerializedName("id")
	public long getId () {
		return id;
	}
	public void setId (long id) {
		this.id = id;
	}
	
	@Column(name = "name", unique = true, updatable = false)
	@SerializedName("name")
	public String getName () {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}
	
	@Column(name = "description")
	@SerializedName("description")
	public String getDescription () {
		return description;
	}
	public void setDescription (String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=EventPoint.class/*, cascade = CascadeType.ALL*/)
	@JoinColumn(name = "eventpoint_id")
	public EventPoint getEventPoint () {
		return this.eventPoint;
	}
	public void setEventPoint (EventPoint eventPoint) {
		this.eventPoint = eventPoint;
	}

	@ManyToMany(fetch= FetchType.EAGER, targetEntity = User.class)
	@JoinTable(name ="users_on_event",
		joinColumns = {@JoinColumn(name="events_id")},
		inverseJoinColumns = {@JoinColumn(name="users_id")})
	private Set<User> users;


	public Event () {
	}

	public Event (String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Event (String name, String description, EventPoint eventPoint) {
		this.name = name;
		this.description = description;
		this.eventPoint = eventPoint;
	}

//	public Set<User> getUsers(){return this.users;}
//	public void setUsers(Set<User> users){this.users=users;}
//ublic void addUser(User user){
		//if (users.contains(user)){
//			return false;
//		}
//		else{
//	users.add(user);
//
	@Override
	public String toString () {
		return "Event{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", eventPoint=" + eventPoint +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;
		return id == event.id &&
				Objects.equals(name, event.name) &&
				Objects.equals(description, event.description) &&
				Objects.equals(eventPoint, event.eventPoint) &&
				Objects.equals(users, event.users);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, eventPoint, users);
	}
}
