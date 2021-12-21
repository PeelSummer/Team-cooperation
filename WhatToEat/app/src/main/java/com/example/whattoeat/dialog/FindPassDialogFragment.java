package com.example.whattoeat.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.whattoeat.R;
import com.example.whattoeat.activity.MainActivity;

public class FindPassDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_findpass, null));

        return builder.create();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btn_sendPassword = (Button) getActivity().findViewById(R.id.btn_sendPassword);
        EditText edt_sendDestination = (EditText) getActivity().findViewById(R.id.edt_sendDestination);

        btn_sendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_sendDestination.setText("");
                Toast.makeText(getActivity(),"密碼已寄至您的郵件、手機",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
