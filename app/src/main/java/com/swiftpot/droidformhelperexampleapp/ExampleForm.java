package com.swiftpot.droidformhelperexampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by Rodney on 25-Feb-16 @ 2:32:40 AM
 */
public class ExampleForm extends AppCompatActivity{


    public ExampleForm() {
    }

    Button btnSignin = null;
    EditText edtEmail,edtName,edtAge = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignin = (Button) findViewById(R.id.btnSignin);
        edtEmail  = (EditText) findViewById(R.id.edtEmail);
        edtName   = (EditText) findViewById(R.id.edtName);
        edtAge    = (EditText) findViewById(R.id.edtAge);

        EditText[] editTextsToBeValidated = new EditText[]{edtEmail,edtName,edtAge};

        Method nonStaticVoidMethod= null;
        try {

            nonStaticVoidMethod = getClass().getMethod("myClickMethod", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //Note : If using in A Fragment,The Class Must Be A Singleton.,so we can pass in a .getInstance as the object
        //But For a Normal Activity,ClassName.this as used in this example is enough,since Android returns a single instance by default.
        btnSignin.setOnClickListener(new com.swiftpot.formhelperlib.FormHelper(editTextsToBeValidated,
                                                                               nonStaticVoidMethod,
                                                                               ExampleForm.this){
            //do nothing inside here
        });
    }

    public void myClickMethod(){
        //this will be called only when all the edittexts are not empty otherwise,an error will be set on the edittext to require input
        Toast.makeText(getApplicationContext(),"Success!!\nNo edittext has an empty Value.\n\nGood!!",Toast.LENGTH_LONG).show();
    }
}
