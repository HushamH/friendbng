package com.bhd.friendbang;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.SignUpCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            return;
        }

        if (fieldIsEmpty(userEmail)) {
            Toast.makeText(this, "Please enter an e-mail address", Toast.LENGTH_SHORT).show();
            return;
        } else if (!userEmail.getText().toString().matches("(.*)@mail.utoronto.ca")) {
            Toast.makeText(this, "Please enter a Utoronto email.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fieldIsEmpty(userDob)) {
            Toast.makeText(this, "Please enter a date of birth", Toast.LENGTH_SHORT).show();
            return;
        } else if (!dateIsInvalid(userDob.getText().toString())) {
            Toast.makeText(this, "Please enter a valid date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fieldIsEmpty(userPassword) || fieldIsEmpty(reenterPassword)) {
            Toast.makeText(this, "Please enter password in both fields", Toast.LENGTH_SHORT).show();
            return;
        } else if (!userPassword.getText().toString().equals(reenterPassword.getText().toString())) {
            Toast.makeText(this, "THe passwords you entered do not match", Toast.LENGTH_SHORT).show();
            return;
        }


        Date date = null;
        try {
            date = formatter.parse(userDob.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        User user = new User(userName.getText().toString(), date, userEmail.getText().toString());

        user.setPassword(userPassword.getText().toString());
        user.setUsername(userEmail.getText().toString());
        user.setEmail(userEmail.getText().toString());
        user.setName(userName.getText().toString());
        user.put("Name",userName.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    Toast.makeText(SignUpActivity.this, "Please verify your E-mail by following the link we have sent you", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    if (e.getCode() == com.parse.ParseException.EMAIL_TAKEN || e.getCode() == com.parse.ParseException.USERNAME_TAKEN) {
                        Toast.makeText(SignUpActivity.this, "The E-mail you entered is linked to another account", Toast.LENGTH_SHORT).show();
                    } else if (e.getCode() == com.parse.ParseException.TIMEOUT||e.getCode() == com.parse.ParseException.CONNECTION_FAILED) {
                        Toast.makeText(SignUpActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }
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
            Date calendar = new Date();
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
