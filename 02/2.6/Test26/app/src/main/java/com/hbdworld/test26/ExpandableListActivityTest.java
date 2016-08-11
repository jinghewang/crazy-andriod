package com.hbdworld.test26;

import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableListActivityTest extends ExpandableListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            int[] logos = {R.drawable.p, R.drawable.z, R.drawable.t};
            private String[] armType = {"神族兵种", "虫族兵种", "人族兵种"};
            private String[][] arms = {
                    {"狂战士", "龙骑士", "黑暗圣堂", "电兵"},
                    {"小狗", "刺蛇", "飞龙", "自爆飞机"},
                    {"机枪兵", "护士MM", "幽灵"}
            };

            @Override
            public int getGroupCount() {
                return armType.length;
            }

            @Override
            public int getChildrenCount(int i) {
                return arms[i].length;
            }

            @Override
            public Object getGroup(int i) {
                return armType[i];
            }

            @Override
            public Object getChild(int i, int j) {
                return arms[i][j];
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int j) {
                return j;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                LinearLayout linearLayout = new LinearLayout(ExpandableListActivityTest.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(ExpandableListActivityTest.this);
                logo.setImageResource(logos[i]);
                linearLayout.addView(logo);
                TextView textView = new TextView(ExpandableListActivityTest.this);
                textView.setText(this.getGroup(i).toString());
                textView.setTextSize(20);
                textView.setTextColor(Color.BLUE);
                linearLayout.addView(textView);
                return linearLayout;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                TextView textView = new TextView(ExpandableListActivityTest.this);
                textView.setText(this.getChild(i,i1).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }
        };
        this.setListAdapter(adapter);
    }
}
