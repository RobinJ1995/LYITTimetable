package be.robinj.lyit.timetable;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import be.robinj.lyit.timetable.async.AsyncSetupFetchDepartments;

public class SetupActivity
	extends ActionBarActivity
{
	private TextView tvStatus;
	private ProgressBar progressBar;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_setup);

		this.progressBar = (ProgressBar) this.findViewById (R.id.progressBar);
		this.tvStatus = (TextView) this.findViewById (R.id.tvStatus);

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
		}
		else
		{
			this.tvStatus.setText (status);
			this.tvStatus.setVisibility (View.VISIBLE);
			this.progressBar.setVisibility (View.VISIBLE);
		}
	}
}
