package com.mk.springrestapi.retrofit;

import com.mk.springrestapi.models.Student;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    /*@FormUrlEncoded
    @POST //@POST("create"): Url end point
    Call<ResponseBody> mCreate(
            @Field("name") String name,
            @Field("author") String author,
            @Field("title") String title,
            @Field("description") String description,
            @Field("url") String url,
            @Field("urlToImage") String urlToImage,
            @Field("publishedAt") String publishedAt,
            @Field("content") String content
    );*/

    @Headers("Content-Type: application/json")
    @POST("add")
    Call<Student> add(@Body Student student);

    @GET("students")
    Call<List<Student>> getAll(); //Call<List<Student>> getStudents(@Path("user") String user);

    @GET("student/{id}")
    Call<Student> getById(@Path("id") String id);
}
