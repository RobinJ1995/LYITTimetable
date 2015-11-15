package be.robinj.lyit.timetable.entity;

/**
 * Created by robin on 07/10/15.
 */
public class Entity
{
	// Immutable (final) because I don't want these to be changed after initialisation //
	public final String name;
	public final String code;
	// I see no good reason to use separate getter methods over just making them public in this case //

	public Entity (String name, String code)
	{
		this.name = name;
		this.code = code;
	}
}
