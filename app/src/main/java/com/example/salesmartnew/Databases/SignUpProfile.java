package com.example.salesmartnew.Databases;

import android.provider.BaseColumns;

public class SignUpProfile {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private SignUpProfile() {}

        /* Inner class that defines the table contents */
        public static class Users implements BaseColumns {
            public static final String TABLE_NAME = "SaleSmart";
            public static final String COLUMN_1= "image";
            public static final String COLUMN_2 = "fullName";
            public static final String COLUMN_3 = "userName";
            public static final String COLUMN_4 = "passWord";
            public static final String COLUMN_5 = "confirmPassword";

    }
}
