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
public final class Group extends Entity // Immutable because it's not supposed to be inherited from //
{
	public final String departmentCode;

	public Group (String name, String code, String departmentCode)
	{
		super (name, code);

		this.departmentCode = departmentCode;
	}

	public static List<Group> fetch () throws Exception
	{
		URL url = new URL ("http://timetables.lyit.ie/js/filter.js");

		Pattern pattern = Pattern.compile ("\\s*studsetarray\\[(\\d+)\\]\\s*\\[(\\d)\\]\\s*=\\s*\\\"([^\\\"]+)\\\";", Pattern.DOTALL | Pattern.MULTILINE);
		List<Group> groups = new ArrayList<> ();

		InputStreamReader streamReader = new InputStreamReader (url.openStream ());
		BufferedReader reader = new BufferedReader (streamReader);

		String line = null;

		String name = null;
		String departmentCode = null;
		boolean randomness = false;

		boolean go = false;
		while ((line = reader.readLine ()) != null)
		{
			if ((! go) && line.contains ("studsetarray[0]"))
				go = true;

			if (go && line.contains ("studsetarray.sort"))
				break;

			if (go)
			{
				Matcher matcher = pattern.matcher (line);

				if (matcher.matches ())
				{
					if (name == null)
						name = matcher.group (3);
					else if (departmentCode == null)
						departmentCode = matcher.group (3);
					else
						randomness = true;
				}

				if (name != null && departmentCode != null && randomness)
				{
					String[] parts = name.split ("\\s-\\s", 2);
					String code = "";

					if (parts.length == 2)
					{
						code = parts[0];
						name = parts[1];
					}

					Group group = new Group (name, code, departmentCode);
					groups.add (group);

					name = null;
					departmentCode = null;
					randomness = false;
				}
			}
		}

		reader.close ();

		return groups;
	}
}
