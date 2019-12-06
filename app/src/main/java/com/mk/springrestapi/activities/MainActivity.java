package com.mk.springrestapi.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mk.springrestapi.R;
import com.mk.springrestapi.models.Student;
import com.mk.springrestapi.retrofit.RetrofitClient;
import com.mk.springrestapi.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ACTION_PICK_REQUEST_CODE = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image_view1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, ACTION_PICK_REQUEST_CODE);
            }
        });

        //addStudent();
        //getAllStudents();
        //getStudentById();
        //updateStudentById();
        //deleteStudentById();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ACTION_PICK_REQUEST_CODE && resultCode==RESULT_OK && data != null) {
            Uri uri = data.getData();
            //imageView.setImageURI(uri);
            //imageName = "img_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".png";
            //Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                if (bitmap != null) {
                    String imagePath = Utility.saveToInternalStorage(MainActivity.this, bitmap, "sample");
                    imageView.setImageBitmap(Utility.loadImage(imagePath, "sample"));
                    uploadToServer(imagePath, "sample");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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

    private void uploadToServer(String filePath, String imageName) {
        //Create a file object using file path
        //File file = new File(filePath);
        File file = new File(filePath, imageName);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
        Call<ResponseBody> res = RetrofitClient.getInstance().getApi().uploadImage(part, description);
        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "Retrofit " + new Gson().toJson(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(TAG, "Retrofit " + t.getMessage());
            }
        });
    }
}
