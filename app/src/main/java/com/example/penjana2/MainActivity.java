package com.example.penjana2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    EditText txtName;
    EditText txtLoan;
    EditText txtDuration;
    TextView viewResult;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        txtName = (EditText)findViewById(R.id.editName);
        txtLoan = (EditText)findViewById(R.id.editTextLoan);
        txtDuration = (EditText)findViewById(R.id.editTextDuration);
        viewResult = (TextView)findViewById(R.id.textView2);
    }

    public void showResult(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Reset is successful", Toast.LENGTH_SHORT);
        toast.show();

        txtName.setText("");
        txtLoan.setText("");
        txtDuration.setText("");
        viewResult.setText("");
    }

    public void printResult(View view) {

        isAllFieldsChecked = CheckAllFields();

        if (isAllFieldsChecked) {
            double loan = Double.parseDouble(txtLoan.getText().toString());
            int duration =Integer.parseInt(txtDuration.getText().toString());
            double intValue = loan * 0.03;
            double yearly = (intValue + loan) / duration;

            String msg = "Yearly is " + yearly;
            viewResult.setText(msg);
        }
    }

    private boolean CheckAllFields() {
        if (txtLoan.length() == 0) {
            txtLoan.setError("This field is required");
            return false;
        }

        if (txtDuration.length() == 0) {
            txtDuration.setError("This field is required");
            return false;
        }

        // after all validation return true.
        return true;
    }
}