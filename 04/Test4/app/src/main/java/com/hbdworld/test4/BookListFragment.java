package com.hbdworld.test4;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hbdworld.test4.model.BookContent;

/**
 * Created by hbd on 16/8/16.
 */
public class BookListFragment extends ListFragment {

    private Callbacks mCallbacks;

    public interface Callbacks{

        public void onItemSelected(Integer id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 为该ListFragment设置Adapter
        ListAdapter listAdapter = new ArrayAdapter<BookContent.Book>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,BookContent.ITEMS);

        setListAdapter(listAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(this.getActivity() instanceof Callbacks)){
            throw new IllegalStateException(
                    "BookListFragment所在的Activity必须实现Callbacks接口!");
        }
        mCallbacks = (Callbacks)this.getActivity();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        mCallbacks.onItemSelected(BookContent.ITEMS.get(position).id);
    }

    public void setActivateOnItemClick(boolean activateOnItemClick)
    {
        getListView().setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
    }
}
