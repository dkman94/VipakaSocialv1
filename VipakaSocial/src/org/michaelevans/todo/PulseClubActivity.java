package org.michaelevans.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class PulseClubActivity extends Activity{

	/*
	private EditText mTaskInput;
	private ListView mListView;
	private TaskAdapter mAdapter;
	*/
	
	private int touchCount = 0;
	private boolean isTouched = false;
	private CountDownTimer cdTimer;
	private long timeLength = 30000; //30sec
	private long countInterval = 1000; //counts down at 1 sec
	private long timeLeft;
	private boolean wInning = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pulse_club);

		Parse.initialize(this, "APP_ID", "CLIENT_KEY");
		ParseAnalytics.trackAppOpened(getIntent());
		ParseObject.registerSubclass(Task.class);

		ParseUser currentUser = ParseUser.getCurrentUser();
		if(currentUser == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		
		final TextView timer = (TextView) findViewById(R.id.countTimer);
		
		

		//countdowntimer is done in longs, and is in milliseconds	
		
		
		new CountDownTimer(timeLength, countInterval) {       
			   public void onTick(long millisUntilFinished) {          
			   timer.setText(""+millisUntilFinished /1000);
			   timeLeft = millisUntilFinished;
			}       

			public void onFinish() {  
			   timer.setText("Congratulations you made it!");  
			   Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			   // Vibrate for 500 milliseconds
			   v.vibrate(500);
			   wInning = true;
			   Intent intent2 = new Intent(PulseClubActivity.this, CongratsActivity.class);
			   startActivity(intent2);
			}   
		 }.start();  
	
		 
		
		/*
		mAdapter = new TaskAdapter(this, new ArrayList<Task>());

		mTaskInput = (EditText) findViewById(R.id.task_input);
		mListView = (ListView) findViewById(R.id.task_list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);

		updateData();
		*/
	}
	
	public void test(View view)
	 {
		 Intent intent2 = new Intent(this, CongratsActivity.class);
		 startActivity(intent2);
	 }

	

	public boolean onTouchEvent(MotionEvent event) {
		
		int eventaction = event.getAction();
		Toast t = Toast.makeText(this, "Stop Clicking", Toast.LENGTH_LONG);
		
		switch(eventaction)
		{
		case MotionEvent.ACTION_MOVE:
			t.show();
			touchCount++;
			isTouched = true;
			break;
		}
		
		if(touchCount >= 2)
		{
			Intent intent = new Intent(this, TodoActivity.class);
			startActivity(intent);
			touchCount = 0;
		}
		
		return false;		
	}
	

/*
	public void updateData(){
		ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		query.findInBackground(new FindCallback<Task>() {
			@Override
			public void done(List<Task> tasks, ParseException error) {
				if(tasks != null){
					mAdapter.clear();
					for (int i = 0; i < tasks.size(); i++) {
						mAdapter.add(tasks.get(i));
					}
				}
			}
		});
	}
	public void createTask(View v) {
		if (mTaskInput.getText().length() > 0){
			Task t = new Task();
			t.setACL(new ParseACL(ParseUser.getCurrentUser()));
			t.setUser(ParseUser.getCurrentUser());
			t.setDescription(mTaskInput.getText().toString());
			t.setCompleted(false);
			t.saveEventually();
			mAdapter.insert(t, 0);
			mTaskInput.setText("");
		}
	} 
*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.todo, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_logout: 
			ParseUser.logOut();
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
			return true; 
		} 
		return false; 
	}

/*
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Task task = mAdapter.getItem(position);
		TextView taskDescription = (TextView) view.findViewById(R.id.task_description);

		task.setCompleted(!task.isCompleted());

		if(task.isCompleted()){
			taskDescription.setPaintFlags(taskDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}else{
			taskDescription.setPaintFlags(taskDescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
		}

		task.saveEventually();
	}
*/

}
