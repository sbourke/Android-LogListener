package de.otw.android.loglistener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class LogListenerDemo extends Activity {

	private static final String LOG_TAG = "LogListenerDemo";

	private static final int MSG_LOG = 0;
	private static final int MENU_SHOW_DEBUG_CONSOLE = 1;

	private ScrollView mConsoleWrapper;
	private TextView mConsole;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(final Message msg) {
			switch (msg.what) {
			case MSG_LOG:
				appendConsoleText(getString(R.string.log_msg)
						+ ((String) msg.obj));
				break;
			default:
				break;
			}
		}
	};

	private Runnable mGenerateLogMessagesTask = new Runnable() {
		public void run() {
				Double random = Math.random();
				long timeOffset = new Double(random * 5000).longValue();
				long nextMsgTime = SystemClock.uptimeMillis() + timeOffset;
				
				PickAndExecuteLogMessage(new Double(random * 10).longValue());
				
				mHandler.postAtTime(this, nextMsgTime);
		}

		private void PickAndExecuteLogMessage(long type) {
			if(type <= 1)
				L.llv(LOG_TAG, "new verbose log msg");
			else if (type > 2 && type <= 4)
				L.lld(LOG_TAG, "new debug log msg");
			else if (type > 4 && type <= 6)
				L.lli(LOG_TAG, "new info msg");
			else if (type > 6 && type <= 8)
				L.llw(LOG_TAG, "new warning msg");
			else if (type > 8 && type <= 10)
				L.lle(LOG_TAG, "new error message");
		}
	};
	
	private OnClickListener mStartLogMsgListener = new OnClickListener() {

		public void onClick(View v) {
				mHandler.removeCallbacks(mGenerateLogMessagesTask);
				mHandler.postDelayed(mGenerateLogMessagesTask, 100);
		}
	};

	private OnClickListener mStopLogMsgListener = new OnClickListener() {

		public void onClick(View v) {
			mHandler.removeCallbacks(mGenerateLogMessagesTask);
		}
	};

	private LogListener logL = new LogListener() {

		public void onLogMessage(String tag, String msg) {
			String logMsg = tag + " " + msg;
			mHandler.sendMessage(mHandler.obtainMessage(MSG_LOG, logMsg));
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		L.setMode(L.MODE_ENGINEERING_LOGLISTENER);
		L.registerListener(logL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (logL != null)
			L.unregisterListener(logL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_SHOW_DEBUG_CONSOLE, 0, R.string.start_msg_gen);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()) {
		case MENU_SHOW_DEBUG_CONSOLE:
			break;
		default:
			break;
		}
		return true;
	}

	private void initView() {
		mConsoleWrapper = (ScrollView) findViewById(R.id.consoleWrapper);
		mConsole = (TextView) findViewById(R.id.textViewConsole);
		appendConsoleText(R.string.welcome);
		appendConsoleText(R.string.have_fun);
		appendConsoleText(R.string.options_howto);
		appendConsoleText(R.string.prompt);
		
		Button startGenMsgButton = (Button) findViewById(R.id.start_gen_msg_button);
		startGenMsgButton.setOnClickListener(mStartLogMsgListener);
		Button stopGenMsgButton = (Button) findViewById(R.id.stop_gen_msg_button);
		stopGenMsgButton.setOnClickListener(mStopLogMsgListener);
	}

	private void appendConsoleText(int resID) {
		appendConsoleText(getString(resID));
	}

	private void appendConsoleText(String text) {
		if (text.equals(getString(R.string.prompt)))
			text = "";

		mConsole.append("> " + text + "\n");
		mConsoleWrapper.scrollTo(0, mConsole.getHeight() + 10);
	}
}