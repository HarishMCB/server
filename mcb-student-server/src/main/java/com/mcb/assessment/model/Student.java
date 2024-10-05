package com.mcb.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false)
	private Long studentId;
	@Column
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "name cannot be null")
	private String firstName;
	@Column
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "name cannot be null")
	private String LastName;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "groupId")
	//@JsonIgnore
	private Group group;
}
