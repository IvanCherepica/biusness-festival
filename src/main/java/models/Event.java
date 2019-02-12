package models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
	
	private long id;
	private String name;
	private String description;
	private EventPoint eventPoint;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "eventpoint_id")
	public EventPoint getEventPoint () {
		return this.eventPoint;
	}
	public void setEventPoint (EventPoint eventPoint) {
		this.eventPoint = eventPoint;
	}
	
	@Override
	public String toString () {
		return "Event{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", eventPoint=" + eventPoint +
				'}';
	}
}
