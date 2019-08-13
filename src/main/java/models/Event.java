package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import util.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.time.LocalDateTime;


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

	@Expose
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eventpoint_id")
	private EventPoint eventPoint;

	@Expose
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "festival_id")
	private Festival festival;

//	@Column(name = "date_begin", columnDefinition="TIMESTAMP")
//    @Expose
//	@SerializedName("date_begin")
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
//	private LocalDateTime dateBegin = LocalDateTime.now();
//
//    @Expose
//	@Column(name = "date_end", columnDefinition="TIMESTAMP")
//	@SerializedName("date_end")
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
//	private LocalDateTime dateEnd = LocalDateTime.now();

	@Expose
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
		this.users = new HashSet<>();
	}

	public Event (String name, String description, EventPoint eventPoint, Festival festival) {
		this.name = name;
		this.description = description;
		this.eventPoint = eventPoint;
		this.festival = festival;
		this.users = new HashSet<>();
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

	public Event (String name, String description, EventPoint eventPoint) {
		this.name = name;
		this.description = description;
		this.eventPoint = eventPoint;
		this.users = new HashSet<>();
	}

	public Festival getFestival () {
		return festival;
	}
	public void setFestival (Festival festival) {
		this.festival = festival;
	}

//	public LocalDateTime getDateBegin () {
//		return dateBegin;
//	}
//	public void setDateBegin (LocalDateTime dateBegin) {
//		this.dateBegin = dateBegin;
//	}
//
//	public LocalDateTime getDateEnd () {
//		return dateEnd;
//	}
//	public void setDateEnd (LocalDateTime dateEnd) {
//		this.dateEnd = dateEnd;
//	}

	@Override
	public String toString () {
		return "Event{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", eventPoint='" + eventPoint + '\'' +
//				", beginDate='" + dateBegin + '\'' +
//				", endDate='"+ dateEnd + '\'' +
				'}';
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
//        if (dateBegin != null ? !dateBegin.equals(event.dateBegin) : event.dateBegin != null) return false;
//        if (dateEnd != null ? !dateEnd.equals(event.dateEnd) : event.dateEnd != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
//        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
//        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        return result;
    }


}
