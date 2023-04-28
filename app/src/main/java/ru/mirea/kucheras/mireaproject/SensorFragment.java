package ru.mirea.kucheras.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import ru.mirea.kucheras.mireaproject.databinding.FragmentSensorBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorFragment extends Fragment implements SensorEventListener {
    private FragmentSensorBinding binding;
    private SensorManager sensorManager;
    private Sensor pressureSensor;
    float pressure;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SensorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
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
        binding = FragmentSensorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float pressure = event.values[0];
            binding.textView4.setText("Атмосферное давление: " + pressure);
            if (pressure > 760){
                binding.textView5.setText("Атмосферное давление - повышеное");
            }
            else{
                binding.textView5.setText("Атмосферное давление - понижженное");
            }

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
