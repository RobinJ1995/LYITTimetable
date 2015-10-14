package be.robinj.lyit.timetable.entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by robin on 07/10/15.
 */
public class Lesson
	extends Entity
{
	private Group group;

	public Lesson (String name, String code, Group group)
	{
		super (name, code);

		this.group = group;
	}
}
