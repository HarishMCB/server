package com.mcb.assessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Table(name = "Marks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long MarkId;

	@Column
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "name cannot be null")
	private BigDecimal mark;

	@ManyToOne
	@JoinColumn(name = "subjectId")
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "studentId")
	@JsonIgnore
	private Student student;
	@Column
	private ZonedDateTime date;


}
