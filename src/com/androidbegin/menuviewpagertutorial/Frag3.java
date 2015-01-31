package com.androidbegin.menuviewpagertutorial;


import com.actionbarsherlock.app.SherlockFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class Frag3 extends SherlockFragment {
 
    public static Fragment newInstance(Context context) {
        Frag3 f = new Frag3();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_frag3, null);
        return root;
    }
 
}