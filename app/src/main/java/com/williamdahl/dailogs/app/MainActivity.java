package com.williamdahl.dailogs.app;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private int mTheme;
    private int mStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerTheme = (Spinner) findViewById(R.id.spinnerTheme);
        Spinner spinnerStyle = (Spinner) findViewById(R.id.spinnerStyle);
        setUpSpinner(spinnerStyle, R.array.style_array);
        setUpSpinner(spinnerTheme, R.array.theme_array);

        final Button button = (Button) findViewById(R.id.ShowDialog);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(mStyle,mTheme);
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item
        //Toast.makeText(this, parent.getItemAtPosition(pos).toString() + " || " + Integer.toString(pos), Toast.LENGTH_LONG).show();

        if (parent.getId() == R.id.spinnerStyle){
            mStyle = pos + 1;
        }
        if (parent.getId() == R.id.spinnerTheme){
            mTheme = pos + 1;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        // Mandatory for interface, but not used in this scenario
    }

    void showDialog(int style, int theme) {
        //mStackLevel++;

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        android.app.Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = Dialog.newInstance(style,theme);
        newFragment.show(ft, "dialog");
    }



    void setUpSpinner(Spinner targetSpinner, int stringList){

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                stringList, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        targetSpinner.setOnItemSelectedListener(this);
        targetSpinner.setAdapter(adapter);

    }


}
