package com.example.testexcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSqlite con = new ConexionSqlite(this, "bd_usuarios", null, 1);

        Button registrar = (Button) findViewById(R.id.button1);
        Button exportar = (Button) findViewById(R.id.button2);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        exportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportar();
            }
        });
    }

    public void registrar() {
        ConexionSqlite con = new ConexionSqlite(this, "bd_usuarios", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", "Hector");
        values.put("pass", "1234");

        Long resultante = db.insert("usuarios", "nombre", values);
        Toast.makeText(this, resultante + " ", Toast.LENGTH_LONG).show();
    }

    public void exportar() {
        String path = Environment.getExternalStorageDirectory().getPath() + "/testexcel/";

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        SQLiteToExcel sqLiteToExcel = new SQLiteToExcel(this, "bd_usuarios", path);

        sqLiteToExcel.exportSingleTable("usuarios", "nombre", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(String filePath) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
