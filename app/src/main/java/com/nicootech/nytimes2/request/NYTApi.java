package com.nicootech.nytimes2.request;

import com.nicootech.nytimes2.request.responses.ArticleSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTApi {

    //Search
    @GET("/svc/search/v2/articlesearch.json?")
    Call<ArticleSearchResponse> searchArticle(

            @Query("api-key")String key,
            @Query("q")String query,
            @Query("page")int page
   );
//    @GET("svc/search/v2/articlesearch.json?api-key=OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj&q=afghanistan&page=1")
//    Call<ArticleSearchResponse> searchArticle();

}
