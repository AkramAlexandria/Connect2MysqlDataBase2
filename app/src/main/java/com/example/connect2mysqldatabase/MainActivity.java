package com.example.connect2mysqldatabase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private static final String url = "jdbc:mysql://192.168.1.3:3306/test_clinet";
    private static final String user = "user";
    private static final String pass = "gTQ1nj5Gt7SdFU2u";
    TextView txtFirstName, txtLastName;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyTask().execute();
            }
        });
    }

    private class MyTask extends AsyncTask<Void, Void, Void>
    {
        private String fName = "", lName = "";

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();
                String  sql = "select * from clinet";
                final ResultSet rs = st.executeQuery(sql);
                rs.next();

                fName = rs.getString(2);
                lName = rs.getString(3);
            }
            catch (Exception e) { e.printStackTrace(); }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            txtFirstName.setText(fName);
            txtLastName.setText(lName);

            super.onPostExecute(aVoid);
        }
    }
}
