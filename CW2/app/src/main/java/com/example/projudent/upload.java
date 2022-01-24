package com.example.projudent;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface upload {
    @Multipart
    @POST("image")
    Call<RequestBody> uploadImage(@Part MultipartBody.Part part);
}
