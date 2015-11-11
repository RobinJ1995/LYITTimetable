package be.robinj.lyit.timetable;

/**
 * Created by robin on 11/11/15.
 */
public class Timespan
{
	private Time start;
	private Time end;

	public Timespan (Time start, Time end)
	{
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString ()
	{
		return this.start + " - " + this.end;
	}
}
