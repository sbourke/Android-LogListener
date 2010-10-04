package de.otw.android.loglistener;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

public final class L {
	
	/**
	 * A wrapper around the Android Log class providing a static Log Tag. 
	 * It also provides a concept called LogListeners, which gives you more log power.
	 * Memory Allocation, Runtime Measurement.
	 * You can register listeners which will fire when a specific log message is triggered. 
	 * A use case ie. is to foward log messages to a debug UI console.
	 * 
	 * @author <a href="http//www.twitter.com/onlythoughtwork">Stephan Linzner</a>
	 */
	
	public static final int VERBOSE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int ERROR = 4;
    public static final int ASSERT = 5;
    
    /**
     * Default value is: MODE_ENGINEERING, which will log all messages as normal.
     * MODE_PRODUCTIVE just logs messages, which are enforced by --force flag.
     * No LogListeners will be triggered using this modes.
     */
    public static final int MODE_ENGINEERING = 6;
    public static final int MODE_PRODUCTIVE = 7;
    
    /**
     * Same behaviour as the other modes, but LogListeners are triggered as well 
     */
    public static final int MODE_ENGINEERING_LOGLISTENER = 8;
    public static final int MODE_PRODUCTIVE_LOGLISTENER = 9;
    
    /**
     * If this flag is set in a call to a log message the message will be written to log
     * regardless of current mode! 
     */
    public static  int MODE_FORCE = 10;

    private static ArrayList<LogListener> logListeners = new ArrayList<LogListener>();
    private static HashMap<String, Long> startTimes = new HashMap<String, Long>();
	private static HashMap<String, Integer> counters = new HashMap<String, Integer>();
	private static int mode = MODE_ENGINEERING;

    private L() { }
    
    /**
     * Must be called as a precondition before using augmented Log class L.class
     * to set current logging mode
     */
    public static void setMode(int mode) {
    	L.mode = mode;
    }

    
    /**
     * Following methods just delegate calls to standard android Log.class API calls
     */
    
    public static int v(String tag, String msg) {
        	return v(tag, msg, -1);
    }

    public static int v(String tag, String msg, Throwable tr) {
    		return v(tag, msg, tr, -1);
    }
    
    public static int v(String tag, String msg, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.v(tag, msg);
    	else
    		return -1;
    }

    public static int v(String tag, String msg, Throwable tr, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.v(tag, msg, tr);
    	else
    		return -1;
    }
    
    public static int d(String tag, String msg) {
        return d(tag, msg, -1);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return d(tag, msg, tr, -1);
    }
    
    public static int d(String tag, String msg, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.d(tag, msg);
    	else
    		return -1;
    }

    public static int d(String tag, String msg, Throwable tr, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.d(tag, msg, tr);
    	else
    		return -1;
    }

    public static int i(String tag, String msg) {
        return i(tag, msg, -1);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return i(tag, msg, tr, -1);
    }
    
    public static int i(String tag, String msg, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.i(tag, msg);
    	else
    		return -1;
    }

    public static int i(String tag, String msg, Throwable tr, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.i(tag, msg, tr);
    	else
    		return -1;
    }

    public static int w(String tag, String msg) {
        return w(tag, msg, -1);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return w(tag, msg, tr, -1);
    }
    
    public static int w(String tag, String msg, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.w(tag, msg);
    	else
    		return -1;
    }

    public static int w(String tag, String msg, Throwable tr, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.w(tag, msg, tr);
    	else
    		return -1;
    }

    public static int e(String tag, String msg) {
        return e(tag, msg, -1);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return e(tag, msg, tr, -1);
    }
    
    public static int e(String tag, String msg, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.e(tag, msg);
    	else
    		return -1;
    }

    public static int e(String tag, String msg, Throwable tr, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.e(tag, msg, tr);
    	else
    		return -1;
    }
    
    public static int wtf(String tag, String msg) {
        return wtf(tag, msg, -1);
    }

    public static int wtf(String tag, String msg, Throwable tr) {
        return wtf(tag, msg, tr, -1);
    }
    
    public static int wtf(String tag, String msg, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.wtf(tag, msg);
    	else
    		return -1;
    }

    public static int wtf(String tag, String msg, Throwable tr, int force) {
    	if(force == MODE_FORCE || mode == MODE_ENGINEERING || mode == MODE_ENGINEERING_LOGLISTENER)
    		return Log.wtf(tag, msg, tr);
    	else
    		return -1;
    }
    
    /**
     * notifies screen log listeners
     * @param LOG_TAG
	 * @param msg
     */
    public static int ll(String tag, String msg) {
    	notifyListeners(tag, msg);
    	return 1;
    }
    
    /**
     * notifies screen log listeners and writes to specified verbose log
     * @param LOG_TAG
	 * @param msg
     */
    public static int llv(String tag, String msg) {
    	notifyListeners(tag, msg);
    	return v(tag, msg, -1);
    }
    
    /**
     * notifies screen log listeners and writes to specified verbose log
     * @param LOG_TAG
	 * @param msg
	 * @param force MODE_FORCE
     */
    public static int llv(String tag, String msg, int force) {
    	notifyListeners(tag, msg);
    	return v(tag, msg, force);
    }
    
    /**
     * notifies screen log listeners and writes to specified debug log
     * @param LOG_TAG
	 * @param msg
     */
    public static int lld(String tag, String msg) {
    	notifyListeners(tag, msg);
    	return d(tag, msg, -1);
    }
    
