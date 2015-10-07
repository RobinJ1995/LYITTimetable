package be.robinj.lyit.timetable.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by robin on 11/09/15.
 */
public class Department extends Entity
{
	public Department (String name, String code)
	{
		super (name, code);
	}

	public static List<Department> fetch () throws Exception
	{
		// Please tell me this is a joke... //
		URL url = new URL ("http://timetables.lyit.ie/js/filter.js");

		Pattern pattern = Pattern.compile ("\\s*deptarray\\[(\\d+)\\]\\s*\\[(\\d)\\]\\s*=\\s*\\\"([^\\\"]+)\\\";", Pattern.DOTALL | Pattern.MULTILINE);
		List<Department> departments = new ArrayList<> ();

		InputStreamReader streamReader = new InputStreamReader (url.openStream ());
		BufferedReader reader = new BufferedReader (streamReader);

		StringBuilder strb = new StringBuilder ();
		String line = null;

		String name = null;
		String code = null;

		boolean go = false;
		while ((line = reader.readLine ()) != null)
		{
			if ((! go) && line.contains ("deptarray[0]"))
				go = true;

			if (go && line.contains ("deptarray.sort"))
				break;

			if (go)
			{
				// Apparently not... Parsing a Javascript array with regular expressions... Let's go. //
				//WARNING// This will break when the timetable system changes (so at least when this breaks it may actually be good news) //
				Matcher matcher = pattern.matcher (line);

				if (matcher.matches ())
				{
					if (name == null)
						name = matcher.group (3);
					else
						code = matcher.group (3);
				}

				if (name != null && code != null)
				{
					Department department = new Department (name, code);
					departments.add (department);

					name = null;
					code = null;
				}
			}
		}

		reader.close ();

		return departments;
	}
}
