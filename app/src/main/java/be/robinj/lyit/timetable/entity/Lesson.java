package be.robinj.lyit.timetable.entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.robinj.lyit.timetable.Timespan;

/**
 * Created by robin on 07/10/15.
 */
public final class Lesson
	extends Entity // Immutable (final) because this class is not supposed to be inherited from //
{
	public final boolean practical;
	public final Timespan timespan;
	public final List<String> rooms;
	public final List<String> staff;
	// Immutable (final) because there is no reason for these to ever be changed after initialisation //
	// Public because in this case getter methods don't seem to offer any advantages //

	public Lesson (String name, String code, boolean practical, Timespan timespan, List<String> rooms, List<String> staff) //TODO// Maybe I should use the builder pattern to make it more readable //
	{
		super (name, code);

		this.practical = practical;
		this.timespan = timespan;
		this.rooms = rooms;
		this.staff = staff;
	}
}
