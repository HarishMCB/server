package com.mcb.assessment.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
	private final String message;
	private final String description;
}
