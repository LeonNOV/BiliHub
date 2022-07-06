package com.leon.biuvideo.http;

import com.leon.biuvideo.beans.home.HomeRecommend;
import com.leon.biuvideo.beans.home.drawerFunction.Series;
import com.leon.biuvideo.beans.search.SearchSuggestion;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc
 */
public interface HttpApi {
    String DEFAULT_COOKIE = "buvid3=A290DE6B-616B-F0ED-6C7E-3D347D9E2D6E37118infoc; rpdid=|(J~R~JullRY0J'uYkkmYRkl); LIVE_BUVID=AUTO3516235748669920; CURRENT_BLACKGAP=0; buvid_fp_plain=F8208EDA-C9C6-4C28-8A8D-99DE65A59945148830infoc; video_page_version=v_old_home; fingerprint_s=221bcc4bcfd2a7f6c417ff21c667c1db; buvid_fp=3e09918cc6353c4587ae0d12b648ed13; buvid4=A3F49DF4-3996-B186-977E-1D42F2DD958289040-022031514-RXW3vQfQ66OKIFi3lDC3SA==; PVID=2; fingerprint3=db99ea80e0ab1b86b10e1781bf9f0a4b; hit-dyn-v2=1; nostalgia_conf=-1; CURRENT_QUALITY=112; fingerprint=e5abcdec47965bf6034a4ee15f814d5f; blackside_state=0; is-2022-channel=1; sid=93azlu17; innersign=0; CURRENT_FNVAL=80; bp_video_offset_49405324=677488283035369500; i-wanna-go-back=-1; b_ut=7";

    /**
     * 首页推荐接口
     * 如果没有登录账户的话只能获取系统推荐的内容
     * 如果登录上的话，需要在请求头加入Cookie数据，即可获取个人推荐数据
     *
     * @return HomeRecommend
     */
    @GET("x/web-interface/index/top/rcmd?fresh_type=3&version=1&ps=10&fresh_idx=1&fresh_idx_1h=1&homepage_ver=1")
    Observable<HomeRecommend> getHomeRecommend();

    /**
     * 搜索建议接口
     *
     * @return SearchSuggestion
     * @param keyword   keyword
     */
    @GET("main/suggest?main_ver=v1")
    Observable<SearchSuggestion> getSearchSuggestion(@Query("term") String keyword);

    /**
     * https://www.bilibili.com/v/popular/rank/all
     *
     * https://api.bilibili.com/x/web-interface/popular/series/list
     *
     * @return  Series
     */
    @GET("x/web-interface/popular/series/list")
    Observable<Series> getSeries();

    /**
     * 热搜榜接口
     * <p>
     * 参数：
     * build：可为0
     * limit：默认为10，且只能为10
     * https://app.bilibili.com/x/v2/search/square
     * </p>
     *
     * @return HotSearch
     */
//    @GET("x/v2/search/square?build=0&limit=10")
//    Observable<HotSearch> getHotSearch();
    String hotSearch = "https://app.bilibili.com/x/v2/search/square";
}
