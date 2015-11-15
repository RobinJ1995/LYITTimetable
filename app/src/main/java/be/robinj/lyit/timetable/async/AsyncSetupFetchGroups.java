package be.robinj.lyit.timetable.async;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import be.robinj.lyit.timetable.R;
import be.robinj.lyit.timetable.SetupActivity;
import be.robinj.lyit.timetable.adapter.SetupDepartmentAdapter;
import be.robinj.lyit.timetable.adapter.SetupGroupAdapter;
import be.robinj.lyit.timetable.entity.Department;
import be.robinj.lyit.timetable.entity.Group;

/**
 * Created by robin on 11/09/15.
 */
public final class AsyncSetupFetchGroups
	extends AsyncTask<Void, Void, List<Group>>
{
	private final SetupActivity parent;
	private final ListView lvGroup;
	private final Department department;
	private static List<Group> groups;

	public AsyncSetupFetchGroups (SetupActivity parent, ListView lvGroup, Department department)
	{
		this.parent = parent;
		this.lvGroup = lvGroup;
		this.department = department;
	}

	@Override
	protected List<Group> doInBackground (Void... params)
	{
		try
		{
			List<Group> results = new ArrayList<Group> ();

			if (AsyncSetupFetchGroups.groups == null || AsyncSetupFetchGroups.groups.isEmpty ())
				AsyncSetupFetchGroups.groups = Group.fetch ();

			for (Group group : AsyncSetupFetchGroups.groups)
			{
				if (this.department.code.equals (group.departmentCode))
					results.add (group);
			}

			return results;
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

		SetupGroupAdapter adapter = new SetupGroupAdapter (this.parent, groups, this.parent.getCheckedGroups (), (Button) this.parent.findViewById (R.id.btnContinue));
		this.lvGroup.setAdapter (adapter);
		this.lvGroup.setVisibility (View.VISIBLE);

		this.parent.setInstructions ("Please select your group(s).");
		this.parent.setStatus (null);
	}
}
