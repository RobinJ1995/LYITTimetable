package be.robinj.lyit.timetable.async;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Spinner;

import java.util.List;

import be.robinj.lyit.timetable.SetupActivity;
import be.robinj.lyit.timetable.adapter.SetupDepartmentAdapter;
import be.robinj.lyit.timetable.adapter.SetupGroupAdapter;
import be.robinj.lyit.timetable.entity.Group;

/**
 * Created by robin on 11/09/15.
 */
public class AsyncSetupFetchGroups
	extends AsyncTask<Void, Void, List<Group>>
{
	private SetupActivity parent;
	private Spinner spiGroup;

	public AsyncSetupFetchGroups (SetupActivity parent, Spinner spiGroup)
	{
		this.parent = parent;
		this.spiGroup = spiGroup;
	}

	@Override
	protected List<Group> doInBackground (Void... params)
	{
		try
		{
			List<Group> groups = Group.fetch ();
			groups.add (0, null);

			return groups;
		}
		catch (Exception ex)
		{
			ex.printStackTrace ();

			return null;
		}
	}

	@Override
	protected void onPostExecute (List<Group> groups)
	{
		super.onPostExecute (groups);

		SetupGroupAdapter adapter = new SetupGroupAdapter (this.parent, groups);
		this.spiGroup.setAdapter (adapter);
		this.spiGroup.setVisibility (View.VISIBLE);

		this.parent.setStatus (null);


	}
}
