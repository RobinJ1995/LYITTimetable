package be.robinj.lyit.timetable;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import be.robinj.lyit.timetable.adapter.MainGroupAdapter;
import be.robinj.lyit.timetable.adapter.MainLessonAdapter;
import be.robinj.lyit.timetable.entity.Group;
import be.robinj.lyit.timetable.entity.Lesson;
import be.robinj.lyit.timetable.listener.MainGroupOnItemSelectedListener;

public final class MainActivity
	extends ActionBarActivity
{
	final HashMap<Group, HashMap<String, List<Lesson>>> timetables = new HashMap<Group, HashMap<String, List<Lesson>>> (); // ... Please have mercy. //

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_main);

		SharedPreferences prefs = this.getSharedPreferences ("prefs", MODE_PRIVATE);
		Set<String> strsGroups = prefs.getStringSet ("groups", null);

		if (strsGroups == null)
		{
			Intent intent = new Intent (this, SetupActivity.class);

			this.startActivityForResult (intent, 1);
		}
		else
		{
			try
			{
				List<Group> groups = new ArrayList<Group> ();
				ThreadPoolCoordinator coordinator = new ThreadPoolCoordinator (this, 2);

				final MainActivity self = this;

				for (String strGroup : strsGroups)
				{
					String[] arrGroup = strGroup.split ("\n");
					final Group group = new Group (arrGroup[0], arrGroup[1], arrGroup[2]);

					groups.add (group);

					coordinator.addTask
					(
						new Runnable ()
						{
							@Override
							public void run ()
							{
								try
								{
									final HashMap<String, List<Lesson>> data = Timetable.fetch (group);

									runOnUiThread (new Runnable ()
									{
										@Override
										public void run ()
										{
											self.gotTimetable (group, data);
										}
									});
								}
								catch (final Exception ex)
								{
									runOnUiThread (new Runnable ()
									{
										@Override
										public void run ()
										{
											self.errorTimetable (group, ex);
										}
									});
								}
							}
						}
					);
				}

				Spinner spiGroup = (Spinner) this.findViewById (R.id.spiGroup);
				Spinner spiDay = (Spinner) this.findViewById (R.id.spiDay);

				ArrayAdapter adapter = ArrayAdapter.createFromResource (this, R.array.days, android.R.layout.simple_spinner_item);
				adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
				spiDay.setAdapter (adapter);
				spiDay.setEnabled (false);

				spiGroup.setAdapter (new MainGroupAdapter (this, groups));
				spiGroup.setOnItemSelectedListener (new MainGroupOnItemSelectedListener (this));
				spiDay.setOnItemSelectedListener (new MainDayOnItemSelectedListener (this));
			}
			catch (Exception ex)
			{
				new AlertDialog.Builder (this)
					.setTitle ("(╯°□°）╯︵ ┻━┻")
					.setMessage (ex.getMessage ())
					.create ()
					.show ();
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
		return super.onOptionsItemSelected (item);
	}

	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data)
	{
		if (requestCode == 1)
			this.recreate ();
	}

	private void gotTimetable (Group group, HashMap<String, List<Lesson>> timetable)
	{
		this.timetables.put (group, timetable);

		Spinner spiGroup = (Spinner) this.findViewById (R.id.spiGroup);
		if (spiGroup.getSelectedItem () == group)
			this.showTimetable (group);
	}

	private void errorTimetable (Group group, Exception ex)
	{
		new AlertDialog.Builder (this)
			.setTitle ("(╯°□°）╯︵ ┻━┻")
			.setMessage ("Couldn't get timetable for group " + group.name + ".\n\n" + ex.getMessage ())
			.create ()
			.show ();
	}

	public void showTimetable (int day)
	{
		Spinner spiGroup = (Spinner) this.findViewById (R.id.spiGroup);

		this.showTimetable ((Group) spiGroup.getSelectedItem (), day);
	}

	public void showTimetable (Group group)
	{
		int day = new GregorianCalendar ().get (Calendar.DAY_OF_WEEK) - 2; // Week starts on Sunday -> -1 // 1-indexed -> -1 //
		if (day == -1) // Sunday acording to GregorianCalendar - 2 //
			day = 6; // Sunday according to my 0-indexed array //

		this.showTimetable (group, day);
	}

	public void showTimetable (Group group, int day)
	{
		HashMap<String, List<Lesson>> timetable = this.timetables.get (group);

		if (timetable != null)
		{
			ProgressBar pbProgress = (ProgressBar) this.findViewById (R.id.pbProgress);
			Spinner spiDay = (Spinner) this.findViewById (R.id.spiDay);
			ListView lvLessons = (ListView) this.findViewById (R.id.lvLessons);

			String[] days = this.getResources ().getStringArray (R.array.days);
			String strDay = days[day];
			spiDay.setEnabled (true);
			if (spiDay.getSelectedItemPosition () != day)
				spiDay.setSelection (day);

			List<Lesson> lessons = timetable.get (strDay);

			lvLessons.setAdapter (lessons != null ? new MainLessonAdapter (this, lessons) : null);

			pbProgress.setVisibility (View.GONE);
			lvLessons.setVisibility (View.VISIBLE);
		}
	}
}
