package com.example.permissions;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.example.permissions.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestPermissionLauncher=registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                permission->{
                    if(permission){
                        snack("Permission granted");
                    }
                    else{
                        snack("Permission denied");
                    }
                }
        );

        binding.internetBtn.setOnClickListener(v -> {
            requestPermissionLauncher.launch(Manifest.permission.INTERNET);
        });
        binding.contactsBtn.setOnClickListener(v -> {
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        });
    }

    public void snack(String msg){
        Snackbar snackbar=Snackbar.make(MainActivity.this,
                binding.mainLayout, msg,
                BaseTransientBottomBar.LENGTH_LONG);
        snackbar.show();
    }
}