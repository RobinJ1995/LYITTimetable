package be.robinj.lyit.timetable;

import android.os.AsyncTask;

import java.util.HashMap;
import java.util.List;

import be.robinj.lyit.timetable.entity.Department;

/**
 * Created by robin on 11/09/15.
 */
public class AsyncSetupFetchDepartments extends AsyncTask<Void, Void, List<Department>>
{
	@Override
	protected List<Department> doInBackground (Void... params)
	{
		try
		{
			return Department.fetch ();
		} catch (Exception e)
		{
			e.printStackTrace ();
			return null;
		}
	}
}
