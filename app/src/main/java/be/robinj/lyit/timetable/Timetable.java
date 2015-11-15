package be.robinj.lyit.timetable;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.robinj.lyit.timetable.entity.Group;
import be.robinj.lyit.timetable.entity.Lesson;

/**
 * Created by robin on 14/10/15.
 */
public class Timetable
{
	public static HashMap<String, List<Lesson>> fetch (Group group) throws IOException
	{
		final String url = "http://www.lyit.ie:8001/reporting/textspreadsheet;student+set;id;" + URLEncoder.encode (group.code) + "%0D%0A%23?t=student+set+textspreadsheet&template=student+set+textspreadsheet";
		// %0D%0A%23 // Decoded : "\r\n#" //

		HashMap<String, List<Lesson>> data = new HashMap<> ();
		String day = null;

		Document document = Jsoup.connect (url).timeout (5000).get ();
		for (Element element : document.body ().children ())
		{
			if ("p".equalsIgnoreCase (element.tagName ())) // Calling .equals () on the literal avoids NullPointerExceptions //
			{
				Element span = element.getElementsByTag ("span").first ();

				if (span != null)
				{
					for (String className : span.classNames ())
					{
						if (className.startsWith ("label"))
							day = span.text ();
					}
				}
			}
			else if ("table".equalsIgnoreCase (element.tagName ()))
			{
				if (element.hasClass ("spreadsheet"))
				{
					List<Lesson> lessons = new ArrayList<Lesson> ();

					for (Element row : element.getElementsByTag ("tr"))
					{
						if (! row.hasClass ("columnTitles"))
						{
							Elements fields = row.getElementsByTag ("td");

							String fActivity = fields.get (0).text ();
							String fModule = fields.get (1).text ();
							String fType = fields.get (2).text ();
							String fStart = fields.get (3).text ();
							String fEnd = fields.get (4).text ();
							// 5 // Duration // Don't care. Will be calculated by Timespan //
							// 6 // Weeks // Don't care. //
							String fRoom = fields.get (7).text ();
							String fStaff = fields.get (8).text ();
							// 9 // Student groups // Don't care. //

							boolean practical = "Practical".equalsIgnoreCase (fType);
							Timespan timespan = new Timespan (new Time (fStart), new Time (fEnd));

							List<String> rooms = new ArrayList<> ();
							for (String strRoom : fRoom.split (";"))
							{
								String trimmed = fRoom.trim ();

								if (! trimmed.isEmpty ())
									rooms.add (trimmed);
							}

							List<String> staff = new ArrayList<> ();
							for (String strStaff : fStaff.split (";"))
							{
								String trimmed = strStaff.trim ();

								if (! trimmed.isEmpty ())
									staff.add (trimmed);
							}

							Lesson lesson = new Lesson (fModule, fActivity, null, practical, timespan, rooms, staff);
							lessons.add (lesson);
						}
					}

					data.put (day, lessons);
				}
			}
		}

		return data;
	}
}
