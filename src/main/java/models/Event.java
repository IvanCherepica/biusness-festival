package models;

import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@SerializedName("id")
	private long id;

	@Column(name = "name", unique = true, updatable = false)
	@SerializedName("name")
	private String name;

	@Column(name = "description")
	@SerializedName("description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eventpoint_id")
	private EventPoint eventPoint;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "festival_id")
	private Festival festival;

	@Column(name = "date_begin")
	@SerializedName("date_begin")
	@ColumnDefault("2000-01-01T00:00:00")
	private LocalDateTime dateBegin;

	@Column(name = "date_end")
	@SerializedName("date_end")
	@ColumnDefault("2000-01-01T00:00:00")
	private LocalDateTime dateEnd;

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

	public Event (String name, String description, EventPoint eventPoint, Festival festival) {
		this.name = name;
		this.description = description;
		this.eventPoint = eventPoint;
		this.festival = festival;
	}

	public long getId () {
		return id;
	}
	public void setId (long id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}

	public String getDescription () {
		return description;
	}
	public void setDescription (String description) {
		this.description = description;
	}

	public EventPoint getEventPoint () {
		return this.eventPoint;
	}
	public void setEventPoint (EventPoint eventPoint) {
		this.eventPoint = eventPoint;
	}

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

	public Festival getFestival () {
		return festival;
	}
	public void setFestival (Festival festival) {
		this.festival = festival;
	}

	public LocalDateTime getDateBegin () {
		return dateBegin;
	}
	public void setDateBegin (LocalDateTime dateBegin) {
		this.dateBegin = dateBegin;
	}

	public LocalDateTime getDateEnd () {
		return dateEnd;
	}
	public void setDateEnd (LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString () {
		return "Event{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", eventPoint=" + eventPoint +
				", beginDate=" + dateBegin.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) +
				", endDate=" + dateEnd.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) +
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
