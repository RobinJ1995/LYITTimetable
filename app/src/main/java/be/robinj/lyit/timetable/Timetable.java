package be.robinj.lyit.timetable;

import java.net.URLEncoder;
import java.util.List;

import be.robinj.lyit.timetable.entity.Group;
import be.robinj.lyit.timetable.entity.Lesson;

/**
 * Created by robin on 14/10/15.
 */
public class Timetable
{
	public static List<Lesson> fetch (List<Group> groups)
	{
		String url = "http://www.lyit.ie:8001/reporting/textspreadsheet;student+set;id;{:groups:}?t=student+set+textspreadsheet&template=student+set+textspreadsheet";

		StringBuilder strbGroups = new StringBuilder ();
		for (Group group : groups)
		{
			if (strbGroups.length () > 0)
				strbGroups.append ("%0D%0A%23");

			strbGroups.append (URLEncoder.encode (group.getCode ()));
		}

		url = url.replace ("{:groups:}", strbGroups.toString ());

		return null;
	}
}
