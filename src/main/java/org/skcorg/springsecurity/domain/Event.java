package org.skcorg.springsecurity.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * An {@link Event} is an item on a calendar that contains an owner (the person
 * who created it), an attendee (someone who was invited to the event), when the
 * event will occur, a summary, and a description. For simplicity, all fields
 * are required.
 * 
 * @author Sumit Chandna
 * 
 */
@Entity
@Table(name = "EVENTS")
@NamedQueries(value = {
		@NamedQuery(name = "Event.getEvent", query = "Select e from Event e where e.id= :id"),
		@NamedQuery(name = "Event.findEventForUser", query = "Select e from Event e where e.owner.id = :user or e.attendee.id= :user") })
public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	@NotEmpty(message = "Summary is required")
	private String summary;
	@NotEmpty(message = "Description is required")
	private String description;
	@NotNull(message = "When is required")
	private Calendar when;
	@OneToOne
	@JoinColumn(name = "OWNER")
	private CalendarUser owner;
	@OneToOne
	@JoinColumn(name = "ATTENDEE")
	private CalendarUser attendee;

	/**
	 * The identifier for the {@link Event}. Must be null when creating a new
	 * {@link Event}, otherwise non-null.
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * The summary of the event.
	 * 
	 * @return
	 */
	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * The detailed description of the event.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * When this event is happening.
	 * 
	 * @return
	 */
	public Calendar getWhen() {
		return when;
	}

	public void setWhen(final Calendar when) {
		this.when = when;
	}

	/**
	 * The owner (who created the Event)
	 * 
	 * @return
	 */
	public CalendarUser getOwner() {
		return owner;
	}

	public void setOwner(final CalendarUser owner) {
		this.owner = owner;
	}

	/**
	 * The user that was invited to the event.
	 * 
	 * @return
	 */
	public CalendarUser getAttendee() {
		return attendee;
	}

	public void setAttendee(final CalendarUser attendee) {
		this.attendee = attendee;
	}

	// --- override Object ---

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}