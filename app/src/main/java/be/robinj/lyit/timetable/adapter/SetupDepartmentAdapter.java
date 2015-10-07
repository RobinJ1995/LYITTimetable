package be.robinj.lyit.timetable.adapter;

import android.content.Context;
import android.util.Log;
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
	public SetupDepartmentAdapter (Context context, List<Department> departments)
	{
		super (context, R.layout.setup_department_spinner_item, departments);
	}

	@Override
	public View getView (int position, View view, ViewGroup parent)
	{
		Department department = this.getItem (position);

		if (view == null)
			view = LayoutInflater.from (this.getContext ()).inflate (R.layout.setup_department_spinner_item, parent, false);

		TextView tvName = (TextView) view.findViewById (R.id.tvName);
		TextView tvCode = (TextView) view.findViewById (R.id.tvCode);

		tvName.setText (department.getName ());
		tvCode.setText (department.getCode ());

		view.setTag (department);

		return view;
	}

	@Override
	public View getDropDownView (int position, View view, ViewGroup parent)
	{
		return this.getView (position, view, parent);
	}
}
