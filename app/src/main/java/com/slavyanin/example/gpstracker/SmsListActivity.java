package com.slavyanin.example.gpstracker;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsListActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
        private SimpleCursorAdapter adapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sms_list);
            fillData();
        }

    private void fillData() {
        String[] from = new String[] {SmsTable.COLUMN_DATE, SmsTable.COLUMN_TEXT};
        int[] to = new int[] {R.id.smsDateAndTime, R.id.smsMessage};

        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.sms_row, null, from,
                to, 0);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (columnIndex == cursor.getColumnIndex(SmsTable.COLUMN_DATE)) {
                    Date d = new Date(cursor.getLong(columnIndex));
                    String formatted = new SimpleDateFormat("dd.MM.yy HH:mm").format(d);
                    ((TextView) view).setText(formatted);
                    return true;
                }
                return false;
            }
        });

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Uri messageUri = Uri.parse(SmsContentProvider.CONTENT_URI + "/" + id);
        String[] projection = {SmsTable.COLUMN_DATE, SmsTable.COLUMN_TEXT};
        Cursor cursor = getContentResolver().query(messageUri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String message = cursor.getString(cursor.getColumnIndexOrThrow(SmsTable.COLUMN_TEXT));

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message)
                    .setCancelable(true);
            AlertDialog alert = builder.create();
            alert.show();

            cursor.close();
        } else {
            Toast.makeText(this, "Can't extract SMS", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {SmsTable.COLUMN_ID, SmsTable.COLUMN_DATE, SmsTable.COLUMN_TEXT};
        CursorLoader cursorLoader = new CursorLoader(this, SmsContentProvider.CONTENT_URI,
                projection, null, null, "-" + SmsTable.COLUMN_DATE);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
