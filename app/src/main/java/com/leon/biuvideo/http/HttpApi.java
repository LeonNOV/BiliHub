package com.leon.biuvideo.http;

import com.leon.biuvideo.beans.account.Collect;
import com.leon.biuvideo.beans.account.FavoriteFolder;
import com.leon.biuvideo.beans.account.RelationDetail;
import com.leon.biuvideo.beans.account.RelationTags;
import com.leon.biuvideo.beans.account.WatchLater;
import com.leon.biuvideo.beans.home.HomeRecommend;
import com.leon.biuvideo.beans.home.HotSearch;
import com.leon.biuvideo.beans.home.channel.ChannelCategory;
import com.leon.biuvideo.beans.home.channel.ChannelData;
import com.leon.biuvideo.beans.home.channel.UserChannelCategory;
import com.leon.biuvideo.beans.home.drawerFunction.Series;
import com.leon.biuvideo.beans.partition.PartitionData;
import com.leon.biuvideo.beans.partition.PartitionRecommend;
import com.leon.biuvideo.beans.publicBeans.user.UserArticle;
import com.leon.biuvideo.beans.publicBeans.user.UserAudio;
import com.leon.biuvideo.beans.publicBeans.user.UserInfo;
import com.leon.biuvideo.beans.publicBeans.user.UserOrder;
import com.leon.biuvideo.beans.publicBeans.user.UserPicture;
import com.leon.biuvideo.beans.publicBeans.user.UserStat;
import com.leon.biuvideo.beans.publicBeans.user.UserVideo;
import com.leon.biuvideo.beans.search.SearchSuggestion;
import com.leon.biuvideo.beans.home.channel.ChannelDetailFeatured;
import com.leon.biuvideo.beans.home.channel.ChannelDetailMultiple;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc 各API接口，详细文档看{https://console-docs.apipost.cn/preview/7efb949e265ab7d7/9fdcecbd7dd69d3f}
 */
public interface HttpApi {
    String COOKIE = "Cookie";
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
     * @param mid UID
     * @return {@link UserInfo}
     */
    @GET("x/space/acc/info")
    Observable<UserInfo> getUserInfo(@Query("mid") String mid);

    /**
     * https://space.bilibili.com/492393
     * <p>
     * http://api.bilibili.com/x/relation/stat?vmid=492393
     *
     * @param mid UID
     * @return {@link UserStat}
     */
    @GET("x/relation/stat")
    Observable<UserStat> getUserStat(@Query("vmid") String mid);

    /**
     * 用户视频数据
     * <p>
     * https://api.bilibili.com/x/space/arc/search?ps=30
     * <p>
     * https://space.bilibili.com/492393/video
     *
     * @param mid     UID
     * @param pageNum 页码，从1开始
     * @param order   排序方式，默认为pubdate<br>pubdate：最新发布<br>click：最多播放<br>stow：最多收藏
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
     * @param type         1：番剧<br>2： 剧集
     * @param followStatus 0: 全部<br>1：想看<br>2：在看<br>3：看过
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
     * 用户关注分组
     * <strong>只需要Cookie</strong>
     * <p>
     * https://space.bilibili.com/49405324/fans/follow
     * <p>
     * http://api.bilibili.com/x/relation/tag?tagid=-10&ps=50&pn=1
     *
     * @param tagId 特别关注恒为-10；默认分组恒为0
     * @param pn    页码
     * @return {@link RelationTags}
     */
    @GET("x/relation/tag?ps=50")
    Observable<RelationDetail> getUserRelationDetail(@Query("tagid") String tagId, @Query("pn") int pn);

    /**
     * 分区子Tag推荐
     * <p>
     * https://www.bilibili.com/v/kichiku
     * <p>
     * https://api.bilibili.com/x/web-interface/dynamic/region?ps=20&pn=1&rid=22
     *
     * @param pageNum 特别关注恒为-10；默认分组恒为0
     * @param rid     分区Tag推荐
     * @return {@link PartitionRecommend}
     */
    @GET("x/web-interface/dynamic/region?ps=20")
    Observable<PartitionRecommend> getPartitionRecommend(@Query("pn") int pageNum, @Query("rid") String rid);

    /**
     * 分区数据
     * <p>
     * https://www.bilibili.com/v/kichiku/guide
     * <p>
     * https://s.search.bilibili.com/cate/search?main_ver=v3&search_type=video&view_type=hot_rank&copy_right=-1&new_web_tag=1&order=click&cate_id=22&page=1&pagesize=30&time_from=20220615&time_to=20220715
     *
     * @param tagId     标签ID
     * @param keyword   关键字
     * @param pageNum   页码，从1开始
     * @param startTime 搜索结果发布区间，开始，格式：yyyyMMdd
     * @param endTime   搜索结果发布区间，开始，格式：yyyyMMdd
     * @return {@link PartitionData}
     */
    @GET("cate/search?main_ver=v3&search_type=video&view_type=hot_rank&copy_right=-1&new_web_tag=1&order=click&pagesize=30")
    Observable<PartitionData> getPartitionData(@Query("cate_id") String tagId, @Query("keyword") String keyword, @Query("page") int pageNum, @Query("time_from") String startTime, @Query("time_to") String endTime);

    /**
     * 热搜排行榜
     * <p>
     * https://www.bilibili.com/
     * <p>
     * https://api.bilibili.com/x/web-interface/search/square?limit=10
     *
     * @return {@link HotSearch}
     */
    @GET("x/web-interface/search/square?limit=10")
    Observable<HotSearch> getHotSearch();

    /**
     * 频道列表分组
     * <p>
     * https://www.bilibili.com/v/channel
     * <p>
     * https://api.bilibili.com/x/web-interface/web/channel/category/list
     *
     * @return {@link ChannelCategory}
     */
    @GET("x/web-interface/web/channel/category/list")
    Observable<ChannelCategory> getChannelCategory();

    /**
     * 用户频道列表
     * <strong>只需要Cookie</strong>
     * <p>
     * https://www.bilibili.com/v/channel
     * <p>
     * https://api.bilibili.com/x/web-interface/web/channel/subscribe/list
     *
     * @return {@link UserChannelCategory}
     */
    @GET("x/web-interface/web/channel/subscribe/list")
    Observable<UserChannelCategory> getUserChannelCategory();

    /**
     * 频道列表详细数据
     *
     * <p>
     * https://www.bilibili.com/v/channel/type/3
     * <p>
     * https://api.bilibili.com/x/web-interface/web/channel/category/channel_arc/list?id=3&offset=0
     *
     * @param id     频道分类ID
     * @param offset offset
     * @return {@link ChannelData}
     */
    @GET("x/web-interface/web/channel/category/channel_arc/list")
    Observable<ChannelData> getChannelData(@Query("id") int id, @Query("offset") String offset);

    /**
     * 频道详情-精选
     *
     * <p>
     * https://www.bilibili.com/v/channel/17683
     * <p>
     * https://api.bilibili.com/x/web-interface/web/channel/featured/list?channel_id=17683&filter_type=0&offset=&page_size=30
     *
     * @param id     频道分类ID
     * @param type   0：全部<br>2022-2009：2022-2009年精选
     * @param offset offset
     * @return {@link ChannelDetailFeatured}
     */
    @GET("x/web-interface/web/channel/featured/list?page_size=30")
    Observable<ChannelDetailFeatured> getChannelDetailFeatured(@Query("channel_id") String id, @Query("filter_type") int type, @Query("offset") String offset);

    /**
     * 频道详情-综合
     *
     * <p>
     * https://www.bilibili.com/v/channel/17683
     * <p>
     * https://api.bilibili.com/x/web-interface/web/channel/multiple/list?channel_id=17683&sort_type=hot&offset=&page_size=30
     *
     * @param id     频道分类ID
     * @param sort   hot：近期热门<br>view：播放最多<br>new：最新投稿
     * @param offset offset
     * @return {@link ChannelDetailMultiple}
     */
    @GET("x/web-interface/web/channel/multiple/list?page_size=30")
    Observable<ChannelDetailMultiple> getChannelDetailMultiple(@Query("channel_id") String id, @Query("sort_type") String sort, @Query("offset") String offset);
}
