package comtelekpsi.github.hiloandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtGuess;
    private Button guessBtn;
    private TextView lblOutput;
    private int tries=6;
    private int myNum;
    private String output = "";


    public void checker(){

        //get user-entered #
        String theirNum = txtGuess.getText().toString();
        try{
            int guess = Integer.parseInt(theirNum);
            tries--;

            if(guess<1 || guess>100) {
                output = guess + " is a fish outta water! 1-100 only please. "+tries+" tries left";
                lblOutput.setText(output);
                txtGuess.requestFocus();
                txtGuess.selectAll();
            }
            else if (guess > myNum){
                output = guess + " was higher than my number. "+tries+" tries left";
                lblOutput.setText(output);
                txtGuess.requestFocus();
                txtGuess.selectAll();
            }
            else if (guess < myNum){
                output = guess + " was lower than my number. "+tries+" tries left";
                lblOutput.setText(output);
                txtGuess.requestFocus();
                txtGuess.selectAll();
            } else{
                if (tries==5){
                    output = "You...how did you even do that?";
                    lblOutput.setText(output);
                }
                else {
                    output = "Ya got it in " + tries + ". Play again if so inclined...";
                    lblOutput.setText(output);
                    tries=6;
                }
                newRand();
            }
        }
        catch (Exception ex){
            output = "Integers between 1-100 only please.  ...Please?";
            lblOutput.setText(output);
        }
        finally { //now highlight txtGuess field
            txtGuess.requestFocus();
            txtGuess.selectAll();
        }
        if (tries==0){
            output = "All outta tries buddy.  NEW GAME!";
            lblOutput.setText(output);
            tries=6;
            newRand();
        }
    }

    //new random
    private void newRand(){
        myNum = (int)(Math.random()*100+1);
        tries = 6;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGuess = (EditText) findViewById(R.id.txtGuess);
        guessBtn = (Button) findViewById(R.id.guessBtn);
        lblOutput = (TextView) findViewById(R.id.lblOutput);

        //kickoff
        newRand();

        //buttonclick
        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker();
            }
        });

        //input field listener
        txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                checker();
                return false;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
