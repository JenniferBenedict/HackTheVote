package victorine.hackthevote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener, EditText.OnEditorActionListener{
    //array of voter IDs
    String voterIdArray []= {"001", "002", "003", "004", "005","006","007","008","009","010","011","012","013","014","015","016","017","018",
            "019","020","021","022","023","024"};
    //array of admin IDs
    String adminIDArray []= {"101","102","103","104","105"};
    EditText idEntry;
    Button submitID;
    TextView idError;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sets up layout with widgets
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idEntry = (EditText)findViewById(R.id.editText2);
        submitID = (Button) findViewById(R.id.button);
        submitID.setOnClickListener(this);
        idError = (TextView) findViewById(R.id.textView3);
        idEntry.setOnEditorActionListener(this);






    }


    @Override
    public void onClick(View view) {
        /*this method to open the polls checks to see whether or not the entered ID was an admin or
        a voter id, if a voter id is entered, an error will appear prompting
        for an admin id to open the polls*/
        if(view.getId()==R.id.button) {
            id = idEntry.getText().toString();
            System.out.println("test");
            System.out.println(id);
            for (int i=0;i<adminIDArray.length;i++) {
                if (adminIDArray[i].equals(id)) {
                    idError.setVisibility(View.GONE);
                    Intent election = new Intent(this, VoterIdEntryActivity.class);
                    startActivity(election);
                } else if(voterIdArray[i].equals(id)){
                    idError.setVisibility(View.VISIBLE);
                    idError.setText("Admin ID must be entered to begin voting!");
                }
            }
        }




    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        //this function fetches the entered the ID from the edit text
        id = idEntry.getText().toString();
        return false;
    }
}
