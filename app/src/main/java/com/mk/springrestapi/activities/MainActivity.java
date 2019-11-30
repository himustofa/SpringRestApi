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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //addStudent();
        //getAllStudents();
        //getStudentById();
        //updateStudentById();
        deleteStudentById();
    }

    private void addStudent() {
        Student student = new Student();
        student.setId(1);
        student.setName("Kamal");
        student.setAge(32);

        Call<Student> res = RetrofitClient.getInstance().getApi().add(student);
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

    private void getAllStudents() {
        Call<List<Student>> res = RetrofitClient.getInstance().getApi().getAll();
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

    private void getStudentById() {
        Call<Student> res = RetrofitClient.getInstance().getApi().getById("10");
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

    private void updateStudentById() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Zaman");
        body.put("age", "45");

        Call<Student> res = RetrofitClient.getInstance().getApi().updateById("10", body);
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

    private void deleteStudentById() {
        Call<Boolean> res = RetrofitClient.getInstance().getApi().deleteById("10");
        res.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "Retrofit " + new Gson().toJson(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                Log.e(TAG, "Retrofit " + t.getMessage());
            }
        });
    }
}
