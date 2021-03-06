package com.wzx.lightmodule.sample;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.wzx.lightmodule.Module;
import com.wzx.lightmodule.ModuleManager;
import com.wzx.lightmodule.impl.VerticalScrollModuleGroup;
import com.wzx.lightmodule.sample.module.ItemModule;
import com.wzx.lightmodule.sample.module.TopModule;

public class MainActivity extends AppCompatActivity {

    private ModuleManager mModuleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildModuleManager();
    }

    private void buildModuleManager() {
        mModuleManager = new ModuleManager(this, (ViewGroup) findViewById(R.id.activity_main));
        mModuleManager.bindLifecycle(this);

        Module topModule = new TopModule(this);
        mModuleManager.addModule(topModule);

        Drawable divider = getResources().getDrawable(R.drawable.gray_horizontal_separator);
        VerticalScrollModuleGroup scrollModuleGroup = new VerticalScrollModuleGroup(this, divider);
        for (int i = 0; i < 20; i++) {
            scrollModuleGroup.addModule(new ItemModule(this, i + 1));
        }
        mModuleManager.addModule(scrollModuleGroup);
        mModuleManager.refresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mModuleManager.onActivityResult(requestCode, resultCode, data);
    }
}
