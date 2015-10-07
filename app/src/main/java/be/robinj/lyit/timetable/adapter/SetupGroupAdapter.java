package be.robinj.lyit.timetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import be.robinj.lyit.timetable.R;
import be.robinj.lyit.timetable.entity.Group;

/**
 * Created by robin on 11/09/15.
 */
public class SetupGroupAdapter
	extends ArrayAdapter<Group>
{
	public SetupGroupAdapter (Context context, List<Group> entities)
	{
		super (context, R.layout.setup_entity_spinner_item, entities);
	}

	@Override
	public View getView (int position, View view, ViewGroup parent)
	{
		return this.getView (position, view, parent, false);
	}

	public View getView (int position, View view, ViewGroup parent, boolean dropdown)
	{
		Group entity = this.getItem (position);

		if (view == null)
			view = LayoutInflater.from (this.getContext ()).inflate (R.layout.setup_entity_spinner_item, parent, false);

		TextView tvName = (TextView) view.findViewById (R.id.tvName);
		TextView tvCode = (TextView) view.findViewById (R.id.tvCode);

		String name;
		String code;

		if (entity == null)
		{
			if (! dropdown)
			{
				name = "Group";
				code = "Please make a selection...";
			}
			else
			{
				name = "";
				code = "--";
			}
		}
		else
		{
			name = entity.getName ();
			code = entity.getCode ();
		}

		tvName.setText (name);
		tvCode.setText (code);

		view.setTag (entity);

		return view;
	}

	@Override
	public View getDropDownView (int position, View view, ViewGroup parent)
	{
		return this.getView (position, view, parent, true);
	}
}
