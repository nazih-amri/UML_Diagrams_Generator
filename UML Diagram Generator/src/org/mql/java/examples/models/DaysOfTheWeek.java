package org.mql.java.examples.models;

public enum DaysOfTheWeek {

	MONDAY(1),
	TUESDAY(2),
	WEDNESDAY(3),
	THURSDAY(4),
	FRIDAY(5),
	SATURDAY(6),
	SUNDAY(7);
	
	final public int number;
	
	private DaysOfTheWeek(int number) {
		this.number=number;
	}
}
