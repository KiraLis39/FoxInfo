package fox;


public class ThreadsScanner {	
	private ThreadGroup currentGroup;
	private Thread[] lstThreads;
	
	public ThreadsScanner() {
		currentGroup = Thread.currentThread().getThreadGroup();
		lstThreads = new Thread[currentGroup.activeCount()];

		currentGroup.enumerate(lstThreads);

		for (int i = 0; i < currentGroup.activeCount(); i++) {
			System.out.println(ThreadsScanner.class.getSimpleName() + "Thread " + i + ": " + lstThreads[i].getName());
		}
	}
}