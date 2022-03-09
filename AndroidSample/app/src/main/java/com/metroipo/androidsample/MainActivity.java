package com.metroipo.androidsample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.metroipo.sdk.MetroIpoSdk;

public class MainActivity extends AppCompatActivity {
    private AlertDialog mDialog;
    private EditText mCodeField;
    private Button mSubmitButton;
    private MetroIpoSdk metroSdk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCodeField = findViewById(R.id.codeField);
        mSubmitButton = findViewById(R.id.enter_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });
        try {
            metroSdk = new MetroIpoSdk.Builder().setDomain("METROIPO-SERVER").create();
        }catch (IllegalStateException e) {
            showAlert("Error", e.getLocalizedMessage());
        }
    }

    private void next() {
        try {
            String mCode = mCodeField.getText().toString();
            metroSdk.start(mCode, this);
            metroSdk.onStart(new MetroIpoSdk.Response() {
                @Override
                public void onSuccess() {
                    Log.i("MetroSDK", "Sdk started.");
                }

                @Override
                public void onFailure(String message){
                    showAlert("Error", message);
                }
            });
            metroSdk.onComplete(new MetroIpoSdk.Callback() {
                @Override
                public void execute() {
                    mCodeField.setText("");
                    showAlert("Congratulations", "You have successfully added your signature.");
                }
            });
            metroSdk.onCancel(new MetroIpoSdk.Callback() {
                @Override
                public void execute() {
                    Log.i("MetroSDK", "Metro IPO Sdk encountered an error.");
                }
            });
        } catch (Exception e) {
            showAlert("Error", e.getLocalizedMessage());
        }
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", null);
        mDialog = builder.create();
        mDialog.show();
    }
}