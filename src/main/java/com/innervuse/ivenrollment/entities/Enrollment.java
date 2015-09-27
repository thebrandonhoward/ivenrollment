package com.innervuse.ivenrollment.entities;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class Enrollment
{
	@PrimaryKey(value = "username")
	private String username;
	
	@Column(value = "enddate")
	private String enddate;
	
	@Column(value = "startdate")
	private String startdate;
	
	@Column(value = "userlevel")
	private String userlevel;
	
	public Enrollment(String username, String enddate, String startdate, String userlevel)
	{
		this.username = username;
		this.startdate = startdate;
		this.enddate = enddate;
		this.userlevel = userlevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	@Override
	public String toString() {
		return "Enrollment [username=" + username + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", userlevel=" + userlevel
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		if (enddate == null) {
			if (other.enddate != null)
				return false;
		} else if (!enddate.equals(other.enddate))
			return false;
		if (startdate == null) {
			if (other.startdate != null)
				return false;
		} else if (!startdate.equals(other.startdate))
			return false;
		if (userlevel == null) {
			if (other.userlevel != null)
				return false;
		} else if (!userlevel.equals(other.userlevel))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}