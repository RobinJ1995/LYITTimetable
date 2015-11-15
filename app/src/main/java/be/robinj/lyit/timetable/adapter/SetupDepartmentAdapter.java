package be.robinj.lyit.timetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import be.robinj.lyit.timetable.R;
import be.robinj.lyit.timetable.entity.Department;

/**
 * Created by robin on 11/09/15.
 */
public class SetupDepartmentAdapter
	extends ArrayAdapter<Department>
{
	public SetupDepartmentAdapter (Context context, List<Department> entities)
	{
		super (context, R.layout.setup_department_spinner_item, entities);
	}

	@Override
	public View getView (int position, View view, ViewGroup parent)
	{
		return this.getView (position, view, parent, false);
	}

	public View getView (int position, View view, ViewGroup parent, boolean dropdown)
	{
		Department entity = this.getItem (position);

		if (view == null)
			view = LayoutInflater.from (this.getContext ()).inflate (R.layout.setup_department_spinner_item, parent, false);

		TextView tvName = (TextView) view.findViewById (R.id.tvName);
		TextView tvCode = (TextView) view.findViewById (R.id.tvCode);

		String name;
		String code;

		if (entity == null)
		{
			if (! dropdown)
			{
				name = "Department";
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
			name = entity.name;
			code = entity.code;
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
