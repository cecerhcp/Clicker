package pun.projects.clicker;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.text.DecimalFormat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private Integer score = 0;
    private Double time = 10d;
    private boolean isPlaying = false;
    private TextView timeView = null;
    private TextView scoreView = null;
    private Handler handler = new Handler();
    private Runnable timeRunnable = new Runnable(){
        public void run() {
            DecimalFormat df = new DecimalFormat("0.0");
            time -= 0.1d;
            if (time > 0) {
                timeView.setText("Time: " + df.format(time));
                handler.postDelayed(timeRunnable, 100);
            }
            else
            {
                isPlaying = false;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreView = (TextView) findViewById(R.id.scoreView);
        timeView = (TextView) findViewById(R.id.timeView);



        final Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying)
                {
                    score += 1;
                    scoreView.setText("Score: " + score.toString());
                }
                else {
                    startButton.setText("CLICK!!");
                    handler.postDelayed(timeRunnable, 100);
                    score = 0;
                    isPlaying = true;
                }

            }
        });

        final Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                time = 10d;
                isPlaying = false;
                handler.removeCallbacks(timeRunnable);
                startButton.setText("START");
                timeView.setText("Time: 10");
                scoreView.setText("Score: 0");

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
