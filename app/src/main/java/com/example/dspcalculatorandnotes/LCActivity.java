package com.example.dspcalculatorandnotes;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_c);
    }

    public void extract(View view) {
        EditText firstinput = findViewById(R.id.Firstinput);
        EditText secondinput = findViewById(R.id.SecondInput);
        String firstseries;
        String secondseries;
        firstseries = firstinput.getText().toString();
        secondseries = secondinput.getText().toString();

        //=====================================================================================================================
        {
            // Replacing every non-digit number
            // with a space(" ")
            firstseries = firstseries.replace(",", " ");

            // Remove extra spaces from the beginning
            // and the ending of the string
            firstseries = firstseries.trim();

            // Replace all the consecutive white
            // spaces with a single space
            firstseries = firstseries.replaceAll(" +", " ");
            firstseries = firstseries + ' ';
        }
        char m;
        String s = "";
        int k = 0;//stores index no. of array a[]
        double[] a = new double[20]; //stores the numbers of linear convolution
        for (int i = 0; i < firstseries.length(); i++) {
            m = firstseries.charAt(i);
            if (m != ' ')
            {
                s += m;
            } else if (m == ' ') {
                a[k++] = Double.parseDouble(s);
                s = "";
            }
        }

        //========================================================================================================================
        {
            // Replacing every non-digit number
            // with a space(" ")
            secondseries = secondseries.replace(",", " ");

            // Remove extra spaces from the beginning
            // and the ending of the string
            secondseries = secondseries.trim();

            // Replace all the consecutive white
            // spaces with a single space
            secondseries = secondseries.replaceAll(" +", " ");
            secondseries = secondseries + ' ';
        }
        int l = 0;//stores index no. of array a[]
        double[] b = new double[20]; //stores the numbers of linear convolution
        s = "";
        for (int i = 0; i < secondseries.length(); i++) {
            m = secondseries.charAt(i);
            if (m != ' ') {
                //r=r*10+Character.getNumericValue(m);
                s += m;
            } else if (m == ' ') {
                //b[l++]=r;
                b[l++] = Double.parseDouble(s);
                s = "";
            }
        }
        //=====================================================================================================================
        // padding of zeors
        for (int i = k; i <= k + l - 1; i++) a[i] = 0;
        for (int i = l; i <= k + l - 1; i++) b[i] = 0;
        //======================================================================================================================
        /* convolution operation */
        double[] y = new double[k + l - 1];
        for (int i = 0; i < k + l - 1; i++) {
            y[i] = 0;
            for (int j = 0; j <= i; j++) {
                y[i] = y[i] + (a[j] * b[i - j]);
            }
        }
        //===========================================================================================================================
        //displaying the o/p
        String output = "OUTPUT....." + System.lineSeparator();
        for (int i = 0; i < k + l - 1; i++) {
            output += Double.toString(y[i]);
            output = output + System.lineSeparator();
        }
        //=============================================================================================================================
        TextView result = findViewById(R.id.Result);
        result.setText(output);
    }
}