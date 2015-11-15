package be.robinj.lyit.timetable.listener;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.util.List;

import be.robinj.lyit.timetable.entity.Group;

/**
 * Created by robin on 13/10/15.
 */
public final class SetupGroupOnCheckedChangeListener implements OnCheckedChangeListener
{
	private final List<Group> checkedGroups;
	private final Group group;
	private final Button btnContinue;

	public SetupGroupOnCheckedChangeListener (List<Group> checkedGroups, Group group, Button btnContinue)
	{
		this.checkedGroups = checkedGroups;
		this.group = group;
		this.btnContinue = btnContinue;
	}

	@Override
	public void onCheckedChanged (CompoundButton cbxSelected, boolean isChecked)
	{
		if (isChecked)
		{
			if (! this.checkedGroups.contains (group))
				this.checkedGroups.add (group);
		}
		else
		{
			this.checkedGroups.remove (group);
		}

		this.btnContinue.setVisibility (this.checkedGroups.size () > 0 ? View.VISIBLE : View.GONE);
	}
}
