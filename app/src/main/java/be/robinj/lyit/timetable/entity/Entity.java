package be.robinj.lyit.timetable.entity;

/**
 * Created by robin on 07/10/15.
 */
public class Entity
{
	private String name;
	private String code;

	protected Entity ()
	{
	}

	public Entity (String name, String code)
	{
		this.name = name;
		this.code = code;
	}

	public String getName ()
	{
		return this.name;
	}

	public String getCode ()
	{
		return this.code;
	}
}
