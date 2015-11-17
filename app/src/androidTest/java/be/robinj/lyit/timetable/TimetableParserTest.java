package be.robinj.lyit.timetable;

import junit.framework.TestCase;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import be.robinj.lyit.timetable.entity.Group;
import be.robinj.lyit.timetable.entity.Lesson;

import static org.junit.Assert.*;

/**
 * Created by robin on 16/11/15.
 */
@RunWith (value = BlockJUnit4ClassRunner.class)
public class TimetableParserTest
	extends TestCase
{
	@Test (expected = IOException.class) // HttpStatusException inherits from IOException, so that's covered as well //
	public void nonexistentGroup () throws Exception
	{
		try
		{
			Group group = new Group ("name", "code", "dept");
			Document document = Timetable.download (group);

			assertNotNull ("Should be a Document", document);
		}
		catch (Exception ex)
		{
			assertEquals ("IOException should be thrown", ex instanceof IOException, true);
			// I'd use the @Test(expected=...) annotation, but it doesn't do what the documentation says it should do //
		}
	}

	@Test
	public void parseWrongDocument () throws Exception // With the college's captive portal on the LYIT-Student network there is a good possibility of this happening //
	{
		Document document = Jsoup.connect ("http://www.lyit.ie/").ignoreHttpErrors (true).timeout (5000).get ();

		Group group = new Group ("name", "code", "dept");
		HashMap<String, List<Lesson>> timetable = Timetable.parse (group, document);

		assertEquals (timetable.isEmpty (), true);
	}

	// Impractical to write a test to download and parse an actual timetable, since which groups actually have a timetable available and which don't depends on the time of year etc //
}