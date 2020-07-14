package com.biker.api.Callbacks;

import com.google.android.gms.tasks.OnFailureListener;

import androidx.annotation.NonNull;

public class LocationFailureCallback implements OnFailureListener {
    @Override
    public void onFailure(@NonNull Exception e) {
        //TODO: Display error
        System.out.println("Failed to Retrieve Location");
        e.printStackTrace();
    }
}
