package com.bradleege.bottomsheet;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        // Make sure that when PeekHeight Is Used it's possible to dismiss BottomSheet completely.
        // If this isn't done it'll only go back to it's PeekHeight instead of completely off screen.
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.button_1: {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            }
            case R.id.button_2: {
                bottomSheetBehavior.setPeekHeight(300);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            }
            case R.id.button_3: {
                BottomSheetDialogFragment bottomSheetDialogFragment = new MyBottomSheetDialogFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                break;
            }
        }
    }
}
