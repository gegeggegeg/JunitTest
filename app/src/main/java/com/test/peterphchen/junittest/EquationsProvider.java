package com.test.peterphchen.junittest;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class EquationsProvider extends ContentProvider {
    private static final String TAG = "EquationsProvider";
    private static final String number = DatabaseHelper.NUMBER;
    private static final String equation = DatabaseHelper.EQUATION;
    private static final String table = DatabaseHelper.TABLE;
    private DatabaseHelper dbhelper;
    private static final Uri content_uri = Uri.parse("content://com.test.peterphchen.junittest.EquationsProvider/result");
    @Override
    public boolean onCreate() {
        dbhelper= new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sort) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(table);
        String orderBy;
        if(TextUtils.isEmpty(sort))
            orderBy = number;
        else
            orderBy = sort;
        Cursor cursor = qb.query(dbhelper.getReadableDatabase(),projection,selection,
                selectionArgs,null,null,orderBy);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.dir/result";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long rowID = dbhelper.getWritableDatabase().insert(table,equation,contentValues);

        if(rowID >0)
            return  ContentUris.withAppendedId(content_uri,rowID);

        throw new SQLException("Fail to insert row id"+uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String where, @Nullable String[] whereArgs) {
        int count = dbhelper.getWritableDatabase().delete(table,where,whereArgs);
        try {
            getContext().getContentResolver().notifyChange(uri,null);
        }catch (Exception e){
            Log.e(TAG, "delete: "+e.getMessage());
        }

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String where, @Nullable String[] whereArgs) {
        int count = dbhelper.getWritableDatabase().update(table,contentValues,where,whereArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
