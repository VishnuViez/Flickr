package com.example.flickr.view;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.flickr.R;
import com.example.flickr.data.ApiResponse;
import com.example.flickr.data.URIBuilder;
import com.example.flickr.databinding.ActivityMainBinding;
import com.example.flickr.model.Photo;
import com.example.flickr.view.PhotoAdapter;
import com.example.flickr.viewmodel.MainViewModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.bind();
        this.setAdapter(this.binding.gridview);
    }

    private void bind() {
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModel mainViewModel = new MainViewModel(this, new ApiResponse(), new ArrayList<Photo>(), new URIBuilder());

        this.setupObserver(mainViewModel);
        this.binding.setMainViewModel(mainViewModel);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void setAdapter(GridView gridView) {
        gridView.setAdapter(new PhotoAdapter(this, new ArrayList<Photo>()));
    }

    MainViewModel mainViewModel;
    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof MainViewModel) {
            PhotoAdapter adapter = (PhotoAdapter) binding.gridview.getAdapter();
            mainViewModel = (MainViewModel) observable;
            adapter.setPhotoList(mainViewModel.getPhotoList());
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (getCurrentFocus() != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.dispatchTouchEvent(ev);
    }
}
