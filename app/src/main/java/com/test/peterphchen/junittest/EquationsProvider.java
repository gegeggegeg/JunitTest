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
    private DatabaseHelper dbhelper;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    @Override
    public boolean onCreate() {
        dbhelper = new DatabaseHelper(getContext());
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
        switch (uriMatcher.match(uri)){
            case 1001:
                return EquationContract.CONTENT_LIST_TYPE;
            case 1002:
                return EquationContract.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("unknown URI "+ uri );
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        String equation = contentValues.getAsString(EquationContract.EQUATION);
        if(equation == null) {
            throw new IllegalArgumentException("Equation can not be null");
        }
        switch (uriMatcher.match(uri)){
            case 1001:
                long rowID = dbhelper.getWritableDatabase().insert(EquationContract.TABLE_NAME,EquationContract.EQUATION,contentValues);
                if(rowID >0)
                    return  ContentUris.withAppendedId(uri,rowID);
                else
                    throw new SQLException("Fail to insert row id"+uri);
            default:
                throw  new IllegalArgumentException("Insertion is not supported for"+ uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String where, @Nullable String[] whereArgs) {
        int count;
        switch (uriMatcher.match(uri)){
            case 1001:
                count = dbhelper.getWritableDatabase().delete(EquationContract.TABLE_NAME,where,whereArgs);
                break;
            case 1002:
                where = EquationContract.EQUATION+ "=?";
                whereArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                count = dbhelper.getWritableDatabase().delete(EquationContract.TABLE_NAME,where,whereArgs);
                break;
             default:
                 throw new IllegalArgumentException("Unable to delete this uri "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String where, @Nullable String[] whereArgs) {
        String equation = contentValues.getAsString(EquationContract.EQUATION);
        if (equation == null) {
            throw new IllegalArgumentException("Equation can not be null");
        }
        int count;
        switch (uriMatcher.match(uri)){
            case 1001:
                count = dbhelper.getWritableDatabase().update(EquationContract.TABLE_NAME,contentValues,where,whereArgs);
                break;
            case 1002:
                where = EquationContract.EQUATION + "=?";
                whereArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                count = dbhelper.getWritableDatabase().update(EquationContract.TABLE_NAME,contentValues,where,whereArgs);
                break;
            default:
                throw  new IllegalArgumentException("Update is not supported for"+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
