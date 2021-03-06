package be.robinj.lyit.timetable.async;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Spinner;

import java.util.List;

import be.robinj.lyit.timetable.SetupActivity;
import be.robinj.lyit.timetable.adapter.SetupDepartmentAdapter;
import be.robinj.lyit.timetable.entity.Department;
import be.robinj.lyit.timetable.entity.Group;
import be.robinj.lyit.timetable.listener.SetupDepartmentOnItemSelectedListener;

/**
 * Created by robin on 11/09/15.
 */
public final class AsyncSetupFetchDepartments extends AsyncTask<Void, Void, List<Department>>
{
	private final SetupActivity parent;
	private final Spinner spiDepartment;
	private String errorMessage;

	public AsyncSetupFetchDepartments (SetupActivity parent, Spinner spiDepartment)
	{
		this.parent = parent;
		this.spiDepartment = spiDepartment;
	}

	@Override
	protected List<Department> doInBackground (Void... params)
	{
		try
		{
			List<Department> departments = Department.fetch ();
			departments.add (0, null);

			return departments;
		}
		catch (Exception ex)
		{
			this.errorMessage = ex.getMessage ();

			return null;
		}
	}

	@Override
	protected void onPostExecute (List<Department> departments)
	{
		super.onPostExecute (departments);

		if (departments != null)
		{
			SetupDepartmentAdapter adapter = new SetupDepartmentAdapter (this.parent, departments);
			this.spiDepartment.setAdapter (adapter);
			this.spiDepartment.setVisibility (View.VISIBLE);

			this.parent.setStatus (null);

			SetupDepartmentOnItemSelectedListener listener = new SetupDepartmentOnItemSelectedListener (this.parent);
			this.spiDepartment.setOnItemSelectedListener (listener);
		}
		else
		{
			new AlertDialog.Builder (this.parent)
				.setTitle ("(╯°□°）╯︵ ┻━┻")
				.setMessage ("Couldn't get list of departments.\n\n" + this.errorMessage)
				.create ()
				.show ();
		}
	}
}
