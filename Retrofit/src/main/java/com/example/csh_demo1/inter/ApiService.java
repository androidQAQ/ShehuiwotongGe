package com.example.csh_demo1.inter;

import com.example.csh_demo1.bean.MBean;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 曹少航 on 2017/11/4.
 */

public interface ApiService {
    /**
     *  //http://result.eolinker.com/6rdnXgZ20130cc07fd3b192ce2715f6df4113e76fe201df?uri=info
     *
     */
  @GET("6rdnXgZ20130cc07fd3b192ce2715f6df4113e76fe201df")
   Observable<List<MBean>>  getParams(@Query("uri") String uri);
}
