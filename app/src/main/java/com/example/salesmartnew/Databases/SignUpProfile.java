package com.example.salesmartnew.Databases;

import android.provider.BaseColumns;

public class SignUpProfile {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private SignUpProfile() {}

        /* Inner class that defines the table contents */
        public static class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "entry";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_SUBTITLE = "subtitle";

    }
}
