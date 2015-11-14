package be.robinj.lyit.timetable.listener;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import be.robinj.lyit.timetable.MainActivity;
import be.robinj.lyit.timetable.entity.Group;

/**
 * Created by robin on 07/10/15.
 */
public class MainGroupOnItemSelectedListener
	implements AdapterView.OnItemSelectedListener
{
	private MainActivity parent;

	public MainGroupOnItemSelectedListener (MainActivity parent)
	{
		this.parent = parent;
	}

	@Override
	public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
	{
		Group group = (Group) view.getTag ();

		this.parent.showTimetable (group);
	}

	@Override
	public void onNothingSelected (AdapterView<?> parent)
	{
	}
}
