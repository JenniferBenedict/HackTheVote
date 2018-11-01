package victorine.hackthevote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class VoterIdEntryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, EditText.OnEditorActionListener {
    //array of voter IDs
    String voterIdArray[] = {"001", "002", "003", "004", "005","006","007","008","009","010","011","012","013","014","015","016","017","018",
            "019","020","021","022","023","024"};
    //array of admin IDs
    String adminIDArray[] = {"101", "102", "103", "104", "105"};
    EditText voterIdEntry;
    Button submitID, castVote;
    TextView alreadyVotedError;
    String id;
    //code below connects widgets for the poll
    LinearLayout poll;
    RadioButton yes, no;
    CheckBox HillaryClinton, DonaldTrump,GeorgeWBush;
    //array of strings of presidential candidates
    String [] Presidents = {    "Select One", "Oprah Winfrey",
            "Joe Biden", "Donald Trump Jr.", "Michelle Obama", "Bill Clinton"};
    //array to track totals for each presidential candidate
    int [] PresidentsTotals = {0,0,0,0,0,0};
    //array of strings of vice presidential candidate
    String[] VicePresidents = {"Select One","Hilary Clinton", "Donald Trump ", "George W. Bush"};
    //array to track totals for each vice presidential candidate
    int [] VicePresidentsTotals= {0,0,0,0};
    //total of votes for the final question
    int yesTotals =0;
    int noTotals =0;
    ArrayList<String> alreadyVoted=new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //code below sets up the layout for this screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voter_id_entry_layout);
        voterIdEntry = (EditText) findViewById(R.id.editText22);
        submitID = (Button) findViewById(R.id.button2);
        submitID.setOnClickListener(this);
        voterIdEntry.setOnEditorActionListener(this);
        poll = (LinearLayout) findViewById(R.id.polls);
        yes=(RadioButton) findViewById(R.id.radioButton2) ;
        no=(RadioButton) findViewById(R.id.radioButton);
        castVote=(Button)findViewById(R.id.button3);
        castVote.setOnClickListener(this);
        HillaryClinton=(CheckBox)findViewById(R.id.checkBox3);
        HillaryClinton.setOnClickListener(this);
        DonaldTrump=(CheckBox)findViewById(R.id.checkBox2);
        DonaldTrump.setOnClickListener(this);
        GeorgeWBush=(CheckBox)findViewById(R.id.checkBox);
        GeorgeWBush.setOnClickListener(this);
        alreadyVotedError=(TextView)findViewById(R.id.textView13);

    }

    @Override
    public void onClick(View view) {
        alreadyVoted.add(" ");
        id = voterIdEntry.getText().toString();
        /*if statement below checks if an entered ID is in the voter array, and if so, checks if the
        voter has already voted. If voter has already voted, an error is shown, and if not, then the polls are
        made visible to the voter*/
        if (view.getId() == R.id.button2) {
            for (int i = 0; i < voterIdArray.length; i++) {
                if (voterIdArray[i].equals(id) ) {
                    if(!alreadyVoted.contains(voterIdArray[i])) {
                        alreadyVotedError.setVisibility(View.GONE);
                        System.out.println("id matches a voter");
                        alreadyVoted.add(id);
                        Spinner race1 = (Spinner) findViewById(R.id.spinner);
                        String[] race1choices =
                                {"Select One", "Oprah Winfrey",
                                        "Joe Biden", "Donald Trump Jr.", "Michelle Obama", "Bill Clinton"};
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, race1choices);
                        race1.setAdapter(adapter);
                        race1.setOnItemSelectedListener(this);
                        race1.setSelection(0);
                        HillaryClinton.setChecked(false);
                        DonaldTrump.setChecked(false);
                        GeorgeWBush.setChecked(false);
                        yes.setChecked(false);
                        no.setChecked(false);
                        poll.setVisibility(View.VISIBLE);
                    }
                    else{
                        alreadyVotedError.setVisibility(View.VISIBLE);
                        alreadyVotedError.setText("You may only vote once.");
                    }
                }
            }
            /*for loop below checks to see if the entered ID matches an admin ID, and if so
            passes the total results for the election to the results screen through a bundle, and
            brings the user to the results screen
             */
            for (int j = 0; j < adminIDArray.length; j++) {
                if (adminIDArray[j].equals(id)) {
                    System.out.println("id matches an admin");
                    Intent results=new Intent(this,PollResultsActivity.class);
                    Bundle resultBundle = new Bundle();
                    resultBundle.putStringArray("Presidents",Presidents);
                    resultBundle.putIntArray("Presidents Totals",PresidentsTotals);
                    resultBundle.putStringArray("Vice Presidents",VicePresidents);
                    resultBundle.putIntArray("Vice Presidents Totals",VicePresidentsTotals);
                    resultBundle.putInt("yes",yesTotals);
                    resultBundle.putInt("no",noTotals);
                    results.putExtras(resultBundle);
                    startActivity(results);
                }
                /*THIS IS THE HACK FOR THIS APP: IF AN ADMIN ID IS ENTERED, FOLLOWED BY A SPACE, THE
                RESULTS SCREEN WILL APPEAR AND THE ELECTION IS RIGGED TO ENSURE THAT OPRAH WINS THE FIRST RACE, GEORGE BUSH
                WINS THE SECOND RACE, AND THE VOTE FOR THE LAST QUESTION WILL BE YES
                 */
                for(int m=0;m<adminIDArray.length;m++){
                    if((adminIDArray[m]+" ").equals(id)){
                        int hackedyesTotals=5;
                        int hackednoTotals=0;
                        int hackedPresidentsTotals[] = {0,20,0,0,0,0};
                        int hackedVPresidentsTotals[] = {0,0,0,20};
                        Intent results=new Intent(this,PollResultsActivity.class);
                        Bundle resultBundle = new Bundle();
                        resultBundle.putStringArray("Presidents",Presidents);
                        resultBundle.putIntArray("Presidents Totals",hackedPresidentsTotals);
                        resultBundle.putStringArray("Vice Presidents",VicePresidents);
                        resultBundle.putIntArray("Vice Presidents Totals",hackedVPresidentsTotals);
                        resultBundle.putInt("yes",hackedyesTotals);
                        resultBundle.putInt("no",hackednoTotals);
                        results.putExtras(resultBundle);
                        startActivity(results);
                    }
                }
            }
        }
        /*if/else statements below cast the vote for a voter once they cast it. Array totals are updated for the
        first two races and the count for the last question is also updated
         */
        if (view.getId() == R.id.button3) {
            /*this if statement checks to ensure that voter is aware of the choice to pick 2
            candidates for teh second race if they have undervoted
             */
            if((GeorgeWBush.isChecked() && !HillaryClinton.isChecked() && !DonaldTrump.isChecked())
                    || (HillaryClinton.isChecked() && !GeorgeWBush.isChecked() && !DonaldTrump.isChecked())
                    || (DonaldTrump.isChecked() && !GeorgeWBush.isChecked() && !HillaryClinton.isChecked())){
                Toast.makeText(getApplicationContext(), " You have only selected one candidate for race 2, you may select up to two",
                        Toast.LENGTH_LONG).show();
            }
            else {
                poll.setVisibility(View.INVISIBLE);
                if (yes.isChecked()) {
                    yesTotals++;
                } else if (no.isChecked()) {
                    noTotals++;
                }
                System.out.println("Yes Totals:" + yesTotals);
                System.out.println("No Totals:" + noTotals);
            }

        }
        /*following if statements update the totals for the vice presidential race, based
        on which check boxes are checked and prevents over voting
         */
        if (view.getId() == R.id.checkBox3){
            VicePresidentsTotals[1]++;
            if(DonaldTrump.isChecked()){
                GeorgeWBush.setChecked(false);
            }
            if(GeorgeWBush.isChecked()){
                DonaldTrump.setChecked(false);
            }
        }
        if(view.getId() ==R.id.checkBox2){
            VicePresidentsTotals[2]++;
            if(HillaryClinton.isChecked()){
                GeorgeWBush.setChecked(false);
            }
            if(GeorgeWBush.isChecked()){
                HillaryClinton.setChecked(false);
            }
        }
        if(view.getId()== R.id.checkBox){
            VicePresidentsTotals[3]++;
            if(HillaryClinton.isChecked()){
                DonaldTrump.setChecked(false);
            }
            if(DonaldTrump.isChecked()){
                HillaryClinton.isChecked();
            }
        }
    }


    @Override
    //this method gets the entered ID from the textbox
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        id = voterIdEntry.getText().toString();
        return false;
    }

    @Override
    /*this method updates the presidential race totals array based on which candidate
    the voter chooses for the first race*/
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinners= (Spinner)adapterView;
        if(spinners.getId()==R.id.spinner){
            if (i==1){
                PresidentsTotals[i]+=1;
            }
            else if(i==2){
                PresidentsTotals[i]+=1;            }
            else if(i==3){
                PresidentsTotals[i]+=1;            }
            else if(i==4){
                PresidentsTotals[i]+=1;            }
            else if(i==5){
                PresidentsTotals[i]+=1;            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //this method returns the largest index of othe largest int in an array
    public int getIndexOfLargest( int[] array )
    {
        if ( array == null || array.length == 0 ) return -1; // null or empty

        int largest = 0;
        for ( int i = 1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest; // position of the first largest found
    }
}





