package akshayjinde.tgbook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;
    TextView t1, t2;
    EditText e1, e2;
    int stu;
    String stu1;
    String loginCode;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.login_button_stu);
        b2 = (Button) findViewById(R.id.tg_login_button);
        t1 = (TextView) findViewById(R.id.signup_text_stu);
        t2 = (TextView) findViewById(R.id.t2);
        e1 = (EditText) findViewById(R.id.email_field_stu);
        e2 = (EditText) findViewById(R.id.pass_field_stu);
        stu = 1;
        stu1 = "true";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(e1.getText().toString())) {
                    e1.setError("Required!!");
                    return;
                }
                if (TextUtils.isEmpty(e2.getText().toString())) {
                    e2.setError("Required!!");
                    return;
                }

                signIn(e1.getText().toString(), e2.getText().toString());
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                //startActivity(new Intent(MainActivity.this,tg_login.class));
                if (stu == 1) {
                    stu = 0;
                    stu1 = "false";
                    t2.setText("Teacher Login");
                    t1.setText("New Teacher ! Sign Up Here");
                    b2.setText("Student Login");
                } else {
                    stu = 1;
                    stu1 = "true";
                    t2.setText("Student Login");
                    t1.setText("New Student ! Sign Up Here");
                    b2.setText("TG Login");
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, stu_signup.class);
                startActivity(i);
            }
        });
        progressDialog = new ProgressDialog(this);
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void signIn(String email, String pass) {

        if (isConnected()) {
            new HttpAsyncTask().execute("http://35.154.136.177/studentlogin", email, pass);
        } else {
            Toast.makeText(MainActivity.this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public String POST(String url, String email, String pass) {
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
            jsonObject.accumulate("email", email);
            jsonObject.accumulate("password", pass);

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
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Failed, Try Again !";

        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private class HttpAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Signing In...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            String json = POST(urls[0], urls[1], urls[2]);

            try {
                JSONObject parentObject = new JSONObject(json);
                loginCode = parentObject.getString("code");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return loginCode;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            if (loginCode.equals("200")) {
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), "Login Successful !", Toast.LENGTH_SHORT).show();
                if (stu == 1 && stu1.equals("true")) {
                    finish();
                    startActivity(new Intent(MainActivity.this, stu_welcome.class));
                } else {
                    finish();
                    startActivity(new Intent(MainActivity.this, tg_welcome.class));
                }
            } else if (loginCode.equals("401")) {
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), "Invalid User !", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}
