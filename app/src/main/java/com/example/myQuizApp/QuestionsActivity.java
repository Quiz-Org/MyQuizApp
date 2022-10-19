package com.example.myQuizApp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myquizapp.R;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsActivity extends Activity {
    //This class contains much of the code for the quiz. There is lots of android specific stuff that isn't important for this discussion, but there are some useful examples of OOP.
    //P.S. This is using eclipse, rather than android studio. I have recovered these classes from old coursework, so while it might look broken, i promise it worked!

    //The word extends is key here. This tells us that this class inherits from the class Activity. This is a native android class, that contains lots of the nuts and bolts of an android app.
    //In android development most of the code you want to be run, especially code that is kicked off by something like a button press will be in a class that inherits from Activity.
    //This means you can get on with what you want to do without worrying about how your code interacts with a touchscreen,or what happens when your user opens another app, then returns
    //That is all sorted in the Activity class that you are inheriting from.

    public static final String EXTRA_QUIZ_ID = "quizID";
    private int questNumCurrent;
    private int questNumTot;
    private final ArrayList<Question> questions = new ArrayList<>();
    private int quizID;
    private final int[] answerIDs = new int[4];

    //A slightly more complicated constructor, with lots of android stuff.
    protected void onCreate(Bundle savedInstanceState) {


        //lots is setup here, the UI (content view in android) and a quiz is fetched from a database, not important here
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_activity);
        //* get quiz id from intent
        quizID = (Integer) getIntent().getExtras().get(EXTRA_QUIZ_ID);
        questNumCurrent = -1;

        //* Cursor to get question num info from database
        try {

            SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            Cursor cursor = db.query("QUIZ", new String[]{"NUM_QUESTS"}, "_id = ?", new String[]{Integer.toString(quizID)}, null, null, null);

            if (cursor.moveToFirst()) {

                questNumTot = cursor.getInt(0);

            }

            cursor.close();
            db.close();

        } catch (SQLException e) {

            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG);
            toast.show();

        }

        loadData();
        refreshView();

    }

    //More setting up the data from a DB, not important.
    private void loadData() {

// loads all of the Questions Answers and their associated IDs from database
        try {

            SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            Cursor cursor = db.query("QUESTION", new String[]{"_id", "QUESTION_TEXT"}, "QUIZ_ID = ?", new String[]{Integer.toString(quizID)}, null, null, null);

            while (cursor.moveToNext()) {

                Question question = new Question(cursor.getInt(0), cursor.getString(1));
                questions.add(question);

            }

            cursor.close();

            for (Question question : questions) {

                cursor = db.query("ANSWER", new String[]{"_ID", "ANSWER_TEXT", "CORRECT"}, "QUESTION_ID = ?", new String[]{Integer.toString(question.getQuestionID())}, null, null, null);

                while (cursor.moveToNext()) {
                    Answer answer = new Answer(cursor.getInt(0), cursor.getString(1), (cursor.getInt(2) != 0));

                    question.addPossAnswer(answer);
                }

            }

            cursor.close();
            db.close();

        } catch (SQLException e) {

            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    private void refreshView() {

        questNumCurrent += 1;

        //Here is where my UI properly gets involved. With android lots of UI elements are already created, so if i want to use them, i can just create an instance!
        //I don't need to know how a 'radioGroup' works on the inside, or to make one myself, i just need to know how to interact with it from the outside.
        //The nitty gritty stuff is handled in the radioGroup class, so i don't need to worry about it!
        //Long story short, the possible answers are placed randomly in a UI element that the user can interact with, Most android users would recognise the result of a setup like this.
        RadioGroup answerGroup = (RadioGroup) findViewById(R.id.answerGroup);

        answerGroup.clearCheck();

        TextView questionBox = (TextView) findViewById(R.id.questionBox);
        questionBox.setText(questions.get(questNumCurrent).getQuestionText());

        boolean[] empty = new boolean[4];

        for (int i = 0; i < empty.length; i++) {

            boolean placed = false;

            while (!placed) {

                Random rand = new Random();

                int n = rand.nextInt(4) + 1;

                switch (n) {
                    case 1:

                        if (!empty[0]) {

                            RadioButton answerButton = (RadioButton) findViewById(R.id.answerButton1);
                            answerButton.setText(questions.get(questNumCurrent).getPossAnswer(i).getAnswerText());

                            empty[0] = true;
                            placed = true;

                            answerIDs[0] = questions.get(questNumCurrent).getPossAnswer(i).getAnswerID();

                        }
                        break;

                    case 2:

                        if (!empty[1]) {

                            RadioButton answerButton = (RadioButton) findViewById(R.id.answerButton2);
                            answerButton.setText(questions.get(questNumCurrent).getPossAnswer(i).getAnswerText());

                            empty[1] = true;
                            placed = true;

                            answerIDs[1] = questions.get(questNumCurrent).getPossAnswer(i).getAnswerID();

                        }
                        break;

                    case 3:

                        if (!empty[2]) {

                            RadioButton answerButton = (RadioButton) findViewById(R.id.answerButton3);
                            answerButton.setText(questions.get(questNumCurrent).getPossAnswer(i).getAnswerText());

                            empty[2] = true;
                            placed = true;

                            answerIDs[2] = questions.get(questNumCurrent).getPossAnswer(i).getAnswerID();

                        }
                        break;
                    case 4:

                        if (!empty[3]) {

                            RadioButton answerButton = (RadioButton) findViewById(R.id.answerButton4);
                            answerButton.setText(questions.get(questNumCurrent).getPossAnswer(i).getAnswerText());

                            empty[3] = true;
                            placed = true;

                            answerIDs[3] = questions.get(questNumCurrent).getPossAnswer(i).getAnswerID();

                        }
                        break;
                }

            }

        }

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        int id = view.getId();
        if (id == R.id.answerButton1) {
            if (checked)
                questions.get(questNumCurrent).setGivenAnswer(answerIDs[0]);
        } else if (id == R.id.answerButton2) {
            if (checked)
                questions.get(questNumCurrent).setGivenAnswer(answerIDs[1]);
        } else if (id == R.id.answerButton3) {
            if (checked)
                questions.get(questNumCurrent).setGivenAnswer(answerIDs[2]);
        } else if (id == R.id.answerButton4) {
            if (checked)
                questions.get(questNumCurrent).setGivenAnswer(answerIDs[3]);
        }

    }

    public void onNextButtonClicked(View view) {


        if (questNumCurrent < questNumTot - 1) {

            refreshView();

        } else {


            GlobalData.passQuestions = questions;

            endQuiz();

        }

    }

    private void endQuiz() {

        Intent intent = new Intent(QuestionsActivity.this, EndQuizActivity.class);
        startActivity(intent);

    }

}
