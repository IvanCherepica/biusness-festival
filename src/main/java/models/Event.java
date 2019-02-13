package models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "eventpoint_id")
	private EventPoint eventPoint;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "festival_id")
	private Festival festival;
	
	@Column(name = "date_begin")
	@SerializedName("date_begin")
	@Temporal(value=TemporalType.TIMESTAMP)
	Date dateBegin;
	
	@Column(name = "date_end")
	@SerializedName("date_end")
	@Temporal(value=TemporalType.TIMESTAMP)
	Date dateEnd;
	
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
	
	public Festival getFestival () {
		return festival;
	}
	public void setFestival (Festival festival) {
		this.festival = festival;
	}
	
	public Date getDateBegin () {
		return dateBegin;
	}
		public void setDateBegin (Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	
	public Date getDateEnd () {
		return dateEnd;
	}
	public void setDateEnd (Date dateEnd) {
		this.dateEnd = dateEnd;
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
