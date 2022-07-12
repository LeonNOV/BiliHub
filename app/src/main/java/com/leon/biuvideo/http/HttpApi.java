package com.leon.biuvideo.http;

import com.leon.biuvideo.beans.account.Collect;
import com.leon.biuvideo.beans.account.FavoriteFolder;
import com.leon.biuvideo.beans.account.RelationTags;
import com.leon.biuvideo.beans.account.WatchLater;
import com.leon.biuvideo.beans.home.HomeRecommend;
import com.leon.biuvideo.beans.home.drawerFunction.Series;
import com.leon.biuvideo.beans.publicBeans.user.UserArticle;
import com.leon.biuvideo.beans.publicBeans.user.UserAudio;
import com.leon.biuvideo.beans.publicBeans.user.UserInfo;
import com.leon.biuvideo.beans.publicBeans.user.UserOrder;
import com.leon.biuvideo.beans.publicBeans.user.UserPicture;
import com.leon.biuvideo.beans.publicBeans.user.UserStat;
import com.leon.biuvideo.beans.publicBeans.user.UserVideo;
import com.leon.biuvideo.beans.search.SearchSuggestion;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc 各API接口，详细文档看{https://console-docs.apipost.cn/preview/7efb949e265ab7d7/9fdcecbd7dd69d3f}
 */
public interface HttpApi {
    String DEFAULT_COOKIE = "buvid3=A290DE6B-616B-F0ED-6C7E-3D347D9E2D6E37118infoc; rpdid=|(J~R~JullRY0J'uYkkmYRkl); LIVE_BUVID=AUTO3516235748669920; CURRENT_BLACKGAP=0; buvid_fp_plain=F8208EDA-C9C6-4C28-8A8D-99DE65A59945148830infoc; video_page_version=v_old_home; fingerprint_s=221bcc4bcfd2a7f6c417ff21c667c1db; buvid_fp=3e09918cc6353c4587ae0d12b648ed13; buvid4=A3F49DF4-3996-B186-977E-1D42F2DD958289040-022031514-RXW3vQfQ66OKIFi3lDC3SA==; PVID=2; fingerprint3=db99ea80e0ab1b86b10e1781bf9f0a4b; hit-dyn-v2=1; nostalgia_conf=-1; CURRENT_QUALITY=112; fingerprint=e5abcdec47965bf6034a4ee15f814d5f; blackside_state=0; is-2022-channel=1; sid=93azlu17; innersign=0; CURRENT_FNVAL=80; bp_video_offset_49405324=677488283035369500; i-wanna-go-back=-1; b_ut=7";

    /**
     * 搜索建议接口
     *
     * @param keyword keyword
     * @return {@link SearchSuggestion}
     */
    @GET("main/suggest?main_ver=v1")
    Observable<SearchSuggestion> getSearchSuggestion(@Query("term") String keyword);

    /**
     * 首页推荐接口
     * 如果没有登录账户的话只能获取系统推荐的内容
     * 如果登录上的话，需要在请求头加入Cookie数据，即可获取个人推荐数据
     *
     * @return {@link HomeRecommend}
     */
    @GET("x/web-interface/index/top/rcmd?fresh_type=3&version=1&ps=10&fresh_idx=1&fresh_idx_1h=1&homepage_ver=1")
    Observable<HomeRecommend> getHomeRecommend();

    /**
     * https://www.bilibili.com/v/popular/rank/all
     * <p>
     * https://api.bilibili.com/x/web-interface/popular/series/list
     *
     * @return {@link Series}
     */
    @GET("x/web-interface/popular/series/list")
    Observable<Series> getSeries();

    /**
     * https://space.bilibili.com/492393
     * <p>
     * http://api.bilibili.com/x/space/acc/info?mid=492393
     *
     * @return {@link UserInfo}
     */
    @GET("x/space/acc/info")
    Observable<UserInfo> getUserInfo(@Query("mid") String mid);

    /**
     * https://space.bilibili.com/492393
     * <p>
     * http://api.bilibili.com/x/relation/stat?vmid=492393
     *
     * @param mid
     * @return {@link UserStat}
     */
    @GET("x/relation/stat")
    Observable<UserStat> getUserStat(@Query("vmid") String mid);

    /**
     * 用户视频数据
     * <p>
     * https://api.bilibili.com/x/space/arc/search?ps=30
     * <p>
     * 默认单页条目数为30
     * order：排序方式，默认为pubdate，可选
     * 最新发布：pubdate
     * 最多播放：click
     * 最多收藏：stow
     *
     * @return {@link UserVideo}
     */
    @GET("x/space/arc/search?ps=30")
    Observable<UserVideo> getUserVideo(@Query("mid") String mid, @Query("pn") int pageNum, @Query("order") String order);

    /**
     * 用户音频数据
     * <p>
     * https://space.bilibili.com/11253297/audio
     * <p>
     * https://api.bilibili.com/audio/music-service/web/song/upper?uid=11253297&pn=1&ps=30&order=1
     *
     * @param mid     UID
     * @param pageNum 页码
     * @return {@link UserAudio}
     */
    @GET("audio/music-service/web/song/upper?ps=30&order=1")
    Observable<UserAudio> getUserAudio(@Query("uid") String mid, @Query("pn") int pageNum);

    /**
     * 用户文章数据
     * <p>
     * https://space.bilibili.com/38366371/article
     * <p>
     * https://api.bilibili.com/x/space/article?mid=38366371&pn=1&ps=15
     *
     * @param mid     UID
     * @param pageNum 页码
     * @return {@link UserArticle}
     */
    @GET("x/space/article?ps=15")
    Observable<UserArticle> getUserArticle(@Query("mid") String mid, @Query("pn") int pageNum);

    /**
     * 用户相簿数据
     * <p>
     * https://space.bilibili.com/38366371/album
     * <p>
     * https://api.bilibili.com/x/dynamic/feed/draw/doc_list?uid=38366371&page_num=0&page_size=30&biz=all
     *
     * @param mid     UID
     * @param pageNum 页码，从0开始
     * @return {@link UserPicture}
     */
    @GET("x/dynamic/feed/draw/doc_list?page_size=30&biz=all")
    Observable<UserPicture> getUserPicture(@Query("uid") String mid, @Query("page_num") int pageNum);

    /**
     * 用户订阅数据
     * <p>
     * https://space.bilibili.com/17783613/bangumi
     * <p>
     * https://api.bilibili.com/x/space/bangumi/follow/list?type=1&follow_status=0&pn=1&ps=15&vmid=17783613
     *
     * @param type         1：番剧；2： 剧集
     * @param followStatus 0: 全部；1：想看；2：在看；3：看过
     * @param pageNum      页码
     * @param mid          UID
     * @return {@link UserOrder}
     */
    @GET("x/space/bangumi/follow/list?ps=15")
    Observable<UserOrder> getUserOrder(@Query("type") int type, @Query("follow_status") int followStatus, @Query("pn") int pageNum, @Query("vmid") String mid);

    /**
     * 用户收藏夹
     * <p>
     * https://space.bilibili.com/29120845/favlist
     * <p>
     * https://api.bilibili.com/x/v3/fav/folder/created/list-all?up_mid=29120845&jsonp=jsonp
     *
     * @param mid UID
     * @return {@link FavoriteFolder}
     */
    @GET("x/v3/fav/folder/created/list-all?jsonp=jsonp")
    Observable<FavoriteFolder> getUserFolder(@Query("up_mid") String mid);

    /**
     * 用户收藏和订阅文件夹
     * <p>
     * https://space.bilibili.com/37090048/favlist
     * <p>
     * https://api.bilibili.com/x/v3/fav/folder/collected/list?pn=1&ps=20&up_mid=37090048&platform=web&jsonp=jsonp
     *
     * @param pageNum 页码，从1开始
     * @param mid     UID
     * @return {@link Collect}
     */
    @GET("x/v3/fav/folder/collected/list?ps=20&platform=web&jsonp=jsonp")
    Observable<Collect> getUserCollect(@Query("pn") int pageNum, @Query("up_mid") String mid);

    /**
     * 用户稍后观看
     * <strong>只需要Cookie</strong>
     * <p>
     * https://www.bilibili.com/watchlater/#/list
     * <p>
     * http://api.bilibili.com/x/v2/history/toview
     *
     * @return {@link WatchLater}
     */
    @GET("x/v2/history/toview")
    Observable<WatchLater> getUserWatchLater();

    /**
     * 用户关注分组
     * <strong>只需要Cookie</strong>
     * <p>
     * https://space.bilibili.com/49405324/fans/follow
     * <p>
     * http://api.bilibili.com/x/relation/tags
     *
     * @return {@link RelationTags}
     */
    @GET("x/relation/tags")
    Observable<RelationTags> getUserRelationTags();

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