    /**
     * notifies screen log listeners and writes to specified debug log
     * @param LOG_TAG
	 * @param msg
	 * @param force MODE_FORCE
     */
    public static int lld(String tag, String msg, int force) {
    	notifyListeners(tag, msg);
    	return d(tag, msg, force);
    }
    
    /**
     * notifies screen log listeners and writes to specified info log
     * @param LOG_TAG
	 * @param msg
     */
    public static int lli(String tag, String msg) {
    	notifyListeners(tag, msg);
    	return i(tag, msg, -1);
    }
    
    /**
     * notifies screen log listeners and writes to specified info log
     * @param LOG_TAG
	 * @param msg
	 * @param force MODE_FORCE
     */
    public static int lli(String tag, String msg, int force) {
    	notifyListeners(tag, msg);
    	return i(tag, msg, force);
    }
    
    /**
     * notifies screen log listeners and writes to specified warning log
     * @param LOG_TAG
	 * @param msg
     */
    public static int llw(String tag, String msg) {
    	notifyListeners(tag, msg);
    	return w(tag, msg, -1);
    }
    
    /**
     * notifies screen log listeners and writes to specified warning log
     * @param LOG_TAG
	 * @param msg
	 * @param force MODE_FORCE
     */
    public static int llw(String tag, String msg, int force) {
    	notifyListeners(tag, msg);
    	return w(tag, msg, force);
    }
    
    /**
     * notifies screen log listeners and writes to specified error log
     * @param LOG_TAG
	 * @param msg
	 * @param force MODE_FORCE
     */
    public static int lle(String tag, String msg) {
    	notifyListeners(tag, msg);
    	return e(tag, msg, -1);
    }
    
    /**
     * notifies screen log listeners and writes to specified error log
     * @param LOG_TAG
	 * @param msg
	 * @param force MODE_FORCE
     */
    public static int lle(String tag, String msg, int force) {
    	notifyListeners(tag, msg);
    	return e(tag, msg, force);
    }

    /**
     * Write to standard output
     * @param tr An exception to log
     */
    public static void println(String tag, String msg) {
    	System.out.println("Log: "+ tag + ", " +msg);
    }
    
    /**
     * Write to standard output including a stack trace
     * @param tr An exception to log
     */
    public static void println(String tag, String msg, Throwable tr) {
    	System.out.println("Log: "+ tag + ", " + msg + " Stack Trace: " + getStackTraceString(tr));
    }
    
    /**
     * Handy function to get a loggable stack trace from a Throwable
     * @param tr An exception to log
     */
    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "no stack trace available...";
        }
        return getStackTraceString(tr);
    }
    
    /**
     * General Info about current memory allocation
     * @param LOG_TAG
     */
    public static void memory(String LOG_TAG, String msg) {
        Runtime rt = Runtime.getRuntime();
        String memInfo = msg + " (" + rt.freeMemory() / 1024 + "k free mem, " + rt.totalMemory() / 1024 + "k total mem, " + rt.maxMemory() / 1024
        + "k max VM mem)";
        d(LOG_TAG, memInfo);
    }

    /**
     * Take starting times for runtime measurements (one per LOG_TAG)
     * @param LOG_TAG
     */
	public static void startT(String LOG_TAG) {
		startTimes.put(LOG_TAG, System.currentTimeMillis());
	}

	/**
	 * Calculate runtime and write to debug log
	 * @param LOG_TAG
	 * @param msg
	 */
	public static void stopT(String LOG_TAG, String msg) {
		L.d(LOG_TAG, msg + (System.currentTimeMillis() - startTimes.get(LOG_TAG).longValue()));
	}

	/**
	 * Start counter for given LOG_TAG
	 * @param LOG_TAG
	 */
	public static void startC(String LOG_TAG) {
		counters.put(LOG_TAG, 0);
	}
	
	/**
	 * Increment counter for given LOG_TAG and write to log
	 * @param LOG_TAG
	 * @param msg
	 */
	public static void incC(String LOG_TAG, String msg) {
		int incrementedCount = counters.get(LOG_TAG)+1;
		counters.put(LOG_TAG, incrementedCount);
		L.lld(LOG_TAG,  msg + incrementedCount);
	}
	
	/**
	 * Increment counter for given LOG_TAG and write LogListeners and debug log
	 * @param LOG_TAG
	 * @param msg
	 */
	public static void sincC(String LOG_TAG, String msg) {
		int incrementedCount = counters.get(LOG_TAG)+1;
		counters.put(LOG_TAG, incrementedCount);
		L.lld(LOG_TAG, msg + incrementedCount);
	}
	
	public static void registerListener(LogListener logL){
		logListeners.add(logL);
	}
	
	public static void unregisterListener(LogListener logL){
		if(logListeners.contains(logL))
			logListeners.remove(logL);
	}
	
	public static boolean listenersEmpty(){
		return logListeners.isEmpty();
	}
	
	/**
	 * Notifies all listeners when a Log message is raised. Use mode constants L.MODE_ENGINEERING_LOGLISTENER
	 * and L.MODE_PRODUCTIVE_LOGLISTENER to allow raising of LogListener messages
	 */
	private static void notifyListeners(String tag, String msg){
		if(mode == MODE_ENGINEERING_LOGLISTENER || mode == MODE_PRODUCTIVE_LOGLISTENER) {
			for(LogListener listener : logListeners){
				listener.onLogMessage(tag, msg);
			}
		}
	}
}

