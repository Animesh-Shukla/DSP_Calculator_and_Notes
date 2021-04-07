package com.example.dspcalculatorandnotes;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DFTransform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_f_transform);
    }

    public void ExtractDFT(View view) {
        EditText inputDFT = findViewById(R.id.InputDFT);
        String seriesDFT;
        seriesDFT = inputDFT.getText().toString();
        //======================================================================================================
        {
            // Replacing every comma with a space(" ")
            seriesDFT = seriesDFT.replace(',', ' ');

            // Remove extra spaces from the beginning
            // and the ending of the string
            seriesDFT = seriesDFT.trim();

            // Replace all the consecutive white
            // spaces with a single space
            seriesDFT = seriesDFT.replaceAll(" +", " ");
            seriesDFT = seriesDFT + ' ';
        }
        //Toast.makeText(getApplicationContext(), "vlaue is "+seriesDFT, Toast.LENGTH_LONG).show();
        //========================================================================================================
        // Function that return count of the given
        // character in the string
        /*int res = 0;
        for (int i=0;i<seriesDFT.length();i++)
            // checking character in string
            if (seriesDFT.charAt(i) == ' ')
                res++;
        //Toast.makeText(getApplicationContext(), "vlaue is "+res, Toast.LENGTH_LONG).show();*/

        //==========================================================================================================
        char m;
        String s = "";
        int k = 0;//stores index no. of array a[]
        double[] real = new double[40]; //stores the numbers of linear convolution
        double[] imag = new double[40];
        for (int i = 0; i < 40; i++) imag[i] = 0;
        for (int i = 0; i < seriesDFT.length(); i++) {
            m = seriesDFT.charAt(i);
            if (m != ' ') {
                s += m;
            } else if (m == ' ') {
                real[k++] = Double.parseDouble(s);
                s = "";
            }
        }
        //==========================================================================================================
        /*Coming Soon......for imaginary numbers as well.....*/
        /*char m;
        String s="";
        double[] real=new double[40];int k=0;
        double[] imag=new double[40];int l=0;
        for(int i=0;i<seriesDFT.length();i++)
        {
            m=seriesDFT.charAt(i);
            if(m==' '){continue;}
            if(m!='+' && m!='j' && m!='-')
            {
                s+=m;
                Toast.makeText(getApplicationContext(), "vlaue is "+s, Toast.LENGTH_LONG).show();
            }
            if(m=='+')
            {
                real[k++]=Double.parseDouble(s);
                s="";
            }
            else if(m=='j')
            {
                imag[l++]=Double.parseDouble(s);
                s="";
            }
        }*/
        //===========================================================================================================================
        //code taken from site https://www.nayuki.io/res/how-to-implement-the-discrete-fourier-transform/Dft.java
        /*
         * Discrete Fourier transform (Java)
         * by Project Nayuki, 2017. Public domain.
         * https://www.nayuki.io/page/how-to-implement-the-discrete-fourier-transform
         */
        /*
         * Computes the discrete Fourier transform (DFT) of the given complex vector.
         * All the array arguments must be non-null and have the same length.
         */
        double[] outreal = new double[40];
        double[] outimag = new double[40];
        for (int i = 0; i < k; i++) {  // For each output element
            double sumreal = 0;
            double sumimag = 0;
            for (int t = 0; t < k; t++) {  // For each input element
                double angle = 2 * Math.PI * t * i / k;
                sumreal += real[t] * Math.cos(angle) + imag[t] * Math.sin(angle);
                sumimag += -real[t] * Math.sin(angle) + imag[t] * Math.cos(angle);
            }
            outreal[i] = Math.round(sumreal * 1000) / (double) 1000;//sumreal;
            outimag[i] = Math.round(sumimag * 1000) / (double) 1000;//sumimag;
        }
        //============================================================================================================================
        /*displaying the o/p*/
        String output = "OUTPUT....." + System.lineSeparator();
        for (int i = 0; i < k; i++) {
            output += Double.toString(outreal[i]);
            output += "+";
            output += Double.toString(outimag[i]);
            output += "j";
            output += System.lineSeparator();
        }
        //============================================================================================================================
        TextView result = findViewById(R.id.ResultDFT);
        result.setText(output);
    }
    //==================================================================================================================
    /*Coming Soon.......imaginary numbers ass well......*/
    //for(int i=0;i<k;i++)Toast.makeText(getApplicationContext(), "vlaue is "+real[i], Toast.LENGTH_LONG).show();
    //code taken from site https://www.nayuki.io/res/how-to-implement-the-discrete-fourier-transform/Dft.java
    /*
     * Discrete Fourier transform (Java)
     * by Project Nayuki, 2017. Public domain.
     * https://www.nayuki.io/page/how-to-implement-the-discrete-fourier-transform
     */
    /*
     * Computes the discrete Fourier transform (DFT) of the given complex vector.
     * All the array arguments must be non-null and have the same length.
     */
        /*double[] outreal=new double[40];
        double[] outimag=new double[40];
        if(k!=l)
        {
            Toast.makeText(getApplicationContext(), "Input entered is in incorrect format!!", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Please see the instructions.", Toast.LENGTH_SHORT).show();
        }
        else {
            for (int i = 0; i < k; i++) {  // For each output element
                double sumreal = 0;
                double sumimag = 0;
                for (int t = 0; t < i; t++) {  // For each input element
                    double angle = 2 * Math.PI * t * i / k;
                    sumreal += real[t] * Math.cos(angle) + imag[t] * Math.sin(angle);
                    sumimag += -real[t] * Math.sin(angle) + imag[t] * Math.cos(angle);
                }
                outreal[i] = sumreal;
                outimag[i] = sumimag;
            }
            //============================================================================================================================
            /*displaying the o/p*/
          /*  String output = "";
            for (int i = 0; i < k; i++) {
                output+=Double.toString(outreal[i]);
                output+="+";
                output+=Double.toString(outimag[i]);
                output+="j";
                output+=",";
            }
            //============================================================================================================================
            TextView result=findViewById(R.id.ResultDFT);
            result.setText(output);
        }*/
    //}
}