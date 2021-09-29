package com.example.loginreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnSubmit;
    EditText _txtFname, _txtLname, _txtPass, _txtEmail, _txtPhone;
    TextView _btnAccLogin;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper=new DatabaseHelper(this);
        _btnSubmit = (Button) findViewById(R.id.btnSubmit);
        _txtFname = (EditText) findViewById(R.id.txtFname);
        _txtLname = (EditText) findViewById(R.id.txtLname);
        _txtPass = (EditText) findViewById(R.id.txtPass);
        _txtEmail = (EditText) findViewById(R.id.txtEmail);
        _txtPhone = (EditText) findViewById(R.id.txtPhone);
        _btnAccLogin = (TextView) findViewById(R.id.btnAccLogin);

        _btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                }
                
                db=openHelper.getReadableDatabase();
                String FirstName=_txtFname.getText().toString();
                String LastName=_txtLname.getText().toString();
                String Password=_txtPass.getText().toString();
                String Email=_txtEmail.getText().toString();
                String Phone=_txtPhone.getText().toString();
                insertdata(FirstName, LastName, Password, Email, Phone);
                Toast.makeText(getApplicationContext(), "successfully registered", Toast.LENGTH_LONG).show();

            }
        });
        _btnAccLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });
    }
    private boolean CheckAllFields() {
        if (_txtFname.length() == 0) {
            _txtFname.setError("This field is required");
            return false;
        }
        if (_txtLname.length() == 0) {
            _txtLname.setError("This Field is required");
            return false;
        }
        if (_txtPass.length() == 0) {
            _txtPass.setError("This field is required");
            return false;
        }
        if (_txtEmail.length() == 0) {
            _txtEmail.setError("This Field is required");
            return false;
        }
        if (_txtPhone.length() == 0) {
            _txtPhone.setError("This Field is required");
            return false;
        }

        return true;
    }
    public void insertdata(String FirstName, String LastName, String Password, String Email, String Phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, FirstName);
        contentValues.put(DatabaseHelper.COL_3, LastName);
        contentValues.put(DatabaseHelper.COL_4, Password);
        contentValues.put(DatabaseHelper.COL_5, Email);
        contentValues.put(DatabaseHelper.COL_6, Phone);

        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);



    }
}