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
import be.robinj.lyit.timetable.entity.Lesson;

/**
 * Created by robin on 11/09/15.
 */
public final class MainLessonAdapter
	extends ArrayAdapter<Lesson>
{
	public MainLessonAdapter (Context context, List<Lesson> entities)
	{
		super (context, R.layout.main_lesson_spinner_item, entities);
	}

	@Override
	public View getView (int position, View view, ViewGroup parent)
	{
		Lesson entity = this.getItem (position);

		if (view == null)
			view = LayoutInflater.from (this.getContext ()).inflate (R.layout.main_lesson_spinner_item, parent, false);

		TextView tvName = (TextView) view.findViewById (R.id.tvName);
		TextView tvStart = (TextView) view.findViewById (R.id.tvStart);
		TextView tvDuration = (TextView) view.findViewById (R.id.tvDuration);
		TextView tvEnd = (TextView) view.findViewById (R.id.tvEnd);

		tvName.setText (entity.name);
		tvStart.setText (entity.timespan.start.toString ());
		tvDuration.setText ("");
		tvEnd.setText (entity.timespan.end.toString ());

		view.setTag (entity);

		return view;
	}

	@Override
	public View getDropDownView (int position, View view, ViewGroup parent)
	{
		return this.getView (position, view, parent);
	}
}
