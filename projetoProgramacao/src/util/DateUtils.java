package util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.text.ParseException;

public class DateUtils {

	public static int calculateAge(String birthDate) throws ParseException {

		// String to Date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(birthDate);
		// Converting obtained Date object to LocalDate object
		Instant instant = date.toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
		LocalDate givenDate = zone.toLocalDate();
		// Calculating the difference between given date to current date
		Period period = Period.between(givenDate, LocalDate.now());
		return period.getYears();

	}

}
