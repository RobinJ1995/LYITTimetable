package be.robinj.lyit.timetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
public class SetupGroupAdapter
	extends ArrayAdapter<Group>
{
	public SetupGroupAdapter (Context context, List<Group> entities)
	{
		super (context, R.layout.setup_group_listview_item, entities);
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

		tvName.setText (entity.getName ());
		tvCode.setText (entity.getCode ());

		SetupGroupOnTextviewClickedListener clickedListener = new SetupGroupOnTextviewClickedListener (cbxSelected);
		tvName.setOnClickListener (clickedListener);
		tvCode.setOnClickListener (clickedListener);

		SetupGroupOnCheckedChangeListener checkedChangeListener = new SetupGroupOnCheckedChangeListener ();
		cbxSelected.setOnCheckedChangeListener (checkedChangeListener);

		view.setTag (entity);

		return view;
	}
}
