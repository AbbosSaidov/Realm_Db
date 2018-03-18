package com.example.insigame.realm_db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
private  Realm realm;
private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

/*
        RealmResults<User> result = realm.where(User.class)
                .greaterThan("age", 10)  // implicit AND
                .beginGroup()
                .equalTo("name", "Peter")
                .or()
                .contains("name", "Jo")
                .endGroup()
                .findAll();*/

    }
    public void  Kirit(View view){
        editText =findViewById(R.id.editText);
        realm = Realm.getDefaultInstance();
        // All writes are wrapped in a transaction
        // to facilitate safe multi threading
        realm.beginTransaction();

        // Add a person
        Person person = realm.createObject(Person.class);
        person.setName(editText.getText().toString());
        person.setAge(14);

        realm.commitTransaction();
    }
public void Show(View view){
    realm = Realm.getDefaultInstance();
    realm.beginTransaction();

    Log.i("Login","1");
Log.i("Login","2");
    RealmResults<Person> realmCities= realm.where(Person.class).findAllAsync();Log.i("Login","3");
    //fetching the data
    realmCities.load();Log.i("Login","4");


String string= String.valueOf(realmCities.size());
    for(Person person:realmCities){
        string=string + person.getName();
    }


    TextView textView =findViewById(R.id.Textview);Log.i("Login","1");
    textView.setText(string);Log.i("Login","4");

    realm.commitTransaction();
}
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void DeleteAll(View view){
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Person.class)
                .findAll()
                .deleteAllFromRealm();
        realm.commitTransaction();
    }
}
