package com.hbdworld.expandablelistviewtest;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ExpandableListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--
        list = (ExpandableListView)this.findViewById(R.id.list);


        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            int[] logos = new int[]
                    {
                            R.drawable.p,
                            R.drawable.z,
                            R.drawable.t
                    };
            private String[] armTypes = new String[]
                    { "神族兵种", "虫族兵种", "人族兵种"};
            private String[][] arms = new String[][]
                    {
                            { "狂战士", "龙骑士", "黑暗圣堂", "电兵" },
                            { "小狗", "刺蛇", "飞龙", "自爆飞机" },
                            { "机枪兵", "护士MM" , "幽灵" }
                    };

            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            @Override
            public int getChildrenCount(int i) {
                return arms[i].length;
            }

            @Override
            public Object getGroup(int i) {
                return armTypes[i];
            }

            @Override
            public Object getChild(int i, int i1) {
                return arms[i][i1];
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                LinearLayout ll = new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(MainActivity.this);
                logo.setImageResource(logos[i]);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setText(getGroup(i).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                TextView textView = getTextView();
                textView.setText(getChild(i, i1).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }

            private TextView getTextView()
            {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(MainActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }
        };


        list.setAdapter(adapter);
    }
}
