package be.robinj.lyit.timetable;

import android.app.Activity;
import android.app.AlertDialog;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by robin on 11/11/15.
 */
public class ThreadPoolCoordinator // Mutable (not final), because it would make sense to extend this class in future //
{
	private final Activity parent;
	private final Set<ThreadPoolThread> threads = new HashSet<ThreadPoolThread> ();
	private final BlockingQueue<Runnable> tasks = new LinkedTransferQueue<Runnable> ();

	public ThreadPoolCoordinator (Activity parent, int numberOfThreads)
	{
		this.parent = parent;

		for (int i = 0; i < numberOfThreads; i++)
		{
			ThreadPoolThread thread = new ThreadPoolThread (this);
			thread.start ();

			this.threads.add (thread);
		}
	}

	public boolean addTask (Runnable runnable) throws InterruptedException
	{
		return this.tasks.offer (runnable, 2, TimeUnit.SECONDS);
	}

	public Runnable getTask () throws InterruptedException
	{
		return this.tasks.take ();
	}

	public void showErrorMessage (final String message)
	{
		this.parent.runOnUiThread
		(
			new Runnable ()
			{
				@Override
				public void run ()
				{
					new AlertDialog.Builder (parent)
						.setTitle ("(╯°□°）╯︵ ┻━┻")
						.setMessage ("Something went wrong while executing a background task.\n\n" + message)
						.create ()
						.show ();
				}
			}
		);
	}
}
