package be.robinj.lyit.timetable.async;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import be.robinj.lyit.timetable.SetupActivity;
import be.robinj.lyit.timetable.adapter.SetupDepartmentAdapter;
import be.robinj.lyit.timetable.adapter.SetupGroupAdapter;
import be.robinj.lyit.timetable.entity.Department;
import be.robinj.lyit.timetable.entity.Group;
import be.robinj.lyit.timetable.listener.SetupGroupOnItemSelectedListener;

/**
 * Created by robin on 11/09/15.
 */
public class AsyncSetupFetchGroups
	extends AsyncTask<Void, Void, List<Group>>
{
	private SetupActivity parent;
	private ListView lvGroup;
	private Department department;
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
				if (this.department.getCode ().equals (group.getDepartmentCode ()))
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

		SetupGroupAdapter adapter = new SetupGroupAdapter (this.parent, groups);
		this.lvGroup.setAdapter (adapter);
		this.lvGroup.setVisibility (View.VISIBLE);

		this.parent.setStatus (null);

		SetupGroupOnItemSelectedListener listener = new SetupGroupOnItemSelectedListener (this.parent);
		this.lvGroup.setOnItemSelectedListener (listener);
	}
}
