package com.example.marvelappitg.retrofitConfig;


import com.example.marvelappitg.models.ModelCharacterList;
import com.example.marvelappitg.models.modelcomicsdetails.ModelComicsDetails;
import com.example.marvelappitg.models.modeleventdetails.ModelEventDetails;
import com.example.marvelappitg.models.modelseriesdetails.ModelSeriesDetails;
import com.example.marvelappitg.models.modelstoriesdetails.ModelStoriesDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiCall {
    @GET("public/characters")
    Call<ModelCharacterList>getCompanyInfo(@Query("ts") String ts,@Query("apikey") String apikey,@Query("hash") String hash,@Query("offset") String offset);


    @GET
    Call<ModelComicsDetails> getcomicsDetails(@Url String url);


    @GET
    Call<ModelSeriesDetails> getseriesDetails(@Url String url);


    @GET
    Call<ModelStoriesDetails> getstoriesDetails(@Url String url);


    @GET
    Call<ModelEventDetails> geteventsDetails(@Url String url);

}

