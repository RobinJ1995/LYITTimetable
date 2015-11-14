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
public class Lesson
	extends Entity
{
	public Group group;
	public boolean practical;
	public Timespan timespan;
	public List<String> rooms;
	public List<String> staff;

	public Lesson (String name, String code, Group group, boolean practical, Timespan timespan, List<String> rooms, List<String> staff)
	{
		super (name, code);

		this.group = group;
		this.practical = practical;
		this.timespan = timespan;
		this.rooms = rooms;
		this.staff = staff;
	}
}
