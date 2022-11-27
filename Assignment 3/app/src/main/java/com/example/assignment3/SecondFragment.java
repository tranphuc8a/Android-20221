package com.example.assignment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.assignment3.databinding.FragmentSecondBinding;


public class SecondFragment extends Fragment implements
        AdapterView.OnItemSelectedListener {

    private FragmentSecondBinding binding;
    private int idItemSelected = -1;

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
    }

    public void displayToast(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onClickRadioButton(View view){
        RadioButton radioButton = (RadioButton) view;
        int id = radioButton.getId();
        if (idItemSelected == id){
            displayToast("You have already selected this item");
        } else {
            idItemSelected = id;
            switch (idItemSelected){
                case R.id.sameday:
                    displayToast(getString(R.string.same_day_messenger_service));
                    break;
                case R.id.nextday:
                    displayToast(getString(R.string.next_day_ground_delivery));
                    break;
                case R.id.pickup:
                    displayToast(getString(R.string.pick_up));
                    break;
                default:
                    displayToast("Failure");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        activity.getFloatingActionButton().setOnClickListener(view->{
            // do nothing;
        });
        activity.getFloatingActionButton().hide();

        binding.sameday.setOnClickListener(view -> onClickRadioButton(view));
        binding.nextday.setOnClickListener(view -> onClickRadioButton(view));
        binding.pickup.setOnClickListener(view -> onClickRadioButton(view));


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.labelSpinner.setOnItemSelectedListener(this);
        binding.labelSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}