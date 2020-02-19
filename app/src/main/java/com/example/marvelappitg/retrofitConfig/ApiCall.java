package com.example.marvelappitg.retrofitConfig;


import com.example.marvelappitg.models.modelcharacterlist.ModelCharacterList;
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
    Call<ModelCharacterList>getCharacterList(@Query("ts") String ts,@Query("apikey") String apikey,@Query("hash") String hash,@Query("offset") String offset);

    @GET("public/characters")
    Call<ModelCharacterList>getCharacterList(@Query("ts") String ts,@Query("apikey") String apikey,@Query("hash") String hash,@Query("offset") String offset,@Query("nameStartsWith") String nameStartsWith);


    @GET
    Call<ModelComicsDetails> getcomicsDetails(@Url String url,@Query("ts") String ts,@Query("apikey") String apikey,@Query("hash") String hash);


    @GET
    Call<ModelSeriesDetails> getseriesDetails(@Url String url,@Query("ts") String ts,@Query("apikey") String apikey,@Query("hash") String hash);


    @GET
    Call<ModelStoriesDetails> getstoriesDetails(@Url String url,@Query("ts") String ts,@Query("apikey") String apikey,@Query("hash") String hash);


    @GET
    Call<ModelEventDetails> geteventsDetails(@Url String url,@Query("ts") String ts,@Query("apikey") String apikey,@Query("hash") String hash);

}

