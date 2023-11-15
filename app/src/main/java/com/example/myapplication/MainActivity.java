package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.bean.Contact;
import com.example.myapplication.bean.ContactAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    ArrayList<Contact> contactList = new ArrayList<>();
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = findViewById(R.id.lvContact);
        ReadJSON readJSON = (ReadJSON) new ReadJSON().execute("https://batdongsanabc.000webhostapp.com/mob403lab3/index.php");

        //contactList.add(new Contact("001", "dwwdue", "efdefjh", "fdhefke", "ewdfehfje", "efuehf", "efdgyeghfc", "scghefj"));
        //contactList.add(new Contact("001", "dwwdue", "efdefjh", "fdhefke", "ewdfehfje", "efuehf", "efdgyeghfc", "scghefj"));
        //contactList.add(new Contact("001", "dwwdue", "efdefjh", "fdhefke", "ewdfehfje", "efuehf", "efdgyeghfc", "scghefj"));
        //contactList.add(new Contact("001", "dwwdue", "efdefjh", "fdhefke", "ewdfehfje", "efuehf", "efdgyeghfc", "scghefj"));
        //contactList = readJSON.getContactList();
        //adapter =  readJSON.getAdapter();
        adapter = new ContactAdapter(this,R.layout.item, contactList);


        lvContact.setAdapter(adapter);

    }

    private class ReadJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();

            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

                bufferedReader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("contacts");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject c = array.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");
                    String address = c.getString("address");
                    String gender = c.getString("gender");

                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");

                    Contact contact = new Contact(id, name, email, address, gender, mobile, home, office);

                    contactList.add(contact);
                    adapter.notifyDataSetChanged();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
    }
}