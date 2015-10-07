package be.robinj.lyit.timetable.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import be.robinj.lyit.timetable.R;
import be.robinj.lyit.timetable.SetupActivity;
import be.robinj.lyit.timetable.async.AsyncSetupFetchGroups;
import be.robinj.lyit.timetable.entity.Department;
import be.robinj.lyit.timetable.entity.Group;

/**
 * Created by robin on 07/10/15.
 */
public class SetupGroupOnItemSelectedListener
	implements AdapterView.OnItemSelectedListener
{
	private SetupActivity parent;

	public SetupGroupOnItemSelectedListener (SetupActivity parent)
	{
		this.parent = parent;
	}

	@Override
	public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
	{
		Group group = (Group) view.getTag ();

		if (group != null)
		{
			TextView tvIntroduction = (TextView) this.parent.findViewById (R.id.tvIntroduction);
			Spinner spiDepartment = (Spinner) this.parent.findViewById (R.id.spiDepartment);
			Spinner spiGroup = (Spinner) this.parent.findViewById (R.id.spiGroup);


			tvIntroduction.setVisibility (View.GONE);
			spiDepartment.setEnabled (false);
			spiGroup.setEnabled (false);
			spiDepartment.setAlpha (0.6F);
			spiGroup.setAlpha (0.6F);

			this.parent.setStatus ("Parsing timetable...");

			//(new AsyncSetupFetchGroups (this.parent, spiGroup, department)).execute ();
		}
	}

	@Override
	public void onNothingSelected (AdapterView<?> parent)
	{
	}
}
