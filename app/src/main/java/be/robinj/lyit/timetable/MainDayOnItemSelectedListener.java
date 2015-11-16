package be.robinj.lyit.timetable;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by robin on 07/10/15.
 */
public final class MainDayOnItemSelectedListener
	implements AdapterView.OnItemSelectedListener
{
	private final MainActivity parent;

	public MainDayOnItemSelectedListener (MainActivity parent)
	{
		this.parent = parent;
	}

	@Override
	public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
	{
		this.parent.showTimetable (position);
	}

	@Override
	public void onNothingSelected (AdapterView<?> parent)
	{
	}
}
