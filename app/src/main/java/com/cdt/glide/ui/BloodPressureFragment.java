package com.cdt.glide.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdt.glide.R;
import com.cdt.glide.net.Entity.WeatherJson;
import com.cdt.glide.net.base.BaseClient;
import com.cdt.glide.net.client.WeatherClient;
import com.cdt.glide.net.endpoint.WeatherEndpoint;
import com.cdt.glide.util.Logger;

import okhttp3.ResponseBody;
import rx.Observer;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BloodPressureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BloodPressureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BloodPressureFragment extends BaseFragment {
    private static final String TAG = "BloodPressureFragment";

    public BloodPressureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeartRateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BloodPressureFragment newInstance(String param1, String param2) {
        BloodPressureFragment fragment = new BloodPressureFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_blood_pressure, container, false);
        final TextView tv = ((TextView) contentView.findViewById(R.id.textView));

        new WeatherClient("http://www.weather.com.cn/").getWeather("101010100").subscribe(new Observer<WeatherJson>() {
            @Override
            public void onCompleted() {
                Logger.e(TAG, "get weather info completed");
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(TAG, "get weather info error");
                e.printStackTrace();
            }

            @Override
            public void onNext(WeatherJson weatherJson) {
                tv.setText(weatherJson.toString());
            }
        });

        return contentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public String getTitle() {
        return "血压";
    }
}
