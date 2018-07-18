package com.test.peterphchen.junittest;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class EquationsProvider extends ContentProvider {
    private static final String TAG = "EquationsProvider";
    private DatabaseHelper dbhelper = new DatabaseHelper(getContext());;
    private static final Uri content_uri = Uri.parse(EquationContract.CONTENT_URI);
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    @Override
    public boolean onCreate() {
        uriMatcher.addURI(EquationContract.CONTENT_AUTHORITY,EquationContract.TABLE_NAME,1001);
        uriMatcher.addURI(EquationContract.CONTENT_AUTHORITY,EquationContract.TABLE_NAME+"/#",1002);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sort) {
        switch (uriMatcher.match(uri)){
            case 1001:
                if(TextUtils.isEmpty(sort))
                    sort = EquationContract.ID;
                else
                break;
            case 1002:
                selection = EquationContract.ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                break;
            default:
                throw  new IllegalArgumentException("Can't query unknow URI"+uri);
        }
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor = database.query(EquationContract.TABLE_NAME,projection,selection,selectionArgs,
                null,null,sort);
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
        long rowID = dbhelper.getWritableDatabase().insert(EquationContract.TABLE_NAME,EquationContract.EQUATION,contentValues);

        if(rowID >0)
            return  ContentUris.withAppendedId(content_uri,rowID);

        throw new SQLException("Fail to insert row id"+uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String where, @Nullable String[] whereArgs) {
        int count = dbhelper.getWritableDatabase().delete(EquationContract.TABLE_NAME,where,whereArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String where, @Nullable String[] whereArgs) {
        int count = dbhelper.getWritableDatabase().update(EquationContract.TABLE_NAME,contentValues,where,whereArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
