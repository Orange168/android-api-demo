package api.android.demo.contentprovider;

import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContentResolverActivity extends Activity {

	private ListView listView  ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		queryFromNew() ;

	}
	
	private void queryFromNew()
	{
		listView = new ListView(this);
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        //startManagingCursor(cursor);���ǽ���õ�Cursor������Activity���?����Cursor���������ں�Activity���ܹ��Զ�ͬ����ʡȥ�Լ��ֶ�����Cursor
        startManagingCursor(cursor);
        ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1,
                cursor,
                new String[]{Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1});
        listView.setAdapter(listAdapter);
        
        setContentView(listView);
	}
	
	private void queryFromOld()
	{
		listView = new ListView(this);
        Cursor cursor = getContentResolver().query(People.CONTENT_URI, null, null, null, null);
        //startManagingCursor(cursor);���ǽ���õ�Cursor������Activity���?����Cursor���������ں�Activity���ܹ��Զ�ͬ����ʡȥ�Լ��ֶ�����Cursor
        startManagingCursor(cursor);
        ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1,
                cursor,
                new String[]{People.NAME},
                new int[]{android.R.id.text1});
        listView.setAdapter(listAdapter);
        
        setContentView(listView);
	}

}
