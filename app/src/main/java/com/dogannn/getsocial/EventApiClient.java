package com.dogannn.getsocial;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface EventApiClient {

    @Headers({"Authorization: Bearer YzKMJ21JV5zm2Gvsrc89v1YgrvMb"})
    @GET("/sellers/search/events/v3")
    Call<Events> getEvents(
            @Query("city") String cityName,
            @Query("sort") String sort,
            @Query("rows") int row
    );
}
