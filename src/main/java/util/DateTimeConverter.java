package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeConverter {
	
	public static LocalDateTime parse(String dateString) {
		
		if (dateString.isEmpty()) { return LocalDateTime.now();}
		
		String pattern =  "";
		int dateStringLenght = dateString.length();
		if (dateStringLenght==10) {
			pattern = "dd.MM.yyyy";
		} else if (dateStringLenght == 16) {
			pattern = "dd.MM.yyyy HH:mm";
		} else {
			pattern = "dd.MM.yyyy HH:mm:ss";
		}
		LocalDateTime result = null;
		try {
			result = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(pattern));
		} catch (DateTimeParseException e) {
			System.out.println("Error converting String to LocalDateTime");
		}
		return result;
	}
	
	public static String format(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
	}
	
	public static String format(LocalDateTime date, String pattern) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}
	
}
