package com.williamdahl.dailogs.app;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Will on 2/05/2014.
 */
public class Dialog extends DialogFragment {
    int mStyle;
    int mTheme;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static Dialog newInstance(int style, int theme) {
        Dialog f = new Dialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("style", style);
        args.putInt("theme", theme);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStyle = getArguments().getInt("style");
        mTheme = getArguments().getInt("theme");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch (mStyle) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch (mTheme) {
            case 1: theme = android.R.style.Theme_Holo; break;
            case 2: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 3: theme = android.R.style.Theme_Holo_Light; break;
            case 4: theme = android.R.style.Theme_Holo_Light_Panel; break;
        }
        setStyle(style, theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog, container, false);
        View tv = v.findViewById(R.id.text);
        ((TextView)tv).setText("Dialog using theme: " + mTheme + ", and using style: " + mStyle);

        // Watch for button clicks.
        Button button = (Button)v.findViewById(R.id.dialogButtonOkay);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // This would finish the activity, not the fragment
                // getActivity().finish();

                // Pop back a step on the stack of fragments
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStackImmediate();
            }
        });

        return v;
    }
}
