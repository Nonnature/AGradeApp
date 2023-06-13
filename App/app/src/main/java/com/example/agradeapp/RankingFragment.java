package com.example.agradeapp;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RankingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RankingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int number_of_ranks = 30;

        // initialize ranking information
        ArrayList<Map<String,Object>> ranking_information = new ArrayList<Map<String,Object>>();
        this.initializeRankingInformation(ranking_information, number_of_ranks);

        // views related to first runner up
        CircleImageView rank_first_runner_up_icon = view.findViewById(R.id.rank_first_runner_up_icon);
        TextView rank_first_runner_up_university = view.findViewById(R.id.rank_first_runner_up_university);
        TextView rank_first_runner_up_faculty = view.findViewById(R.id.rank_first_runner_up_faculty);
        TextView rank_first_runner_up_hours = view.findViewById(R.id.rank_first_runner_up_hours);

        // views related to second runner up
        CircleImageView rank_second_runner_up_icon = view.findViewById(R.id.rank_second_runner_up_icon);
        TextView rank_second_runner_up_university = view.findViewById(R.id.rank_second_runner_up_university);
        TextView rank_second_runner_up_faculty = view.findViewById(R.id.rank_second_runner_up_faculty);
        TextView rank_second_runner_up_hours = view.findViewById(R.id.rank_second_runner_up_hours);

        // views related to third runner up
        CircleImageView rank_third_runner_up_icon = view.findViewById(R.id.rank_third_runner_up_icon);
        TextView rank_third_runner_up_university = view.findViewById(R.id.rank_third_runner_up_university);
        TextView rank_third_runner_up_faculty = view.findViewById(R.id.rank_third_runner_up_faculty);
        TextView rank_third_runner_up_hours = view.findViewById(R.id.rank_third_runner_up_hours);

        // view containing cards showing 4-th to n-th runner up
        LinearLayout linearLayout = view.findViewById(R.id.fragment_ranking_card_container);

        // set up first runner up
        rank_first_runner_up_icon.setImageDrawable((Drawable) ranking_information.get(0).get("Icon"));
        rank_first_runner_up_university.setText((String) ranking_information.get(0).get("University"));
        rank_first_runner_up_faculty.setText((String) ranking_information.get(0).get("Faculty"));
        rank_first_runner_up_hours.setText((String) ranking_information.get(0).get("Hours"));

        // set up second runner up
        rank_second_runner_up_icon.setImageDrawable((Drawable) ranking_information.get(1).get("Icon"));
        rank_second_runner_up_university.setText((String) ranking_information.get(1).get("University"));
        rank_second_runner_up_faculty.setText((String) ranking_information.get(1).get("Faculty"));
        rank_second_runner_up_hours.setText((String) ranking_information.get(1).get("Hours"));

        // set up third runner up
        rank_third_runner_up_icon.setImageDrawable((Drawable) ranking_information.get(2).get("Icon"));
        rank_third_runner_up_university.setText((String) ranking_information.get(2).get("University"));
        rank_third_runner_up_faculty.setText((String) ranking_information.get(2).get("Faculty"));
        rank_third_runner_up_hours.setText((String) ranking_information.get(2).get("Hours"));

        // set up 4-th to n-th runner up
        for (int i = 3; i < number_of_ranks; i++) {
            // the view containing i-th runner up info
            View card = getLayoutInflater().inflate(R.layout.card_ranking, null);

            // views related to i-th runner up
            CircleImageView card_ranking_icon = card.findViewById(R.id.card_ranking_icon);
            TextView card_ranking_university = card.findViewById(R.id.card_ranking_university);
            TextView card_ranking_faculty =  card.findViewById(R.id.card_ranking_faculty);
            TextView card_ranking_hours = card.findViewById(R.id.card_ranking_hours);
            TextView card_ranking_position = card.findViewById(R.id.card_ranking_position);

            card_ranking_icon.setImageDrawable((Drawable) ranking_information.get(i).get("Icon"));
            card_ranking_university.setText((String) ranking_information.get(i).get("University"));
            card_ranking_faculty.setText((String) ranking_information.get(i).get("Faculty"));
            card_ranking_hours.setText((String) ranking_information.get(i).get("Hours"));
            card_ranking_position.setText(i+1+"");

            // put the card into the screen
            linearLayout.addView(card);
        }
    }

    // initialize data used by ranking page
    private void initializeRankingInformation(ArrayList<Map<String,Object>> ranking_information, int number_of_ranks) {
        // sample data here
        ArrayList<String> universities = new ArrayList<>();
        universities.add("HKU");
        universities.add("CU");
        universities.add("UST");
        universities.add("PolyU");
        universities.add("EDU");
        universities.add("BU");
        universities.add("CityU");
        universities.add("OpenU");
        universities.add("LingU");

        ArrayList<String> faculties = new ArrayList<>();
        faculties.add("Architecture");
        faculties.add("Arts");
        faculties.add("Business");
        faculties.add("Dentistry");
        faculties.add("Education");
        faculties.add("Engineering");
        faculties.add("Law");
        faculties.add("Medicine");
        faculties.add("Science");
        faculties.add("Social Sciences");

        // initialize all ranks data
        for (int i = 0; i < number_of_ranks; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("Icon", this.mapUniversityIconByName(universities.get(i%universities.size())));
            map.put("University",universities.get(i%universities.size()));
            map.put("Faculty", faculties.get(i%faculties.size()));
            map.put("Hours", (5000 - i * 10) + "");
            ranking_information.add(map);
        }
    }

    // map university icon drawable by university name
    private Drawable mapUniversityIconByName(String university_name) {
        switch (university_name){
            case "HKU":
                return getResources().getDrawable(R.drawable.university_logo_hku);
            case "CU":
                return getResources().getDrawable(R.drawable.university_logo_cu);
            case "UST":
                return getResources().getDrawable(R.drawable.university_logo_ust);
            case "PolyU":
                return getResources().getDrawable(R.drawable.university_logo_polyu);
            case "EDU":
                return getResources().getDrawable(R.drawable.university_logo_edu);
            case "BU":
                return getResources().getDrawable(R.drawable.university_logo_bu);
            case "CityU":
                return getResources().getDrawable(R.drawable.university_logo_cityu);
            case "OpenU":
                return getResources().getDrawable(R.drawable.university_logo_openu);
            case "LingU":
                return getResources().getDrawable(R.drawable.university_logo_lingu);
            default:
                return getResources().getDrawable(R.drawable.profile_pic);
        }
    }
}