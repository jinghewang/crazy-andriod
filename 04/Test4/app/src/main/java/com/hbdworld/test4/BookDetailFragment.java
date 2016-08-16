package com.hbdworld.test4;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbdworld.test4.model.BookContent;

/**
 * Created by hbd on 16/8/16.
 */
public class BookDetailFragment extends ListFragment {

    public static final String ITEM_ID = "item_id";
    // 保存该Fragment显示的Book对象
    BookContent.Book book;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ITEM_ID)){
            book = BookContent.ITEMS.get(getArguments().getInt(ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_book_detail,container,false);
        if (book != null){
            ((TextView)rootView.findViewById(R.id.book_title)).setText(book.title);
            ((TextView)rootView.findViewById(R.id.book_desc)).setText(book.desc);
        }
        return rootView;
    }
}
