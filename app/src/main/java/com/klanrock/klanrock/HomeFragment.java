package com.klanrock.klanrock;

/**
 * Created by fendrik on 16/05/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class HomeFragment extends Fragment {
    private SliderLayout sliderLayout;
    private TextView desc;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //slider
        sliderLayout = (SliderLayout) v.findViewById(R.id.slider);
        desc = (TextView) v.findViewById(R.id.des);
        slider();
        return v;
    }

    public void slider(){
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Foto Group",R.drawable.slide1);
        file_maps.put("Foto Wisuda",R.drawable.slide2);
        file_maps.put("Foto Personal",R.drawable.slide3);
        file_maps.put("Foto Groupp", R.drawable.slide4);
        for(String name : file_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(this.getActivity());
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Top);
        sliderLayout.setCustomAnimation(new DescriptionAnimation() );
        sliderLayout.setDuration(8000);
    }


}
