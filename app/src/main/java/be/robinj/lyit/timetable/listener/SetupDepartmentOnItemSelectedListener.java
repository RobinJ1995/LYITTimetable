package be.robinj.lyit.timetable.listener;

import android.view.View;
import android.widget.AdapterView;

import be.robinj.lyit.timetable.SetupActivity;
import be.robinj.lyit.timetable.entity.Department;

/**
 * Created by robin on 07/10/15.
 */
public class SetupDepartmentOnItemSelectedListener implements AdapterView.OnItemSelectedListener
{
	private SetupActivity parent;

	public SetupDepartmentOnItemSelectedListener (SetupActivity parent)
	{
		this.parent = parent;
	}

	@Override
	public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
	{
		this.parent.setStatus ("Fetching groups...");

		Department department = (Department) view.getTag ();
	}

	@Override
	public void onNothingSelected (AdapterView<?> parent)
	{
	}
}
