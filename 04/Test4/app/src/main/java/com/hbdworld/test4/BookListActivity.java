package com.hbdworld.test4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BookListActivity extends AppCompatActivity implements BookListFragment.Callbacks {


    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        if(this.findViewById(R.id.book_detail_container) != null){
            mTwoPane = true;
            ((BookListFragment)this.getFragmentManager().findFragmentById(R.id.book_list)).setActivateOnItemClick(true);
        }

    }

    @Override
    public void onItemSelected(Integer id) {

        if (mTwoPane) {
            // 创建Bundle，准备向Fragment传入参数
            Bundle arguments = new Bundle();
            arguments.putInt(BookDetailFragment.ITEM_ID, id);
            BookDetailFragment detailFragment = new BookDetailFragment();
            detailFragment.setArguments(arguments);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.book_detail_container, detailFragment)
                    .commit();

        } else {
            Intent intent = new Intent(this,BookDetailActivity.class);
            intent.putExtra(BookDetailFragment.ITEM_ID,id);
            startActivity(intent);
        }
    }
}
