package com.example.agradeapp;

import static android.content.Context.POWER_SERVICE;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.PowerManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> universities = new ArrayList<>();
        universities.add("The University of Hong Kong");
        universities.add("The Chinese University of Hong Kong");
        universities.add("The Hong Kong University of Science and Technology");
        universities.add("City University of Hong Kong");
        universities.add("The Hong Kong Polytechnic University");
        universities.add("Hong Kong Baptist University");
        universities.add("The Education University of Hong Kong");
        universities.add("Lingnan University");
        universities.add("The Open University of Hong Kong");

        ArrayList<String> faculties = new ArrayList<>();
        faculties.add("Faculty of Architecture");
        faculties.add("Faculty of Arts");
        faculties.add("Faculty of Business and Economics");
        faculties.add("Faculty of Dentistry");
        faculties.add("Faculty of Education");
        faculties.add("Faculty of Engineering");
        faculties.add("Faculty of Law");
        faculties.add("Faculty of Medicine");
        faculties.add("Faculty of Science");
        faculties.add("Faculty of Social Sciences");

        // 以下是展示资料
        TextView setting_displayed_user_name = view.findViewById(R.id.setting_displayed_user_name);
        TextView setting_displayed_user_university = view.findViewById(R.id.setting_displayed_user_university);
        TextView setting_displayed_user_faculty = view.findViewById(R.id.setting_displayed_user_faculty);
        CircleImageView setting_displayed_user_icon = view.findViewById(R.id.setting_displayed_user_icon);

        // 以下是按键
        TextView setting_nick_name = view.findViewById(R.id.setting_nick_name);
        TextView setting_email = view.findViewById(R.id.setting_email);
        TextView setting_password = view.findViewById(R.id.setting_password);
        TextView setting_university = view.findViewById(R.id.setting_university);
        TextView setting_faculty = view.findViewById(R.id.setting_faculty);
        TextView setting_logout = view.findViewById(R.id.setting_Logout);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch setting_background_mode_switch = view.findViewById(R.id.setting_background_mode_switch);

        setting_nick_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomDialogTheme);
                builder.setTitle("Change Nick Name                   ");
                final View category_adding_view = getLayoutInflater().inflate(R.layout.dialogue_setting_input, null);
                builder.setView(category_adding_view);
                EditText input = category_adding_view.findViewById(R.id.dialogue_task_adding_input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 在这里添加给数据库发信息的函数
                                setting_displayed_user_name.setText(input.getText().toString());
                            }
                        }
                );
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.getWindow().setLayout(1000, 400);
                alert.show();
            }
        });

        setting_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomDialogTheme);
                builder.setTitle("Change Email                   ");
                final View category_adding_view = getLayoutInflater().inflate(R.layout.dialogue_setting_input, null);
                builder.setView(category_adding_view);
                EditText input = category_adding_view.findViewById(R.id.dialogue_task_adding_input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 在这里添加给数据库发信息的函数
                            }
                        }
                );
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.getWindow().setLayout(1000, 400);
                alert.show();
            }
        });

        setting_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomDialogTheme);
                builder.setTitle("Change Password                   ");
                final View category_adding_view = getLayoutInflater().inflate(R.layout.dialogue_setting_input, null);
                builder.setView(category_adding_view);
                EditText input = category_adding_view.findViewById(R.id.dialogue_task_adding_input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 在这里添加给数据库发信息的函数
                            }
                        }
                );
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.getWindow().setLayout(1000, 400);
                alert.show();

            }
        });

        setting_university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomDialogTheme);
                builder.setTitle("Select University                   ");
                final View dialog_setting_selection_view = getLayoutInflater().inflate(R.layout.dialogue_setting_selection, null);
                builder.setView(dialog_setting_selection_view);
                Spinner dialog_setting_selection_spinner = dialog_setting_selection_view.findViewById(R.id.dialog_setting_selection_spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, universities);
                dialog_setting_selection_spinner.setAdapter(adapter);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setting_displayed_user_university.setText(dialog_setting_selection_spinner.getSelectedItem().toString());
                            }
                        }
                );
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.getWindow().setLayout(1000, 400);
                alert.show();
            }
        });

        setting_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomDialogTheme);
                builder.setTitle("Select Faculty                   ");
                final View dialog_setting_selection_view = getLayoutInflater().inflate(R.layout.dialogue_setting_selection, null);
                builder.setView(dialog_setting_selection_view);
                Spinner dialog_setting_selection_spinner = dialog_setting_selection_view.findViewById(R.id.dialog_setting_selection_spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, faculties);
                dialog_setting_selection_spinner.setAdapter(adapter);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setting_displayed_user_faculty.setText(dialog_setting_selection_spinner.getSelectedItem().toString());
                            }
                        }
                );
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.getWindow().setLayout(1000, 400);
                alert.show();
            }
        });

        setting_background_mode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(view.getContext(), "Background Mode On", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        Intent intent = new Intent();
                        String packageName = getActivity().getPackageName();
                        PowerManager pm = (PowerManager) view.getContext().getSystemService(POWER_SERVICE);
                        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                            intent.setData(Uri.parse("package:" + packageName));
                            startActivity(intent);
                        }
                    }
                }
                else {
                    Toast.makeText(view.getContext(), "Background Mode Off", Toast.LENGTH_SHORT).show();
                }
            }
        });


        setting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting_displayed_user_name.setText("Visitor");
                setting_displayed_user_university.setText("");
                setting_displayed_user_faculty.setText("");
                setting_displayed_user_icon.setImageDrawable(setting_displayed_user_icon.getContext().getResources().getDrawable(R.drawable.profile_pic));
            }
        });
    }
}