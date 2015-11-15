package be.robinj.lyit.timetable;

/**
 * Created by robin on 11/11/15.
 */
public final class Timespan // Immutable because I don't want this to be inherited from //
{
	// Immutable because no reason to change these, and getters have no advantage over just making these members public here //
	public final Time start;
	public final Time end;

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
