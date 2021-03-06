package com.example.myfirstapp.genreActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.Globals;
import com.example.myfirstapp.R;
import com.example.myfirstapp.main.Controllers.UserRequestBrowse;

import java.util.ArrayList;

public class GenresFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public GenresFragment() {
    }

    public static GenresFragment newInstance(String param1, String param2) {
        GenresFragment fragment = new GenresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("creating new genre view");
        View v = inflater.inflate(R.layout.fragment_genres, container, false);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.GenreListLayout);
        UserRequestBrowse genreController = new UserRequestBrowse();
        ArrayList<String> genres = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            genres = genreController.browseGenres(Globals.getUser_username());
        }

        System.out.println("user is" + Globals.getUser_username());
        for (int i = 0; i < genres.size(); i++) {
            TextView text = new TextView(getContext());
            text.setGravity(Gravity.CENTER);
            text.setTextColor(getResources().getColor(android.R.color.black));
            text.setPadding(100, 60, 0, 0);
            text.setTextSize(24);
            text.setGravity(Gravity.LEFT);
            String genreName = genres.get(i);
            text.setText(genreName);
            text.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = getContext();
                    Globals.setViewGenreName(genreName);
                    Intent intent = new Intent(context, GenreItemActivity.class);
                    startActivity(intent);
                }
            });

            layout.addView(text);
        }

        return v;
    }
}