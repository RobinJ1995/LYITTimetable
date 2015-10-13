package be.robinj.lyit.timetable.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * Created by robin on 13/10/15.
 */
public class SetupGroupOnTextviewClickedListener implements OnClickListener
{
	private CheckBox cbxSelected;

	public SetupGroupOnTextviewClickedListener (CheckBox cbxSelected)
	{
		this.cbxSelected = cbxSelected;
	}

	@Override
	public void onClick (View view)
	{
		this.cbxSelected.toggle ();
	}
}
