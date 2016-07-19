package com.me.bookittothemoon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBAdapter extends SQLiteOpenHelper {
    //Handles interactions with the SQLite database

    //-------------------------------------------------------
    // Declarations
    //-------------------------------------------------------

    // Table  - Question Answer
    // DB Fields
    public static final String QUESTION_ID = "questionID";
    //public static final String AGE_GROUP = "ageGroup";
    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";
    public static final String IMAGE_URL = "imageURL";

    // DB Columns
    public static final int C0L_QUESTION_ID = 0;
    public static final int COL_AGE_GROUP = 1;
    public static final int COL_QUESTION = 2;
    public static final int COL_ANSWER = 3;
    public static final int COL_IMAGE_URL = 4;

    // Table - Fun Fact DB
    public static final String FUN_FACT_ID = "funFactID";
    public static final String AGE_GROUP = "ageGroup";
    public static final String FUN_FACT = "funFact";
    public static final String MEDIA_URL = "mediaURL";

    // DB Columns
    // Table - Fun Fact DB
    public static final int COL_FUN_FACT_ID = 0;
    public static final int COL_FUN_FACT = 2;
    public static final int COL_MEDIA_URL = 3;


    // DB info
    private static final String DB_NAME = "BookItToTheMoon";
    public static final String TABLE_NAME_QuestionAndAnswer = "QuestionAndAnswerDB";
    public static final String TABLE_NAME_FunFact = "FunFactDB";
    private static final int DB_VERSION = 3;

//-------------------------------------------------
// Methods
//-------------------------------------------------

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        System.out.println("IN CONSTRUCTOR");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME_FunFact;
        database.execSQL(dropTable);

        String dropTable1 = "DROP TABLE IF EXISTS " + TABLE_NAME_QuestionAndAnswer;
        database.execSQL(dropTable1);

        onCreate(database);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        System.out.println("IN ON CREATE");
        // DB create string
        final String DB_CREATE = "create table " + TABLE_NAME_QuestionAndAnswer
                + "(" + QUESTION_ID + " integer PRIMARY KEY, "
                + AGE_GROUP + " text, "
                + QUESTION + " text, "
                + ANSWER + " text, "
                + IMAGE_URL + " text)";

        database.execSQL(DB_CREATE);

        final String DB_CREATE_1 = "create table " + TABLE_NAME_FunFact
                + "(" + FUN_FACT_ID + " integer PRIMARY KEY, "
                + AGE_GROUP + " text, "
                + FUN_FACT + " text, "
                + MEDIA_URL + " text)";

        System.out.println(DB_CREATE_1);
        database.execSQL(DB_CREATE_1);

    }

    public void deleteTables(SQLiteDatabase database){
        final String DB_CREATE_1 = "drop table if exists" + TABLE_NAME_FunFact;
        System.out.println(DB_CREATE_1);
        database.execSQL(DB_CREATE_1);

        final String DB_CREATE_2 = "drop table if exists" + TABLE_NAME_QuestionAndAnswer;
        System.out.println(DB_CREATE_2);
        database.execSQL(DB_CREATE_2);
    }
    //-------------------------------------------------------
    // Insert values into table
    //-------------------------------------------------------
    public void insertQuestionAndAnswers(HashMap<String, String> queryValues){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("questionID", queryValues.get("questionID"));
        values.put("ageGroup", queryValues.get("ageGroup"));
        values.put("question", queryValues.get("question"));
        values.put("answer", queryValues.get("answer"));
        values.put("imageURL", queryValues.get("imageURL"));

        database.insert(TABLE_NAME_QuestionAndAnswer, null, values);
        database.close();
    }

    public void insertFunFacts(HashMap<String, String> queryValues){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("funFactID", queryValues.get("funFactID"));
        values.put("ageGroup", queryValues.get("ageGroup"));
        values.put("funFact", queryValues.get("funFact"));
        values.put("mediaURL", queryValues.get("mediaURL"));

        database.insert(TABLE_NAME_FunFact, null, values);
        database.close();
    }

    //-------------------------------------------------------
    // Get all entries
    //-------------------------------------------------------
    public ArrayList<HashMap<String,String>> getQuestions(int ageGroup){

        ArrayList<HashMap<String,String>> hoursArrayList = new ArrayList<HashMap<String,String>>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_QuestionAndAnswer + " WHERE ageGroup = " + ageGroup;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> hoursMap = new HashMap<String,String>();
                hoursMap.put("questionID", cursor.getString(C0L_QUESTION_ID));
                hoursMap.put("answer", cursor.getString(COL_ANSWER));
                hoursMap.put("question", cursor.getString(COL_QUESTION));
                hoursMap.put("imageURL", cursor.getString(COL_IMAGE_URL));
                hoursArrayList.add(hoursMap);
            }while(cursor.moveToNext());

        }
        return hoursArrayList;
    }

    public ArrayList<HashMap<String,String>> getFunFacts(int ageGroup) {

        ArrayList<HashMap<String, String>> hoursArrayList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_FunFact + " WHERE ageGroup = " + ageGroup;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> hoursMap = new HashMap<String, String>();
                hoursMap.put("funFactID", cursor.getString(COL_FUN_FACT_ID));
                hoursMap.put("funFact", cursor.getString(COL_FUN_FACT));
                hoursMap.put("mediaURL", cursor.getString(COL_MEDIA_URL));
                hoursArrayList.add(hoursMap);
            } while (cursor.moveToNext());

        }
        return hoursArrayList;
    }

    public void batchUpload_QuestionAndAnswer(){
        HashMap<String, String> qAndA = new HashMap<String, String>();
        qAndA.put("questionID", "1");
        qAndA.put("ageGroup", "0");
        qAndA.put("question", "0");
        qAndA.put("answer", "0");
        qAndA.put("imageURL", "");

        this.insertQuestionAndAnswers(qAndA);
    }

    public void batchUpload_Fun_Fact(){
        HashMap<String, String> funFact = new HashMap<String, String>();
        funFact.put("funFactID", "1");
        funFact.put("ageGroup", "0");
        funFact.put("funFact", "The Moon has no atmosphere");
        funFact.put("mediaURL", "");

        this.insertFunFacts(funFact);
    }
}
