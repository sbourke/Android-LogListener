package de.otw.android.loglistener;

public interface LogListener {
	/**
	 * Listener that has to be used with L.java. Allows an Application to be informed about
	 * a specific log message.
	 * 
	 * @author <a href="http//www.twitter.com/onlythoughtwork">Stephan Linzner</a>
	 */
	public void onLogMessage(String tag, String msg);
}
