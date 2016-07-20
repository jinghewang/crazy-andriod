package com.hbdworld.autocompletetextviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView auto;
    MultiAutoCompleteTextView mauto;
    String[] books = new String[]{
            "疯狂Java讲义",
            "疯狂Ajax讲义",
            "疯狂XML讲义",
            "疯狂Workflow讲义"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--
        auto = (AutoCompleteTextView)this.findViewById(R.id.auto);
        mauto = (MultiAutoCompleteTextView)this.findViewById(R.id.mauto);

        ArrayAdapter listAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,books);
        auto.setAdapter(listAdapter);
        mauto.setAdapter(listAdapter);
        mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
