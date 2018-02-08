package edu.kit.ifv.mobitopp.result;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import edu.kit.ifv.mobitopp.publictransport.model.Time;
import edu.kit.ifv.mobitopp.simulation.SimulationDate;
import edu.kit.ifv.mobitopp.simulation.SimulationDateIfc;

public class DateFormat {

	private static final String weekdayDateSeparator = ", ";
	private static final String dateTimeSeparator = " ";
	private static final int width = 2;
	private static final DateTimeFormatter dayFormat = weekdayFormat();
	private static final DateTimeFormatter timeFormat = timeFormat();
	private static final DateTimeFormatter fullFormat = fullFormat();
	private static final DateTimeFormatter weekdayTimeFormat = weekdayTimeFormat();
	private static final DateTimeFormatter dayTimeFormat = dayTimeFormat();

	public DateFormat() {
		super();
	}

	public String asDay(SimulationDateIfc date) {
		return format(date, dayFormat);
	}

	public String asTime(SimulationDateIfc date) {
		return format(date, timeFormat);
	}

	public String asFullDate(SimulationDateIfc date) {
		return format(date, fullFormat);
	}

	public String asWeekdayTime(SimulationDateIfc date) {
		return format(date, weekdayTimeFormat);
	}

	public String asDayTime(SimulationDateIfc date) {
		return format(date, dayTimeFormat);
	}

	private String format(SimulationDateIfc date, DateTimeFormatter format) {
		return SimulationDate.monday.plus(date.fromStart().toDuration()).format(format);
	}

	public String asTime(Time time) {
		return timeFormat.format(time.time());
	}

	private static DateTimeFormatter fullFormat() {
		return new DateTimeFormatterBuilder()
				.append(dateFormat())
				.appendLiteral(dateTimeSeparator)
				.append(timeFormat())
				.toFormatter();
	}

	private static DateTimeFormatter timeFormat() {
		return hourMinute()
				.appendLiteral(':')
				.appendValue(ChronoField.SECOND_OF_MINUTE, width)
				.toFormatter();
	}

	private static DateTimeFormatterBuilder hourMinute() {
		return new DateTimeFormatterBuilder()
				.appendValue(ChronoField.HOUR_OF_DAY, width)
				.appendLiteral(':')
				.appendValue(ChronoField.MINUTE_OF_HOUR, width);
	}

	private static DateTimeFormatter dateFormat() {
		return new DateTimeFormatterBuilder()
				.appendValue(ChronoField.DAY_OF_MONTH, width)
				.toFormatter();
	}

	private static DateTimeFormatter weekdayFormat() {
		return new DateTimeFormatterBuilder().appendPattern("EE").toFormatter();
	}

	private static DateTimeFormatter weekdayTimeFormat() {
		return new DateTimeFormatterBuilder()
				.append(weekdayFormat())
				.appendLiteral(weekdayDateSeparator)
				.append(dateFormat())
				.appendLiteral(dateTimeSeparator)
				.append(timeFormat())
				.toFormatter();
	}
	
	private static DateTimeFormatter dayTimeFormat() {
		return new DateTimeFormatterBuilder()
				.append(weekdayFormat())
				.appendLiteral(dateTimeSeparator)
				.append(hourMinute().toFormatter())
				.toFormatter();
	}

}