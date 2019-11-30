package com.mk.springrestapi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.mk.springrestapi.R;
import com.mk.springrestapi.models.Student;
import com.mk.springrestapi.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addStudent();
        //getStudents();
    }

    private void addStudent() {
        Student student = new Student();
        student.setId(1);
        student.setName("Kamal");
        student.setAge(32);

        Call<Student> res = RetrofitClient.getInstance().getApi().addStudent(student);
        res.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(@NonNull Call<Student> call, @NonNull Response<Student> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "Retrofit " + new Gson().toJson(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Student> call, @NonNull Throwable t) {
                Log.e(TAG, "Retrofit " + t.getMessage());
            }
        });
    }

    private void getStudents() {
        Call<List<Student>> res = RetrofitClient.getInstance().getApi().getStudents();
        res.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(@NonNull Call<List<Student>> call, @NonNull Response<List<Student>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "Retrofit " + new Gson().toJson(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Student>> call, @NonNull Throwable t) {
                Log.e(TAG, "Retrofit " + t.getMessage());
            }
        });
    }
}
