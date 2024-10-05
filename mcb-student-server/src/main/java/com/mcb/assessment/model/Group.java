package com.mcb.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Groups")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupId;
	@Column
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "name cannot be null")
	private String groupName;
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<Student> students = new HashSet<> ();

	public Set<Student> getStudents () {
		return students;
	}

	public void setStudents (Set<Student> students) {
		this.students = students;

		for (Student b : students) {
			b.setGroup (this);
		}
	}


}
