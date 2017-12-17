package akshayjinde.tgbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class stu_signup extends AppCompatActivity {
    Button b1;
    EditText name,email,s_mob,p_mob,adhar,pass,dob,roll_no,prn_no;
    TextView t1;
    ProgressDialog progressDialog;
    Spinner stu_class,stu_div,stu_tg;

    String student_class,student_div,student_tg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_signup);
        b1 = (Button) findViewById(R.id.sign_button_stu);

        name = (EditText) findViewById(R.id.name_field);
        email = (EditText) findViewById(R.id.email_field);
        pass = (EditText) findViewById(R.id.pass_field);
        s_mob = (EditText) findViewById(R.id.s_mobile);
        p_mob = (EditText) findViewById(R.id.p_mobile);
        adhar = (EditText) findViewById(R.id.adhar_card);
        dob = (EditText) findViewById(R.id.dob);
        roll_no = (EditText) findViewById(R.id.roll_no);
        prn_no = (EditText)findViewById(R.id.prn_no);

        t1 = (TextView) findViewById(R.id.signin_text_stu);
        stu_class = (Spinner)findViewById(R.id.stu_class);
        stu_div = (Spinner)findViewById(R.id.stu_div);
        stu_tg = (Spinner)findViewById(R.id.stu_tg);

        stu_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Toast.makeText(stu_signup.this,"PLease Select Class !",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        student_class = stu_class.getSelectedItem().toString();
                        break;
                    case 2:
                        student_class = stu_class.getSelectedItem().toString();
                        break;
                    case 3:
                        student_class = stu_class.getSelectedItem().toString();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        stu_div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Toast.makeText(stu_signup.this,"PLease Select Division !",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        student_div = stu_div.getSelectedItem().toString();
                        break;
                    case 2:
                        student_div = stu_div.getSelectedItem().toString();
                        break;
                    case 3:
                        student_div = stu_div.getSelectedItem().toString();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        stu_tg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Toast.makeText(stu_signup.this,"PLease Select Your TG !",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        student_tg = stu_tg.getSelectedItem().toString();
                        break;
                    case 2:
                        student_tg = stu_tg.getSelectedItem().toString();
                        break;
                    case 3:
                        student_tg = stu_tg.getSelectedItem().toString();
                        break;
                    case 4:
                        student_tg = stu_tg.getSelectedItem().toString();
                        break;
                    case 5:
                        student_tg = stu_tg.getSelectedItem().toString();
                        break;
                    case 6:
                        student_tg = stu_tg.getSelectedItem().toString();
                        break;
                    case 7:
                        student_tg = stu_tg.getSelectedItem().toString();
                        break;
                    case 8:
                        student_tg = stu_tg.getSelectedItem().toString();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    email.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(pass.getText().toString())) {
                    pass.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(s_mob.getText().toString())) {
                    s_mob.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(p_mob.getText().toString())) {
                    p_mob.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(adhar.getText().toString())) {
                    adhar.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(roll_no.getText().toString())) {
                    roll_no.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(prn_no.getText().toString())) {
                    roll_no.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(dob.getText().toString())) {
                    roll_no.setError("Required!!");
                    return;
                }
                signUp();
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(stu_signup.this, MainActivity.class));
            }
        });
        progressDialog = new ProgressDialog(this);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                int month = monthOfYear+1;
                dob.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(stu_signup.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public String POST(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name",name.getText().toString());
            jsonObject.accumulate("email",email.getText().toString());
            jsonObject.accumulate("rollno",roll_no.getText().toString());
            jsonObject.accumulate("class",student_class);
            jsonObject.accumulate("div",student_div);
            jsonObject.accumulate("teachername",student_tg);
            jsonObject.accumulate("prnno",prn_no.getText().toString());
            jsonObject.accumulate("studentphone",s_mob.getText().toString());
            jsonObject.accumulate("parentphone",p_mob.getText().toString());
            jsonObject.accumulate("dob",dob.getText().toString());
            jsonObject.accumulate("aadhar",adhar.getText().toString());
            jsonObject.accumulate("password",pass.getText().toString());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Registering...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            POST(urls[0]);
            return POST(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            Toast.makeText(getBaseContext(),result, Toast.LENGTH_LONG).show();
        }
    }

    public  void signUp(){

        if(isConnected()){
            new HttpAsyncTask().execute("http://35.154.136.177/studentsignup");
            startActivity(new Intent(stu_signup.this,stu_welcome.class));
        }
        else{
            Toast.makeText(stu_signup.this,"No Internet Connection ! Try Again",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
