package be.robinj.lyit.timetable;

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
	private final Set<ThreadPoolThread> threads = new HashSet<ThreadPoolThread> ();
	private final BlockingQueue<Runnable> tasks = new LinkedTransferQueue<Runnable> (); //TODO//

	public ThreadPoolCoordinator (int numberOfThreads)
	{
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
}
