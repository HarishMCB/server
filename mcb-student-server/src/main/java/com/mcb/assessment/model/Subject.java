package com.mcb.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long subjectId;

	@Column
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "name cannot be null")
	private String title;


}
