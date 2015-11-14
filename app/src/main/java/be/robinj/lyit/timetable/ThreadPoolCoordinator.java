package be.robinj.lyit.timetable;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by robin on 11/11/15.
 */
public class ThreadPoolCoordinator
{
	private Set<ThreadPoolThread> threads = new HashSet<ThreadPoolThread> ();
	private BlockingQueue<Runnable> tasks = new LinkedTransferQueue<Runnable> ();

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
