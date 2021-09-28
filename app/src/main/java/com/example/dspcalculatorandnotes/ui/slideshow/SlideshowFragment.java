package com.example.dspcalculatorandnotes.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dspcalculatorandnotes.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class SlideshowFragment extends Fragment {


    //String url="https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCsFDZ6Gv1SeOaT7YBQsn3bQ&maxResults=3&key=AIzaSyCISoTEFc7oowlIaxoCx9FtDULpDFU3Sso";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_slideshow,container,false);

        return view;

    }
}