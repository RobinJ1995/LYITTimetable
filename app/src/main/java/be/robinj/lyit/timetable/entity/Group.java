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
public class Group extends Entity
{
	public Group (String name, String code)
	{
		super (name, code);
	}

	public static List<Group> fetch () throws Exception
	{
		URL url = new URL ("http://timetables.lyit.ie/js/filter.js");

		Pattern pattern = Pattern.compile ("\\s*studsetarray\\[(\\d)\\]\\s*\\[(\\d)\\]\\s*=\\s*\\\"([^\\\"]+)\\\";", Pattern.DOTALL | Pattern.MULTILINE);
		List<Group> groups = new ArrayList<> ();

		InputStreamReader streamReader = new InputStreamReader (url.openStream ());
		BufferedReader reader = new BufferedReader (streamReader);

		StringBuilder strb = new StringBuilder ();
		String line = null;

		String name = null;
		String code = null;
		boolean randomness = false;

		boolean go = false;
		while ((line = reader.readLine ()) != null)
		{
			if ((! go) && line.contains ("deptarray[0]"))
				go = true;

			if (go && line.contains ("deptarray.sort"))
				go = false;

			if (go)
			{
				// Apparently not... Parsing a Javascript array with regular expressions... Let's go. //
				//WARNING// This will break when the timetable system changes (so at least when this breaks it may actually be good news) //
				Matcher matcher = pattern.matcher (line);

				if (matcher.matches ())
				{
					if (name == null)
						name = matcher.group (3);
					else if (code == null)
						code = matcher.group (3);
					else
						randomness = true;
				}

				if (name != null && code != null && randomness)
				{
					Group group = new Group (name, code);
					groups.add (group);

					name = null;
					code = null;
					randomness = false;
				}
			}
		}

		reader.close ();

		return groups;
	}
}
