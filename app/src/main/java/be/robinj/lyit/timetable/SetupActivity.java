package be.robinj.lyit.timetable;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

public class SetupActivity
	extends ActionBarActivity
{

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_setup);

		Spinner spiDepartment = (Spinner) this.findViewById (R.id.spiDepartment);

		(new AsyncSetupFetchDepartments (this, spiDepartment)).execute ();
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater ().inflate (R.menu.menu_setup, menu);
		return true;
	}
}
