package com.test.peterphchen.junittest;

import android.net.Uri;

public class EquationContract {
    public static final int SCHEMA = 2;
    public static final String TABLE_NAME = "result";
    public static final String NUMMBER = "number";
    public static final String EQUATION = "equation";
    public static final String DATE = "date";
    public static final String ID = "_id";
    public static final String EQUATIONS_TABLE_CTRATE
            ="CREATE TABLE " + TABLE_NAME
            +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, number INTEGER, " +
            "equation TEXT, date TEXT);";
    public static final String EQUATIONS_TALBE_DELETE
            = "DROP TABLE IF EXIST"+ TABLE_NAME;
    public static final String CONTENT_SCHEME = "content://";
    public static final String CONTENT_AUTHORITY = "com.test.peterphchen.junittest";
    public static final String CONTENT_URI_STRING = CONTENT_SCHEME+CONTENT_AUTHORITY+"/"+TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);
    public static final String CONTENT_LIST_TYPE = "vnd.android.cursor.dir/"+CONTENT_AUTHORITY+"/"+TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/"+CONTENT_AUTHORITY+"/"+TABLE_NAME;
}
