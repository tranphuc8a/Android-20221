package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.navigation.fragment.NavHostFragment;
import com.example.assignment3.databinding.FragmentFirstBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    public String mOrderMessage = null;
    public static final String EXTRA_MESSAGE =
            "com.example.assignment3.databinding.FragmentFirstBindind.extra.MESSAGE";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.donut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showDonutOrder(view);
            }
        });
        binding.iceCream.setOnClickListener(view1 -> showIceCreamOrder(view1));
        binding.froyo.setOnClickListener(view1 -> showFroyoOrder(view1));
        MainActivity activity = (MainActivity) getActivity();
        FloatingActionButton fab = activity.getFloatingActionButton();
        fab.setOnClickListener(view1->{
            Bundle data = new Bundle();
            data.putString("item", mOrderMessage);
            getParentFragmentManager().setFragmentResult(FirstFragment.EXTRA_MESSAGE, data);
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void displayToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showDonutOrder(View view) {
        mOrderMessage = getString(R.string.donut_order_message);
        displayToast(mOrderMessage);
    }

    public void showIceCreamOrder(View view) {
        mOrderMessage = getString(R.string.ice_cream_order_message);
        displayToast(mOrderMessage);
    }

    public void showFroyoOrder(View view) {
        mOrderMessage = getString(R.string.froyo_order_message);
        displayToast(mOrderMessage);
    }

}