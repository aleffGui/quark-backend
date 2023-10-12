package com.guilherme.quarkapi.enums.converters;

import java.util.stream.Stream;

import com.guilherme.quarkapi.enums.TaskPriority;

import jakarta.persistence.AttributeConverter;

public class TaskPriorityConverter implements AttributeConverter<TaskPriority, String> {

	@Override
	public String convertToDatabaseColumn(TaskPriority priority) {
		if(priority == null) {
			return null;			
		}
		return priority.getValue();
	}

	@Override
	public TaskPriority convertToEntityAttribute(String value) {
		if(value == null) {
			return null;
		}
		return Stream.of(TaskPriority.values())
				.filter(c -> c.getValue().equals(value))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
