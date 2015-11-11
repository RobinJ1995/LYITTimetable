package be.robinj.lyit.timetable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import be.robinj.lyit.timetable.entity.Group;

public class MainActivity
	extends ActionBarActivity
{

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_main);

		SharedPreferences prefs = this.getSharedPreferences ("prefs", MODE_PRIVATE);
		Set<String> strsGroups = prefs.getStringSet ("groups", null);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build (); // If this piece of code is still in when I hand in the assignment, I probably didn't have enough time to do it properly. //
		StrictMode.setThreadPolicy (policy);

		if (strsGroups == null)
		{
			Intent intent = new Intent (this, SetupActivity.class);
			this.startActivity (intent);
		}
		else
		{
			try
			{
				List<Group> groups = new ArrayList<Group> ();

				for (String strGroup : strsGroups)
				{
					String[] arrGroup = strGroup.split ("\n");
					Group group = new Group (arrGroup[0], arrGroup[1], arrGroup[2]);

					groups.add (group);
				}

				Timetable.fetch (groups);
			}
			catch (Exception ex)
			{
				ex.printStackTrace (); //TODO//
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater ().inflate (R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId ();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected (item);
	}
}
