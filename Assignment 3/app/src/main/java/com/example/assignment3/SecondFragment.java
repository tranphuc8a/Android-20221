package com.example.assignment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.assignment3.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private Bundle savedInstanceState = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener(FirstFragment.EXTRA_MESSAGE, this,
            new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    if (result.containsKey("item") && result.getString("item") != null){
                        binding.orderTextview.setText(String.valueOf(result.getString("item")));
                    } else {
                        binding.orderTextview.setText("You did not choose any item");
                    }
                }
            });
        this.savedInstanceState = savedInstanceState;

    }

    public void displayToast(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT);
    }

    public void onClickRadioButton(View view){
//        RadioButton radioButton = (RadioButton) view;
//        if (radioButton.isChecked()){
//            displayToast("You have already choosen this item");
//        } else {
//            radioButton.setChecked(true);
//            switch (radioButton.getId()){
//                case R.id.sameday:
//                    displayToast(String.valueOf(R.string.same_day_messenger_service));
//                    break;
//                case R.id.nextday:
//                    displayToast(String.valueOf(R.string.next_day_ground_delivery));
//                    break;
//                case R.id.pickup:
//                    displayToast(String.valueOf(R.string.pick_up));
//                    break;
//                default:
//                    displayToast("Failure");
//            }
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        activity.getFloatingActionButton().setOnClickListener(view->{
            // do nothing;
        });
        binding.deliveryChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                onClickRadioButton(radioGroup.findViewById(i));
            }
        });
        binding.sameday.setOnClickListener(view -> onClickRadioButton(view));
        binding.nextday.setOnClickListener(view -> onClickRadioButton(view));
        binding.pickup.setOnClickListener(view -> onClickRadioButton(view));
        if (savedInstanceState != null && savedInstanceState.containsKey("radioChecked")) {
            int index = savedInstanceState.getInt("radioChecked");
            binding.sameday.setChecked(false);
            binding.nextday.setChecked(false);
            binding.pickup.setChecked(false);
            if (index == 1){
                binding.sameday.setChecked(true);
            } else if (index == 2){
                binding.nextday.setChecked(true);
            } else if (index == 3){
                binding.pickup.setChecked(true);
            } else {
                // do nothing
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int index = 1;
        if (binding.nextday.isChecked()) index = 2;
        else if (binding.pickup.isChecked()) index = 3;
        else {
            // do nothing
        }
        outState.putInt("radioChecked", index);
    }
}