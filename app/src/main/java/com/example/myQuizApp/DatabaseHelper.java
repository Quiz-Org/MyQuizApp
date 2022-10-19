package com.example.myQuizApp;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {

	   private static final String DB_NAME = "MyQuizDB";
	   private static final int DB_VERSION = 3;

	   DatabaseHelper (Context context){

	       super(context,DB_NAME, null, DB_VERSION);

	   }

	   @Override
	   public void onCreate(SQLiteDatabase db){

	       updateMyDatabase(db, 0, DB_VERSION);

	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

	       updateMyDatabase(db, oldVersion, newVersion);

	   }

	   private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){

	       if (oldVersion < 1) {

	           db.execSQL("CREATE TABLE QUIZ (_id INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME TEXT," + "DESCRIPTION TEXT," + "NUM_QUESTS INTEGER);");

	           insertQuiz(db, "History", "A short quiz on general history.", 2);

	       }

	       if (oldVersion < 2){

	           db.execSQL("CREATE TABLE QUESTION (_id INTEGER PRIMARY KEY AUTOINCREMENT," + "QUIZ_ID INTEGER," + "QUESTION_TEXT TEXT," + "FOREIGN KEY(QUIZ_ID) REFERENCES QUIZ (_id) );");

	           insertQuestion(db, 1, "What year was The Battle of Hastings?");
	           insertQuestion(db, 1, "How many different wives did Henry VIII have?");

	           db.execSQL("CREATE TABLE ANSWER (_id INTEGER PRIMARY KEY AUTOINCREMENT," + "QUESTION_ID INTEGER," + "ANSWER_TEXT TEXT," + "CORRECT INTEGER," + "FOREIGN KEY(QUESTION_ID) REFERENCES QUESTION(_id));" );

	           insertAnswer(db, 1 , "1066" , 1);
	           insertAnswer(db, 1 , "1939" , 0);
	           insertAnswer(db, 1 , "45" , 0);
	           insertAnswer(db, 1 , "1010" , 0);

	           insertAnswer(db, 2 , "6" , 1);
	           insertAnswer(db, 2 , "0" , 0);
	           insertAnswer(db, 2 , "64" , 0);
	           insertAnswer(db, 2 , "8" , 0);
	       }
	       if (oldVersion < 3){

	           insertQuiz(db, "Bits and Bytes", "A short quiz about basic data quantities", 5);

	           insertQuestion(db, 2, "What values can a bit hold?");
	           insertQuestion(db, 2, "How many bits make a nibble?");
	           insertQuestion(db, 2, "How many bits are there in a byte?");
	           insertQuestion(db, 2, "What is the biggest value a byte can hold?");
	           insertQuestion(db, 2, "1024 bytes equals what?");

	           insertAnswer(db, 3, "1 or 0", 1);
	           insertAnswer(db, 3, "Only 7", 0);
	           insertAnswer(db, 3, "Anything from one too ten", 0);
	           insertAnswer(db, 3, "No values at all", 0);

	           insertAnswer(db, 4 , "4", 1);
	           insertAnswer(db, 4 , "A half", 0);
	           insertAnswer(db, 4 , "7", 0);
	           insertAnswer(db, 4 , "64", 0);

	           insertAnswer(db, 5 , "8", 1);
	           insertAnswer(db, 5 , "64", 0);
	           insertAnswer(db, 5 , "128", 1);
	           insertAnswer(db, 5 , "1024", 1);

	           insertAnswer(db, 6, "255", 1);
	           insertAnswer(db, 6, "1000000", 0);
	           insertAnswer(db, 6, "42", 0);
	           insertAnswer(db, 6, "256", 0);

	           insertAnswer(db, 7, "1 KiloByte", 1);
	           insertAnswer(db, 7, "1 GigaByte", 0);
	           insertAnswer(db, 7, "4 KiloBytes", 0);
	           insertAnswer(db, 7, "1 Elephant", 0);

	       }
	   }


	   private void insertQuiz (SQLiteDatabase db, String name, String description, int numQuests){

	       ContentValues quizValues = new ContentValues();

	       quizValues.put("NAME", name);
	       quizValues.put("DESCRIPTION", description);
	       quizValues.put("NUM_QUESTS", numQuests);

	       db.insert("QUIZ", null, quizValues);

	   }

	   private void insertQuestion(SQLiteDatabase db, int quizID, String questionText){

	       ContentValues questionValues = new ContentValues();

	       questionValues.put("QUIZ_ID", quizID);
	       questionValues.put("QUESTION_TEXT", questionText);

	       db.insert("QUESTION", null, questionValues);
	   }

	   private void insertAnswer (SQLiteDatabase db, int questionID, String answer, int correct) {

	       ContentValues answerValues = new ContentValues();

	       answerValues.put("QUESTION_ID", questionID);
	       answerValues.put("ANSWER_TEXT", answer);
	       answerValues.put("CORRECT", correct);

	       db.insert("ANSWER", null, answerValues);
	   }
	}
