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
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class CongratsActivity extends Activity{

	/*
	private EditText mTaskInput;
	private ListView mListView;
	private TaskAdapter mAdapter;
	*/
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.congrats_activity);

		Parse.initialize(this, "APP_ID", "CLIENT_KEY");
		ParseAnalytics.trackAppOpened(getIntent());
		ParseObject.registerSubclass(Task.class);

		ParseUser currentUser = ParseUser.getCurrentUser();
		if(currentUser == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
			
		

		//countdowntimer is done in longs, and is in milliseconds	
		
		

		
		/*
		mAdapter = new TaskAdapter(this, new ArrayList<Task>());

		mTaskInput = (EditText) findViewById(R.id.task_input);
		mListView = (ListView) findViewById(R.id.task_list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);

		updateData();
		*/
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
