package be.robinj.lyit.timetable;

/**
 * Created by robin on 11/11/15.
 */
public class ThreadPoolThread extends Thread // Mutable, because it would make sense to extend this class in future //
{
	private final ThreadPoolCoordinator coordinator;

	public ThreadPoolThread (ThreadPoolCoordinator coordinator)
	{
		this.coordinator = coordinator;
	}

	@Override
	public void run ()
	{
		while (true)
		{
			try
			{
				this.coordinator.getTask ().run ();
			}
			catch (Exception ex)
			{
				this.coordinator.showErrorMessage (ex.getMessage ());
			}
		}
	}
}
