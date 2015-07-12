package com.bhd.friendbang;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class SignInActivity extends ActionBarActivity {
    EditText email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email=(EditText) findViewById(R.id.loginEmail);
        password=(EditText) findViewById(R.id.loginPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
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

    public boolean fieldIsEmpty(EditText field) {
        if (field.getText().toString().matches("")) {
            return true;
        }
        return false;
    }

    public void signIn(View view){
        if(fieldIsEmpty(email)||fieldIsEmpty(password)){
            Toast.makeText(this, "Please enter E-mail and Password", Toast.LENGTH_SHORT).show();
        }

        User.logInInBackground(email.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null){
                    Toast.makeText(SignInActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignInActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
