package com.example.agradeapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticsFragment newInstance(String param1, String param2) {
        StatisticsFragment fragment = new StatisticsFragment();
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
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 设置饼图的颜色
        ArrayList<Integer> piechart_colors = new ArrayList<>();
        piechart_colors.add(Color.rgb(254, 186, 197));
        piechart_colors.add(Color.rgb(190, 229, 228));
        piechart_colors.add(Color.rgb(227, 251, 251));
        piechart_colors.add(Color.rgb(186, 211, 215));
        piechart_colors.add(Color.rgb(254, 234, 207));

        // 设置每日专注分布饼图的 占比和对应项目
        ArrayList<PieEntry> dailyFocusingDistributionPieChartYValues = new ArrayList<>();
        dailyFocusingDistributionPieChartYValues.add(new PieEntry(34f, "COMP 3330"));
        dailyFocusingDistributionPieChartYValues.add(new PieEntry(64f, "COMP 3230"));
        dailyFocusingDistributionPieChartYValues.add(new PieEntry(24f, "COMP 3278"));
        dailyFocusingDistributionPieChartYValues.add(new PieEntry(94f, "FINA 1310"));
        dailyFocusingDistributionPieChartYValues.add(new PieEntry(84f, "CCGL 9063"));

        // 设置每周专注分布饼图的 占比和对应项目
        ArrayList<PieEntry> weeklyFocusingDistributionPieChartYValues = new ArrayList<>();
        weeklyFocusingDistributionPieChartYValues.add(new PieEntry(134f, "COMP 3330"));
        weeklyFocusingDistributionPieChartYValues.add(new PieEntry(364f, "COMP 3230"));
        weeklyFocusingDistributionPieChartYValues.add(new PieEntry(224f, "COMP 3278"));
        weeklyFocusingDistributionPieChartYValues.add(new PieEntry(594f, "FINA 1310"));
        weeklyFocusingDistributionPieChartYValues.add(new PieEntry(384f, "CCGL 9063"));


        /* 此处设置顶部数据 */
        TextView totalFocusingTimeFrequency = view.findViewById(R.id.totalFocusingTimeFrequency);
        TextView totalFocusingTimeHours = view.findViewById(R.id.totalFocusingTimeHours);
        TextView totalFocusingTimeDailyAvgHours = view.findViewById(R.id.totalFocusingTimeDailyAvgHours);

        // 设置总资料面板
        totalFocusingTimeFrequency.setText("2047");
        totalFocusingTimeHours.setText("1437");
        totalFocusingTimeDailyAvgHours.setText("7.51");

        /* 此处设置每日学习分布饼图 */
        PieChart dailyFocusingDistributionPieChart = view.findViewById(R.id.dailyFocusingDistributionPieChart);
        dailyFocusingDistributionPieChart.setUsePercentValues(false);
        dailyFocusingDistributionPieChart.getDescription().setEnabled(false);
        dailyFocusingDistributionPieChart.setEntryLabelTextSize(12f);

        // 设置饼图底部换行
        Legend l = dailyFocusingDistributionPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(4f);
        l.setYEntrySpace(0f);
        l.setWordWrapEnabled(true);

        dailyFocusingDistributionPieChart.setExtraOffsets(5,10,5,5);
        Paint dailyFocusingDistributionPieChartPaint = dailyFocusingDistributionPieChart.getPaint(Chart.PAINT_INFO);
        dailyFocusingDistributionPieChartPaint.setColor(getResources().getColor(R.color.black));
        dailyFocusingDistributionPieChart.setEntryLabelColor(Color.BLACK);
        dailyFocusingDistributionPieChart.setDrawHoleEnabled(false);

        dailyFocusingDistributionPieChart.setDragDecelerationFrictionCoef(0.95f);
        dailyFocusingDistributionPieChart.setTransparentCircleRadius(100f);

        PieDataSet dailyFocusingDistributionPieChartDataSet = new PieDataSet(dailyFocusingDistributionPieChartYValues, "");
        dailyFocusingDistributionPieChartDataSet.setSliceSpace(2f);
        dailyFocusingDistributionPieChartDataSet.setSelectionShift(5f);
        dailyFocusingDistributionPieChartDataSet.setValueTextSize(12f);
        dailyFocusingDistributionPieChartDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dailyFocusingDistributionPieChartDataSet.setColors(piechart_colors);

        PieData dailyFocusingDistributionPieChartPieData = new PieData(dailyFocusingDistributionPieChartDataSet);
        dailyFocusingDistributionPieChartPieData.setValueTextSize(12f);
        dailyFocusingDistributionPieChartPieData.setValueFormatter(new PercentFormatter());
        dailyFocusingDistributionPieChart.setData(dailyFocusingDistributionPieChartPieData);

        /* 此处设置每周学习分布饼图 */
        PieChart weeklyFocusingDistributionPieChart = view.findViewById(R.id.weeklyFocusingDistributionPieChart);
        weeklyFocusingDistributionPieChart.setUsePercentValues(false);
        weeklyFocusingDistributionPieChart.getDescription().setEnabled(false);
        weeklyFocusingDistributionPieChart.setEntryLabelTextSize(12f);

        // 设置饼图底部换行
        l = weeklyFocusingDistributionPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(4f);
        l.setYEntrySpace(0f);
        l.setWordWrapEnabled(true);

        weeklyFocusingDistributionPieChart.setExtraOffsets(5,10,5,5);
        Paint weeklyFocusingDistributionPieChartPaint = weeklyFocusingDistributionPieChart.getPaint(Chart.PAINT_INFO);
        weeklyFocusingDistributionPieChartPaint.setColor(getResources().getColor(R.color.black));
        weeklyFocusingDistributionPieChart.setEntryLabelColor(Color.BLACK);
        weeklyFocusingDistributionPieChart.setDrawHoleEnabled(false);

        weeklyFocusingDistributionPieChart.setDragDecelerationFrictionCoef(0.95f);
        weeklyFocusingDistributionPieChart.setTransparentCircleRadius(100f);

        PieDataSet weeklyFocusingDistributionPieChartDataSet = new PieDataSet(weeklyFocusingDistributionPieChartYValues, "");
        weeklyFocusingDistributionPieChartDataSet.setSliceSpace(3f);
        weeklyFocusingDistributionPieChartDataSet.setSelectionShift(5f);
        weeklyFocusingDistributionPieChartDataSet.setValueTextSize(12f);
        weeklyFocusingDistributionPieChartDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        weeklyFocusingDistributionPieChartDataSet.setColors(piechart_colors);

        PieData weeklyFocusingDistributionPieChartPieData = new PieData(weeklyFocusingDistributionPieChartDataSet);
        weeklyFocusingDistributionPieChartPieData.setValueTextSize(12f);
        weeklyFocusingDistributionPieChartPieData.setValueFormatter(new PercentFormatter());
        weeklyFocusingDistributionPieChart.setData(weeklyFocusingDistributionPieChartPieData);


        /* 此处设置每月学习趋势折线图 */
        LineChart monthlyFocusingFocusingLineChart = view.findViewById(R.id.monthlyFocusingTrendLineChart);
        monthlyFocusingFocusingLineChart.setTouchEnabled(true);
        monthlyFocusingFocusingLineChart.setPinchZoom(true);
        monthlyFocusingFocusingLineChart.getLegend().setEnabled(false);
        monthlyFocusingFocusingLineChart.getDescription().setEnabled(false);

        Paint monthlyFocusingFocusingLineChartPaint = monthlyFocusingFocusingLineChart.getPaint(Chart.PAINT_INFO);
        monthlyFocusingFocusingLineChartPaint.setColor(getResources().getColor(R.color.black));

        ArrayList<Object> MonthdataList = new ArrayList<>();
        List<Entry> monthentries = new ArrayList<>();
        //数据库中的datalist导入
        String month = "11-";
        monthentries.add(new Entry(0, 3));
        monthentries.add(new Entry(1, 6));
        monthentries.add(new Entry(2, 5));
        monthentries.add(new Entry(3, 9));
        monthentries.add(new Entry(4, 12));
        monthentries.add(new Entry(5, 10));
        monthentries.add(new Entry(6, 3));

        LineDataSet lineDataSet = new LineDataSet(monthentries, "");
        monthlyFocusingFocusingLineChart.setDrawGridBackground(false);
        monthlyFocusingFocusingLineChart.setDrawBorders(true);
        monthlyFocusingFocusingLineChart.setDragEnabled(false);
        monthlyFocusingFocusingLineChart.setTouchEnabled(true);
        monthlyFocusingFocusingLineChart.animateY(2500);
        monthlyFocusingFocusingLineChart.animateX(1500);
        XAxis xAxis = monthlyFocusingFocusingLineChart.getXAxis();
        YAxis leftYAxis = monthlyFocusingFocusingLineChart.getAxisLeft();
        YAxis rightYaxis = monthlyFocusingFocusingLineChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);

        leftYAxis.setLabelCount(8);
        Legend legend = monthlyFocusingFocusingLineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        lineDataSet.setColor(R.color.all);
        lineDataSet.setCircleColor(R.color.all);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(lineDataSet);
        monthlyFocusingFocusingLineChart.setData(lineData);
        monthlyFocusingFocusingLineChart.setBackgroundColor(Color.WHITE);
        monthlyFocusingFocusingLineChart.setDrawBorders(false);
        xAxis.setDrawGridLines(false);
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        leftYAxis.setDrawGridLines(false);
        rightYaxis.setEnabled(false);
        xAxis.setLabelCount(6,false);

        ArrayList<String> xvalue = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            xvalue.add(month + i);
        }
        xAxis.setValueFormatter(new ValueFormatter() {
            public String getAxisLabel(float value, AxisBase axis) {
                return xvalue.get((int) value);
            }
        });
        leftYAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return (int) value + "h";
            }
        });

        Drawable drawable = getResources().getDrawable(R.drawable.faded_green);
        setChartFillDrawable(drawable, monthlyFocusingFocusingLineChart);


        // 此处设置年度学习趋势
        LineChart yearlyFocusingFocusingLineChart = view.findViewById(R.id.annuallyFocusingTrendLineChart);
        yearlyFocusingFocusingLineChart.setTouchEnabled(true);
        yearlyFocusingFocusingLineChart.setPinchZoom(true);
        yearlyFocusingFocusingLineChart.getLegend().setEnabled(false);
        yearlyFocusingFocusingLineChart.getDescription().setEnabled(false);


        Paint annuallyFocusingTrendLineChartPaint = yearlyFocusingFocusingLineChart.getPaint(Chart.PAINT_INFO);
        annuallyFocusingTrendLineChartPaint.setColor(getResources().getColor(R.color.black));
        ArrayList<Object> YeardataList = new ArrayList<>();
        List<Entry> yearentries = new ArrayList<>();
        //数据库中的datalist导入
        String year = "2022-";
        yearentries.add(new Entry(0, 15));
        yearentries.add(new Entry(1, 236));
        yearentries.add(new Entry(2, 200));
        yearentries.add(new Entry(3, 148));
        yearentries.add(new Entry(4, 150));
        yearentries.add(new Entry(5, 150));
        yearentries.add(new Entry(6, 50));
        yearentries.add(new Entry(7, 30));
        yearentries.add(new Entry(8, 15));
        yearentries.add(new Entry(9, 270));
        yearentries.add(new Entry(10, 300));
        yearentries.add(new Entry(11, 230));

        LineDataSet yearlineDataSet = new LineDataSet(yearentries, "Annually Focusing Trend");
        yearlyFocusingFocusingLineChart.setDrawGridBackground(false);
        yearlyFocusingFocusingLineChart.setDrawBorders(true);
        yearlyFocusingFocusingLineChart.setDragEnabled(false);
        yearlyFocusingFocusingLineChart.setTouchEnabled(true);
        yearlyFocusingFocusingLineChart.animateY(2500);
        yearlyFocusingFocusingLineChart.animateX(1500);
        XAxis yearxAxis = yearlyFocusingFocusingLineChart.getXAxis();
        YAxis yearleftYAxis = yearlyFocusingFocusingLineChart.getAxisLeft();
        YAxis yearrightYaxis = yearlyFocusingFocusingLineChart.getAxisRight();
        yearxAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yearxAxis.setAxisMinimum(0f);
        yearxAxis.setGranularity(1f);
        yearleftYAxis.setAxisMinimum(0f);
        yearrightYaxis.setAxisMinimum(0f);

        yearleftYAxis.setLabelCount(8);
        Legend ylegend = yearlyFocusingFocusingLineChart.getLegend();
        ylegend.setForm(Legend.LegendForm.LINE);
        ylegend.setTextSize(12f);
        ylegend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        ylegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        ylegend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        ylegend.setDrawInside(false);

        yearlineDataSet.setColor(R.color.all);
        yearlineDataSet.setCircleColor(R.color.all);
        yearlineDataSet.setLineWidth(1f);
        yearlineDataSet.setCircleRadius(3f);
        yearlineDataSet.setDrawCircleHole(false);
        yearlineDataSet.setValueTextSize(10f);
        yearlineDataSet.setDrawFilled(true);
        yearlineDataSet.setFormLineWidth(1f);
        yearlineDataSet.setFormSize(15.f);
        yearlineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData yearlineData = new LineData(yearlineDataSet);
        yearlyFocusingFocusingLineChart.setData(yearlineData);
        yearlyFocusingFocusingLineChart.setBackgroundColor(Color.WHITE);
        yearlyFocusingFocusingLineChart.setDrawBorders(false);
        yearxAxis.setDrawGridLines(false);
        yearrightYaxis.setDrawGridLines(false);
        yearleftYAxis.setDrawGridLines(false);
        yearxAxis.setDrawAxisLine(false);
        yearleftYAxis.setDrawGridLines(false);
        yearrightYaxis.setEnabled(false);

        ArrayList<String> yearxvalue = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            yearxvalue.add(year + i);
        }
        yearxAxis.setValueFormatter(new ValueFormatter() {
            public String getAxisLabel(float value, AxisBase axis) {
                return yearxvalue.get((int) value);
            }
        });
        yearleftYAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return (int) value + "h";
            }
        });

        Drawable yeardrawable = getResources().getDrawable(R.drawable.faded_green);
        setChartFillDrawable(yeardrawable, yearlyFocusingFocusingLineChart);

    }

    public void setChartFillDrawable(Drawable drawable, LineChart lineChart) {
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(drawable);
            lineChart.invalidate();
        }
    }
}