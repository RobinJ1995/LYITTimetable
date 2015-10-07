package be.robinj.lyit.timetable.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import be.robinj.lyit.timetable.R;
import be.robinj.lyit.timetable.SetupActivity;
import be.robinj.lyit.timetable.async.AsyncSetupFetchDepartments;
import be.robinj.lyit.timetable.async.AsyncSetupFetchGroups;
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
		Department department = (Department) view.getTag ();
		Spinner spiGroup = (Spinner) this.parent.findViewById (R.id.spiGroup);

		if (department != null)
		{
			this.parent.setStatus ("Fetching groups...");

			(new AsyncSetupFetchGroups (this.parent, spiGroup, department)).execute ();
		}
		else
		{
			spiGroup.setVisibility (View.GONE);
		}
	}

	@Override
	public void onNothingSelected (AdapterView<?> parent)
	{
	}
}
