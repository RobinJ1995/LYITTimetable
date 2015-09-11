package be.robinj.lyit.timetable;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import be.robinj.lyit.timetable.adapter.SetupDepartmentAdapter;
import be.robinj.lyit.timetable.entity.Department;

/**
 * Created by robin on 11/09/15.
 */
public class AsyncSetupFetchDepartments extends AsyncTask<Void, Void, List<Department>>
{
	private Context context;
	private Spinner spiDepartment;

	public AsyncSetupFetchDepartments (Context context, Spinner spiDepartment)
	{
		this.context = context;
		this.spiDepartment = spiDepartment;
	}

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

	@Override
	protected void onPostExecute (List<Department> departments)
	{
		super.onPostExecute (departments);

		SetupDepartmentAdapter adapter = new SetupDepartmentAdapter (this.context, departments);
		this.spiDepartment.setAdapter (adapter);
	}
}
