package com.example.agradeapp;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParadomoFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ParadomoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParadomoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParadomoFragment newInstance(String param1, String param2) {
        ParadomoFragment fragment = new ParadomoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ParadomoFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_paradomo, container, false);
    }

    CountDownTimer countDownTimer = null;
    int minutes_in_total;
    long millis_left;
    long end_time;
    String paradomo_task_name = "";
    boolean timer_is_running;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        minutes_in_total = prefs.getInt("minutes_in_total", 30);
        millis_left =  prefs.getLong("millis_left", 30 * 60 * 1000);
        timer_is_running = prefs.getBoolean("timer_is_running", false);
        paradomo_task_name = prefs.getString("paradomo_task_name", "Work");

        if (timer_is_running) {
            end_time = prefs.getLong("end_time", System.currentTimeMillis() + 30 * 60 * 1000);
            millis_left = end_time - System.currentTimeMillis();
        }


        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        TextView textView = view.findViewById(R.id.progress_bar_text);
        Button button = view.findViewById(R.id.start_button);
        LinearLayout linearLayout = view.findViewById(R.id.progress_bar_center);
        TextView paradomo_task_name_view = view.findViewById(R.id.paradomo_task_name);

        paradomo_task_name_view.setText(paradomo_task_name);
        progressBar.setProgress(25);
        textView.setText("30:00");

        progressBar.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                float centerX = (float) progressBar.getWidth() / 2;
                float centerY = (float) progressBar.getHeight() / 2;

                int degree = (int) (Math.atan2(motionEvent.getY() - centerY, motionEvent.getX() -centerX) * 180 / Math.PI);

                if (degree <= 0) {
                    degree = 180 + 180 + degree;
                }
                degree += 90;

                if (degree > 360) {
                    degree -= 360;
                }

                minutes_in_total = (int) ((degree* 100 / 360 ) * 1.2);
                minutes_in_total = (int) (minutes_in_total - minutes_in_total % 5) + 5;
                if (minutes_in_total>120) {
                    minutes_in_total = 120;
                }
                millis_left = (long) minutes_in_total * 60 * 1000;
                int percentage = minutes_in_total * 100 / 120;

                textView.setText(String.valueOf(minutes_in_total)+":00");

                progressBar.setProgress(percentage);
                progressBar.invalidate();

                return true;
            }


        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer_is_running && countDownTimer != null){
                    countDownTimer.cancel();
                    timer_is_running=false;
                    minutes_in_total = 30;
                    millis_left = 30 * 60 * 1000;
                    end_time = System.currentTimeMillis() + millis_left;
                    button.setText("Start");
                    progressBar.setProgress(25);
                    textView.setText("30:00");
                    progressBar.setEnabled(true);
                    linearLayout.setEnabled(true);
                    Toast.makeText(getActivity(), "Pomodoro Cancelled", Toast.LENGTH_SHORT).show();
                }
                else {
                    timer_is_running=true;
                    button.setText("Stop");
                    progressBar.setEnabled(false);
                    linearLayout.setEnabled(false);
                    countDownTimer = new CountDownTimer(millis_left, 1000) {
                        public void onTick(long millisUntilFinished) {
                            textView.setText(String.format("%02d", ((int) millisUntilFinished / 60000)) + ":" + String.format("%02d", ((int) millisUntilFinished % 60000 / 1000)));
                            progressBar.setProgress((int)(millisUntilFinished*100/(120*60*1000)));
                            millis_left = millisUntilFinished;
                            end_time = System.currentTimeMillis() + millisUntilFinished;
                        }

                        public void onFinish() {
                            countDownTimer.cancel();
                            timer_is_running=false;
                            minutes_in_total = 30;
                            millis_left = 30 * 60 * 1000;
                            button.setText("Start");
                            progressBar.setProgress(25);
                            textView.setText("30:00");
                            progressBar.setEnabled(true);
                            linearLayout.setEnabled(true);
                            Toast.makeText(getActivity(), "Finished !", Toast.LENGTH_SHORT).show();


                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                NotificationChannel notificationChannel = new NotificationChannel("notifications", "notifications", NotificationManager.IMPORTANCE_HIGH);
                                notificationChannel.enableVibration(true);
                                notificationChannel.setVibrationPattern(new long[] {100, 1000, 200, 340});
                                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                                NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
                                notificationManager.createNotificationChannel(notificationChannel);
                            }

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "notifications")
                                    .setSmallIcon(R.drawable.app_icon)
                                    .setContentTitle("A Grade")
                                    .setContentText("Pomodoro has done! Nice job!")
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setVibrate(new long[] {100, 1000, 200, 340})
                                    .setAutoCancel(true);
                            NotificationManagerCompat display = NotificationManagerCompat.from(requireContext());
                            display.notify(1, builder.build());
                        }
                    };
                    countDownTimer.start();
                }
            }
        });

        ArrayList<String> categories = new ArrayList<>();
        categories.add("COMP3230");
        categories.add("COMP3297");
        categories.add("COMP3330");
        categories.add("CCGL9063");
        categories.add("FINA1310");
        categories.add("Violin");
        categories.add("Swim");
        categories.add("Martial Art");


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomDialogTheme);
                final View category_selection_view = getLayoutInflater().inflate(R.layout.dialogue_category_selection,null);
                builder.setView(category_selection_view);
                builder.setTitle("Select Category");
                builder.setCancelable(true);
                builder.setPositiveButton(android.R.string.ok, null);
                AlertDialog category_selection_dialogue = builder.create();
                FlexboxLayout flexboxLayout = category_selection_view.findViewById(R.id.task_selection);

                // set the add new task button at the bottom of the dialog
                category_selection_dialogue.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Button button = ((AlertDialog) category_selection_dialogue).getButton(AlertDialog.BUTTON_POSITIVE);
                        button.setText("ADD");
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomDialogTheme);
                                builder.setTitle("Input a new category                   ");

                                final View category_adding_view = getLayoutInflater().inflate(R.layout.dialogue_category_adding,null);
                                builder.setView(category_adding_view);
                                EditText input = category_adding_view.findViewById(R.id.dialogue_task_adding_input);

                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String category_name = input.getText().toString();
                                        categories.add(category_name);

                                        MaterialButton button = new MaterialButton(category_selection_dialogue.getContext());
                                        button.setCornerRadius(30);
                                        button.setText(category_name);
                                        button.setBackgroundColor(button.getContext().getResources().getColor(R.color.all));
                                        button.setTextColor(button.getContext().getResources().getColor(R.color.white));

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(10,10,10,0);
                                        button.setLayoutParams(params);

                                        // Select the item
                                        button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                paradomo_task_name = button.getText().toString();
                                                ((TextView) getView().findViewById(R.id.paradomo_task_name)).setText(paradomo_task_name);
                                                category_selection_dialogue.cancel();
                                            }
                                        });

                                        // Delete the item
                                        button.setOnLongClickListener(new View.OnLongClickListener() {
                                            @Override
                                            public boolean onLongClick(View view) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(category_selection_view.getContext(), R.style.CustomDialogTheme);
                                                builder.setTitle("Warning");
                                                builder.setMessage("Are you sure you want to delete this item?");
                                                builder.setCancelable(true);
                                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        categories.remove(button.getText().toString());
                                                        ((ViewGroup) button.getParent()).removeView(button);
                                                    }
                                                });
                                                builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                    }
                                                });
                                                AlertDialog dialog = builder.create();
                                                dialog.show();

                                                return true;
                                            }
                                        });
                                        flexboxLayout.addView(button);


                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                });

                for(int i = 0; i < categories.size(); i++) {
                    MaterialButton button = new MaterialButton(category_selection_view.getContext());
                    button.setCornerRadius(30);
                    button.setText(categories.get(i));
                    button.setBackgroundColor(button.getContext().getResources().getColor(R.color.all));
                    button.setTextColor(button.getContext().getResources().getColor(R.color.white));

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(10,10,10,0);
                    button.setLayoutParams(params);

                    // Select the item
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            paradomo_task_name = button.getText().toString();
                            ((TextView) getView().findViewById(R.id.paradomo_task_name)).setText(paradomo_task_name);
                            category_selection_dialogue.cancel();
                        }
                    });

                    // Delete the item
                    button.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(category_selection_view.getContext(), R.style.CustomDialogTheme);
                            builder.setTitle("Warning");
                            builder.setMessage("Are you sure you want to delete this item?");
                            builder.setCancelable(true);
                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    categories.remove(button.getText().toString());
                                    ((ViewGroup) button.getParent()).removeView(button);
                                }
                            });
                            builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();

                            return true;
                        }
                    });
                    flexboxLayout.addView(button);
                }
                category_selection_dialogue.show();
            }
        });

        if (timer_is_running) {
            button.callOnClick();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        // store the data into SharedPreferences
        SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("minutes_in_total", minutes_in_total);
        editor.putLong("millis_left", millis_left);
        editor.putLong("end_time", end_time);
        editor.putBoolean("timer_is_running", timer_is_running);
        editor.putString("paradomo_task_name", paradomo_task_name);
        editor.apply();
    }
}