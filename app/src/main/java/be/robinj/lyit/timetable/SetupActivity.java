package be.robinj.lyit.timetable;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.robinj.lyit.timetable.async.AsyncSetupFetchDepartments;
import be.robinj.lyit.timetable.entity.Group;

public class SetupActivity
	extends ActionBarActivity
{
	private TextView tvStatus;
	private ProgressBar progressBar;
	private TextView tvInstructions;

	private List<Group> checkedGroups = new ArrayList<Group> ();

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_setup);

		this.progressBar = (ProgressBar) this.findViewById (R.id.progressBar);
		this.tvStatus = (TextView) this.findViewById (R.id.tvStatus);
		this.tvInstructions = (TextView) this.findViewById (R.id.tvInstructions);

		Spinner spiDepartment = (Spinner) this.findViewById (R.id.spiDepartment);

		this.setStatus ("Fetching departments...");

		(new AsyncSetupFetchDepartments (this, spiDepartment)).execute ();
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater ().inflate (R.menu.menu_setup, menu);
		return true;
	}

	public void setStatus (String status)
	{
		if (status == null)
		{
			this.tvStatus.setVisibility (View.GONE);
			this.progressBar.setVisibility (View.GONE);
			this.tvInstructions.setVisibility (View.VISIBLE);
		}
		else
		{
			this.tvStatus.setText (status);
			this.tvStatus.setVisibility (View.VISIBLE);
			this.progressBar.setVisibility (View.VISIBLE);
			this.tvInstructions.setVisibility (View.GONE);
		}
	}

	public void btnContinue_clicked (View view)
	{
		this.findViewById (R.id.spiDepartment).setVisibility (View.GONE);
		this.findViewById (R.id.lvGroup).setVisibility (View.GONE);
		this.findViewById (R.id.btnContinue).setVisibility (View.GONE);

		this.setInstructions (null);

		this.setStatus ("Saving preferences...");

		SharedPreferences prefs = this.getSharedPreferences ("prefs", MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit ();

		Set<String> groups = new HashSet<String> ();
		for (Group group : this.checkedGroups)
			groups.add (group.getName () + "\n" + group.getCode () + "\n" + group.getDepartmentCode ());

		editor.putStringSet ("groups", groups);

		editor.commit ();

		this.finish ();
	}

	public void setInstructions (String instructions)
	{
		if (instructions == null)
		{
			this.tvInstructions.setVisibility (View.GONE);
		}
		else
		{
			this.tvInstructions.setText (instructions);
			this.tvInstructions.setVisibility (View.VISIBLE);
		}
	}

	public List<Group> getCheckedGroups ()
	{
		return this.checkedGroups;
	}
}
