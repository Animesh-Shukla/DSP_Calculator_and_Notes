package com.example.dspcalculatorandnotes;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IDFTransform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_d_f_transform);
    }

    public void ExtractIDFT(View view) {
        EditText inputIDFT = findViewById(R.id.InputIDFT);
        String seriesIDFT;
        seriesIDFT = inputIDFT.getText().toString();
        //======================================================================================================
        {
            // Replacing every comma with a space(" ")
            seriesIDFT = seriesIDFT.replace(';', ' ');

            // Remove extra spaces from the beginning
            // and the ending of the string
            seriesIDFT = seriesIDFT.trim();

            // Replace all the consecutive white
            // spaces with a single space
            seriesIDFT = seriesIDFT.replaceAll(" +", " ");
            seriesIDFT = seriesIDFT + ' ';
        }
        //==========================================================================================================
        char m;
        int z = 0;
        String s = "", real = "", imag = "";
        int k = 0;//stores index no. of array a[]
        double[] inreal = new double[40]; //stores the numbers of linear convolution
        double[] inimag = new double[40];
        for (int i = 0; i < seriesIDFT.length(); i++) {
            m = seriesIDFT.charAt(i);
            if (m != ' ') {
                s += m;
            } else if (m == ' ') {
                real = s.substring(1, s.indexOf(','));
                imag = s.substring(s.indexOf(',') + 1, s.length() - 1);
                if (real.indexOf('-') >= 0) {
                    inreal[k] = -1 * Double.parseDouble(real.substring(1, real.length() - 1));
                } else if (real.indexOf('+') >= 0) {
                    inreal[k] = Double.parseDouble(real.substring(1, real.length() - 1));
                } else inreal[k] = Double.parseDouble(real);
                ////////////////////////
                if (imag.indexOf('-') >= 0) {
                    inimag[k] = -1 * Double.parseDouble(imag.substring(1, imag.length() - 1));
                } else if (imag.indexOf('+') >= 0) {
                    inimag[k] = Double.parseDouble(imag.substring(1, imag.length() - 1));
                } else inimag[k] = Double.parseDouble(imag);
                ///////////////////////////////////////
                k++;
                s = "";
            }

        }
        //=================================================================================================================
        /*for (int i = 0; i < k; i++)
            Toast.makeText(getApplicationContext(), "vlaue is " + inimag[i], Toast.LENGTH_LONG).show();*/
        //===============================================================================================================
        /*code for IDFT, adapted from DFT code as it could not be found anywhere*/
        double[] outreal = new double[40];
        double[] outimag = new double[40];
        for (int i = 0; i < k; i++) {  // For each output element
            double sumreal = 0;
            double sumimag = 0;
            for (int t = 0; t < k; t++) {  // For each input element
                double angle = 2 * Math.PI * t * i / k;
                sumreal += (inreal[t] * Math.cos(angle) - inimag[t] * Math.sin(angle)) / k;
                sumimag += (inreal[t] * Math.sin(angle) + inimag[t] * Math.cos(angle)) / k;
            }
            outreal[i] = Math.round(sumreal * 1000) / (double) 1000;//sumreal;
            outimag[i] = Math.round(sumimag * 1000) / (double) 1000;//sumimag;
        }
        //========================================================================================================================
        /*displaying the o/p*/
        String output = "OUTPUT....." + System.lineSeparator();
        for (int i = 0; i < k; i++) {
            output += "(";
            output += Double.toString(outreal[i]);
            output += ") + (";
            output += Double.toString(outimag[i]);
            output += "j)";
            output += System.lineSeparator();
        }
        //============================================================================================================================
        TextView result = findViewById(R.id.ResultIDFT);
        result.setText(output);

    }
}