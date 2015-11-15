package be.robinj.lyit.timetable;

import java.text.DecimalFormat;

/**
 * Created by robin on 11/11/15.
 */
public class Time // Mutable, because it would make sense to extend this class in future (for example to add a member to store the number of seconds in) //
{
	public final short h;
	public final short m;

	public Time (short h, short m)
	{
		this.h = h;
		this.m = m;
	}

	public Time (String str)
	{
		String[] parts = str.split (":", 2);

		this.h = Short.parseShort (parts[0]);
		this.m = Short.parseShort (parts[1]);
	}

	@Override
	public String toString ()
	{
		DecimalFormat df = new DecimalFormat ("00");

		return df.format (this.h) + ":" + df.format (this.m);
	}
}
