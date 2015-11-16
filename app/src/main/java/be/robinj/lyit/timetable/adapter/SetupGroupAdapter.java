package be.robinj.lyit.timetable.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import be.robinj.lyit.timetable.R;
import be.robinj.lyit.timetable.entity.Group;
import be.robinj.lyit.timetable.listener.SetupGroupOnCheckedChangeListener;
import be.robinj.lyit.timetable.listener.SetupGroupOnTextviewClickedListener;

/**
 * Created by robin on 11/09/15.
 */
public final class SetupGroupAdapter
	extends ArrayAdapter<Group>
{
	private Button btnContinue;

	private List<Group> checkedGroups;

	public SetupGroupAdapter (Context context, List<Group> entities, List<Group> checkedGroups, Button btnContinue)
	{
		super (context, R.layout.setup_group_listview_item, entities);

		this.checkedGroups = checkedGroups;
		this.btnContinue = btnContinue;
	}

	@Override
	public View getView (int position, View view, ViewGroup parent)
	{
		Group entity = this.getItem (position);

		if (view == null)
			view = LayoutInflater.from (this.getContext ()).inflate (R.layout.setup_group_listview_item, parent, false);

		TextView tvName = (TextView) view.findViewById (R.id.tvName);
		TextView tvCode = (TextView) view.findViewById (R.id.tvCode);
		CheckBox cbxSelected = (CheckBox) view.findViewById (R.id.cbxSelected);

		cbxSelected.setOnCheckedChangeListener (null); // Views inside a ListView get recycled; Get rid of the old listener //

		tvName.setText (entity.name);
		tvCode.setText (entity.code);
		cbxSelected.setChecked (this.checkedGroups.contains (entity));

		SetupGroupOnTextviewClickedListener clickedListener = new SetupGroupOnTextviewClickedListener (cbxSelected);
		tvName.setOnClickListener (clickedListener);
		tvCode.setOnClickListener (clickedListener);

		SetupGroupOnCheckedChangeListener checkedChangeListener = new SetupGroupOnCheckedChangeListener (this.checkedGroups, entity, this.btnContinue);
		cbxSelected.setOnCheckedChangeListener (checkedChangeListener);

		view.setTag (entity);

		return view;
	}
}
