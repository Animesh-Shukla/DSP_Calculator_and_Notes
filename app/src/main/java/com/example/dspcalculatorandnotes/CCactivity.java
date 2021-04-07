package com.example.dspcalculatorandnotes;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_cactivity);
    }

    public void ExtractC(View view) {
        EditText firstinputC = findViewById(R.id.FirstInputC);
        EditText secondinputC = findViewById(R.id.SecondinputC);
        String firstseriesC;
        String secondseriesC;
        firstseriesC = firstinputC.getText().toString();
        secondseriesC = secondinputC.getText().toString();
        //=====================================================================================================================
        {
            // Replacing every non-digit number
            // with a space(" ")
            //firstseriesC = firstseriesC.replaceAll("[^\\d]", " ");
            firstseriesC = firstseriesC.replace(",", " ");

            // Remove extra spaces from the beginning
            // and the ending of the string
            firstseriesC = firstseriesC.trim();

            // Replace all the consecutive white
            // spaces with a single space
            firstseriesC = firstseriesC.replaceAll(" +", " ");
            firstseriesC = firstseriesC + ' ';
        }
        //debug1.setText(firstseries);
        char m;
        String s = "";
        int k = 0;//stores index no. of array a[]
        double[] a = new double[20]; //stores the numbers of linear convolution
        for (int i = 0; i < firstseriesC.length(); i++) {
            m = firstseriesC.charAt(i);
            if (m != ' ') {
                //r=r*10+Character.getNumericValue(m);
                s += m;
            } else if (m == ' ') {
                a[k++] = Double.parseDouble(s);
                s = "";
                // Toast.makeText(getApplicationContext(), "vlaue is "+a[k-1], Toast.LENGTH_LONG).show();
                //r=0;
            }
        }

        //========================================================================================================================
        {
            // Replacing every non-digit number
            // with a space(" ")
            //secondseriesC = secondseriesC.replaceAll("[^\\d]", " ");
            secondseriesC = secondseriesC.replace(",", " ");

            // Remove extra spaces from the beginning
            // and the ending of the string
            secondseriesC = secondseriesC.trim();

            // Replace all the consecutive white
            // spaces with a single space
            secondseriesC = secondseriesC.replaceAll(" +", " ");
            secondseriesC = secondseriesC + ' ';
        }
        int l = 0;//stores index no. of array a[]
        double[] b = new double[20]; //stores the numbers of linear convolution
        s = "";
        for (int i = 0; i < secondseriesC.length(); i++) {
            m = secondseriesC.charAt(i);
            if (m != ' ') {
                //r=r*10+Character.getNumericValue(m);
                s += m;
            } else if (m == ' ') {
                b[l++] = Double.parseDouble(s);
                s = "";
            }
        }
        //====================================================================================================
        /*If length of both sequences are not equal*/
        // Finding the maximum size between the
        // two input sequence sizes
        int maxSize = k > l ? k : l;

        double[] row_vec = new double[40];
        double[] col_vec = new double[40];
        double[][] circular_shift_mat = new double[40][40];
        double[] y = new double[50];
        //======================================================================================================
        // Copying elements of a to row_vec and padding
        // zeros if size of a < maxSize
        for (int i = 0; i < maxSize; i++) {
            if (i >= k) {
                row_vec[i] = 0;
            } else {
                row_vec[i] = a[i];
            }
        }
        //======================================================================================================
        // Copying elements of b to col_vec and padding
        // zeros if size of b is less than maxSize
        for (int i = 0; i < maxSize; i++) {
            if (i >= l) {
                col_vec[i] = 0;
            } else {
                col_vec[i] = b[i];
            }
        }

        //==========================================================================================================
        // Generating 2D matrix of
        // circularly shifted elements
        int z = 0, d = 0;

        for (int i = 0; i < maxSize; i++) {
            int curIndex = z - d;
            for (int j = 0; j < maxSize; j++) {
                circular_shift_mat[j][i] = row_vec[curIndex % maxSize];
                curIndex++;
            }
            z = maxSize;
            d++;
        }
        //=================================================================================================================
        // Computing result by matrix
        // multiplication and printing results
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                y[i] += circular_shift_mat[i][j]
                        * col_vec[j];
            }
        }

        //===============================================================================================================
        //displaying the o/p
        String output = "OUTPUT....." + System.lineSeparator();
        for (int i = 0; i < maxSize; i++) {
            output += Double.toString(y[i]);
            output = output + System.lineSeparator();
        }
        //=============================================================================================================================
        TextView result = findViewById(R.id.ResultC);
        result.setText(output);

    }
}