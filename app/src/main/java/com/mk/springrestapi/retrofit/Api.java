package com.mk.springrestapi.retrofit;

import com.mk.springrestapi.models.Student;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

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

    @PUT("student/update/{id}")
    Call<Student> updateById(@Path("id") String id, @Body Map<String, String> body);

    @DELETE("student/delete/{id}")
    Call<Boolean> deleteById(@Path("id") String id);

    /*@Multipart
    @POST("/upload")
    //Call<ResponseBody> uploadImage(@Part MultipartBody.Part file);
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file, @Part("name") RequestBody requestBody);*/

    @POST("updatePhoto")
    @Multipart
    Call<String> uploadImage(@Part MultipartBody.Part img);

}
