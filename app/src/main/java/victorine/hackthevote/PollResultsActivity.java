package victorine.hackthevote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PollResultsActivity extends AppCompatActivity {
    //lays out text views for the results of the election
    TextView President, VicePresident, YesorNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this code sets up the layout for this screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_results_layout);
        President = (TextView) findViewById(R.id.textView7);
        VicePresident = (TextView) findViewById(R.id.textView8);
        YesorNo = (TextView) findViewById(R.id.textView9);

        /*code below takes the election results from the bundle passed from the previous screen
        and stores them to variables*/
        Bundle resultsBundle = getIntent().getExtras();
        String Presidents[] = resultsBundle.getStringArray("Presidents");
        int PresidentsTotals[] = resultsBundle.getIntArray("Presidents Totals");

        String VicePresidents[] = resultsBundle.getStringArray("Vice Presidents");
        int VicePresidentsTotals[] = resultsBundle.getIntArray("Vice Presidents Totals");
        int yesTotals = resultsBundle.getInt("yes");
        int noTotals = resultsBundle.getInt("no");

        /*code below finds the highest index (winner) in the presidential candidates
        total array */
        int n = PresidentsTotals.length;
        int max = 0;
        int highestIndex = 0;
        for (int i = 0; i < n; i++) {
            if (max < PresidentsTotals[i]) {
                max = PresidentsTotals[i];
                highestIndex = i;
            }
        }
        String[] presidentwinners = new String[5];
        for (int j = 0; j < PresidentsTotals.length; j++) {
            if (PresidentsTotals[j] == PresidentsTotals[highestIndex]) {
                presidentwinners[j]=new String(Presidents[j]);
            }
        }
        ArrayList<String> presArray = new ArrayList<String>(Arrays.asList(presidentwinners));
        presArray.removeAll(Collections.singleton(null));
        String []presidentwinners2 =new String[presArray.size()];
        presArray.toArray(presidentwinners2);


       /*code below finds the highest index(es) (winner) in the vice presidential candidates
        total array */
        int m = VicePresidentsTotals.length;
        int vmax = 0;
        int vicehighestIndex =0;
        for (int i = 0; i < m; i++) {
            if (vmax < VicePresidentsTotals[i]) {
                vmax = VicePresidentsTotals[i];
                vicehighestIndex = i;
            }
        }
        String[] vicePresidentWinner = new String[5];
        for (int j = 0; j < VicePresidentsTotals.length; j++) {
            if (VicePresidentsTotals[j] == VicePresidentsTotals[vicehighestIndex]) {
                vicePresidentWinner[j]=new String(VicePresidents[j]);
            }
        }
        ArrayList<String> vpresArray = new ArrayList<String>(Arrays.asList(vicePresidentWinner));
        vpresArray.removeAll(Collections.singleton(null));
        String []vicePresidentWinner2 = new String[vpresArray.size()];
        vpresArray.toArray(vicePresidentWinner2);


        //code below checks to see which answer for the final question received more votes
        if(yesTotals>noTotals)

        {
            YesorNo.setText("Funding for education will be increased");
        }

        else if(yesTotals<noTotals)

        {
            YesorNo.setText("Funding for education will not be increased");
        }

        else

        {
            YesorNo.setText("Tied Race, Will have to revote to decide on educational funding!");
        }

        /*code below sets the textviews to the results of the presidential & vice presidential
        races*/
        President.setText(printWinners(presidentwinners2));
        VicePresident.setText(printWinners(vicePresidentWinner2));
    }


    //method prints the results (either tie or one winner)
    public String printWinners(String[] winners) {
        if (winners.length > 1) {
            return "Tied between: \n"+ arraytoS(winners);
        }
        else{
            return "The winner is: \n"+ arraytoS(winners);
        }

    }

    //method to remove null values from array if candidate received no votes
    public String arraytoS(String[] my){
        String m="";
        if (my.length > 1) {
            for (int i=0;i<my.length;i++){
                if(my[i] != null) {
                    m += my[i] + ", ";
                }
            }
        }
        else{
            for (int i=0;i<my.length;i++){
                if(my[i] != null) {
                    m += my[i] + " ";
                }
            }
        }

         return m;
    }
}



