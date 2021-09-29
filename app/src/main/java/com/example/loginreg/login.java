package com.example.loginreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button _btnLogin;
    EditText _txtLoginEmail, _txtLoginPassw;
    Cursor cursor;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        _btnLogin = (Button) findViewById(R.id.btnLogin);
        _txtLoginEmail = (EditText) findViewById(R.id.txtLoginEmail);
        _txtLoginPassw = (EditText) findViewById(R.id.txtLoginPassw);

        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllFieldsChecked = CheckAllFields();

                String email = _txtLoginEmail.getText().toString();
                String passw = _txtLoginPassw.getText().toString();
                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5 + "=? AND " + DatabaseHelper.COL_4 + "=?", new String[]{email, passw});

                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(), "Succeffully loged in", Toast.LENGTH_LONG).show();
                        if (isAllFieldsChecked) {
                            Intent intent = new Intent(login.this, home.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean CheckAllFields() {
        if (_txtLoginEmail.length() == 0) {
            _txtLoginEmail.setError("This field is required");
            return false;
        }
        if (_txtLoginPassw.length() ==0) {
            _txtLoginPassw.setError("This Field is required");
            return false;
        }

        return true;
    }
}