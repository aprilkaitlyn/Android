package com.example.fragmentdialogdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

//this class creates our toasts for the menu's buttons
public class MyDialogFragment extends DialogFragment { //added extends here

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {  //generated override
        //building the dialog & tying it to the activity
        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        //setting "the title" for our dialog
        theDialog.setTitle("sample dialog");

        //setting the text for our dialog
        theDialog.setMessage("hello MSU ITC student I am a dialog fragment!");

        //set text for positive button's toast
        theDialog.setPositiveButton("okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //auto
                //get activity, text, how long bubble stays on screen for, make it show up
                Toast.makeText(getActivity(), "clicked okay", Toast.LENGTH_SHORT).show();
            }
        });

        //set text for negative button's toast
        theDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //auto
                Toast.makeText(getActivity(), "clicked cancel", Toast.LENGTH_SHORT).show();
            }
        });

        return theDialog.create(); //need to add this part
    }
}
