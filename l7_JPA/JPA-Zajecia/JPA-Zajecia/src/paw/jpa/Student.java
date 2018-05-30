package paw.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="student")
public class Student {
	protected int id;
	protected String firstName;
	protected String lastName;
	protected Date creationDate;
	protected Date updateDate;

	public Student() {
		super();
	}

	public Student(String firstName, String lastName,
			Date creationDate, Date updateDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable=false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="imie", nullable=false, length=20)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="nazwisko", nullable=false, length=30)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable=false)		
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable=true)			
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", creationDate=" + creationDate + ", updateDate="
				+ updateDate + "]";
	}

}
