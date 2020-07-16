package com.biker.api.Callbacks;

import android.view.View;

import com.biker.ui.MainActivity;

import java.util.concurrent.CompletableFuture;

public class CreateRouteButtonListener implements View.OnClickListener {

    private MainActivity mainActivity;
    private CompletableFuture locationFuture;

    public CreateRouteButtonListener(MainActivity mainActivity, CompletableFuture locationFuture){
        this.mainActivity = mainActivity;
        this.locationFuture = locationFuture;
    }

    @Override
    public void onClick(View v) {
        mainActivity.buildRoute(locationFuture);
    }
}
