package com.bhd.friendbang;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SignUpActivity extends ActionBarActivity {
    private EditText userName, userDob, userEmail, userPassword, reenterPassword;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = (EditText) findViewById(R.id.userName);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userDob = (EditText) findViewById(R.id.userDob);
        userPassword = (EditText) findViewById(R.id.userPassword);
        reenterPassword = (EditText) findViewById(R.id.reenterPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signUp(View view) {
        if (fieldIsEmpty(userName)) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
        }

        if (fieldIsEmpty(userEmail)) {
            Toast.makeText(this, "Please enter an e-mail address", Toast.LENGTH_SHORT).show();
        } else if (!userEmail.getText().toString().matches("(.*)@mail.utoronto.ca")) {
            Toast.makeText(this, "Please enter a Utoronto email.", Toast.LENGTH_SHORT).show();
        }

        if (fieldIsEmpty(userDob)) {
            Toast.makeText(this, "Please enter a date of birth", Toast.LENGTH_SHORT).show();
        } else if (dateIsInvalid(userDob.getText().toString())) {
            Toast.makeText(this, "Please enter a valid date", Toast.LENGTH_SHORT).show();
        }

        if (fieldIsEmpty(userPassword) || fieldIsEmpty(reenterPassword)) {
            Toast.makeText(this, "Please enter password in both fields", Toast.LENGTH_SHORT).show();
        } else if (!userPassword.getText().toString().equals(reenterPassword.getText().toString())) {
            Toast.makeText(this, "THe passwords you entered do not match", Toast.LENGTH_SHORT).show();
        }


        Date date = null;
        try {
            date = formatter.parse(userDob.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User(userName.getText().toString(),date,userEmail.getText().toString());

        user.setPassword(userPassword.getText().toString());
        user.setUsername(userEmail.getText().toString());

        user.put("E-mail",userEmail.getText().toString());
        user.put("Password",userPassword.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean fieldIsEmpty(EditText field) {
        if (field.getText().toString().matches("")) {
            return true;
        }
        return false;
    }

    public boolean dateIsInvalid(String string) {
        Date date;
        try {
            date = formatter.parse(string);
        } catch (ParseException e) {
            return false;
        }

        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            Date today;
            try {
                today = formatter.parse(formatter.format(calendar));
                if (date.after(today)) {
                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
