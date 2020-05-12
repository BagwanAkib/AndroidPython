package com.akibbagwan.androidpython;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.android.*;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.Python;

public class MainActivity extends AppCompatActivity {

    private TextView displayConsoleText;
    private PyObject classObject;
    private EditText edtCommand;
    private Python py;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!Python.isStarted()) {
            Log.i("PYTHON", "is ON");
            Python.start(new AndroidPlatform(this));
            py = Python.getInstance();
            classObject = py.getModule("demo_ui");
            displayConsoleText = findViewById(R.id.txt_display_text);
            edtCommand = findViewById(R.id.edt_command);
            displayConsoleText.setMovementMethod(new ScrollingMovementMethod());
            displayConsoleText.setText(
                    classObject.callAttr("print_function").toString()
            );
            ((Button) findViewById(R.id.btn_execute)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    classObject = py.getModule("demo_ui");
                    String txt = classObject.callAttr("run_command", edtCommand.getText().toString()).toString();
                    Log.e("PYTHON", txt);
                    displayConsoleText.setText("\n" + txt);
                }
            });
//            Log.e("PYTHON", classObject.callAttr("print_ip").toString());
        }
    }
}
