package com.leon.bilihub.http;

import com.leon.bilihub.beans.Config;
import com.leon.bilihub.beans.account.AccountNav;
import com.leon.bilihub.beans.account.CollectFolder;
import com.leon.bilihub.beans.account.CollectFolderDetail;
import com.leon.bilihub.beans.account.FavoriteFolder;
import com.leon.bilihub.beans.account.FavoriteFolderDetail;
import com.leon.bilihub.beans.account.History;
import com.leon.bilihub.beans.account.RelationDetail;
import com.leon.bilihub.beans.account.RelationTags;
import com.leon.bilihub.beans.account.WatchLater;
import com.leon.bilihub.beans.home.HomeRecommendApp;
import com.leon.bilihub.beans.home.HotSearch;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularHot;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularPrecious;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularRank;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularRankBangumi;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularRankPgc;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularWeekly;
import com.leon.bilihub.beans.home.searchResult.SearchResultArticle;
import com.leon.bilihub.beans.home.searchResult.SearchResultLive;
import com.leon.bilihub.beans.home.searchResult.SearchResultMedia;
import com.leon.bilihub.beans.home.searchResult.SearchResultUser;
import com.leon.bilihub.beans.home.searchResult.SearchResultVideo;
import com.leon.bilihub.beans.home.channel.ChannelCategory;
import com.leon.bilihub.beans.home.channel.ChannelData;
import com.leon.bilihub.beans.home.channel.ChannelDetail;
import com.leon.bilihub.beans.home.channel.UserChannelCategory;
import com.leon.bilihub.beans.home.drawerFunction.Series;
import com.leon.bilihub.beans.partition.PartitionData;
import com.leon.bilihub.beans.partition.PartitionRecommend;
import com.leon.bilihub.beans.partition.PartitionTag;
import com.leon.bilihub.beans.publicBeans.resources.Reply;
import com.leon.bilihub.beans.publicBeans.resources.SubReply;
import com.leon.bilihub.beans.publicBeans.resources.article.ArticleInfo;
import com.leon.bilihub.beans.publicBeans.resources.audio.AudioInfo;
import com.leon.bilihub.beans.publicBeans.resources.audio.AudioResources;
import com.leon.bilihub.beans.publicBeans.resources.live.LiveInfo;
import com.leon.bilihub.beans.publicBeans.resources.live.LiveStream;
import com.leon.bilihub.beans.publicBeans.resources.picture.PictureInfo;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcDetail;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcRelation;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcRecommend;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcStream;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoDetail;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoRelation;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoStream;
import com.leon.bilihub.beans.publicBeans.user.UserArticle;
import com.leon.bilihub.beans.publicBeans.user.UserAudio;
import com.leon.bilihub.beans.publicBeans.user.UserInfo;
import com.leon.bilihub.beans.publicBeans.user.UserOrder;
import com.leon.bilihub.beans.publicBeans.user.UserPicture;
import com.leon.bilihub.beans.publicBeans.user.UserStat;
import com.leon.bilihub.beans.publicBeans.user.UserVideo;
import com.leon.bilihub.beans.search.SearchSuggestion;
import com.leon.bilihub.beans.home.channel.ChannelDetailFeatured;
import com.leon.bilihub.beans.home.channel.ChannelDetailMultiple;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc 各API接口，详细文档看{https://console-docs.apipost.cn/preview/7efb949e265ab7d7/9fdcecbd7dd69d3f}
 */
public interface HttpApi {
    String COOKIE = "Cookie";

    interface ApiGet {
        /**
         * 配置文件
         * <p>
         * https://gitee.com/leon_xf/bili-hub-proxy/raw/master/config.json
         * <p>
         *
         * @return {@link Config}
         */
        @GET("master/config.json")
        Observable<Config> getConfig();
    }

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
     * <p>
     * https://app.bilibili.com/x/v2/feed/index?idx=0&flush=5&column=4&device=pad&device_name=SM-N9600&pull=true
     *
     * @return {@link HomeRecommendApp}
     */
    @GET("x/v2/feed/index")
    Observable<HomeRecommendApp> getHomeRecommendApp();

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
     * 综合热门
     * <p>
     * https://www.bilibili.com/v/popular/all
     * <p>
     * https://api.bilibili.com/x/web-interface/popular?ps=20&pn=1
     *
     * @return {@link PopularHot}
     */
    @GET("x/web-interface/popular?ps=20")
    Observable<PopularHot> getPopularHot(@Query("pn") int pageNum);

    /**
     * 每周必看
     * <p>
     * https://www.bilibili.com/v/popular/weekly?num=175
     * <p>
     * https://api.bilibili.com/x/web-interface/popular/series/one?number=175
     *
     * @return {@link PopularWeekly}
     */
    @GET("x/web-interface/popular/series/one")
    Observable<PopularWeekly> getPopularWeekly(@Query("number") int number);

    /**
     * 入站必刷（可不携带参数进行请求）
     * <p>
     * https://www.bilibili.com/v/popular/history
     * <p>
     * https://api.bilibili.com/x/web-interface/popular/precious?page_size=100&page=1
     *
     * @return {@link PopularPrecious}
     */
    @GET("x/web-interface/popular/precious")
    Observable<PopularPrecious> getPopularPrecious();

    /**
     * 全站排行榜-全站、国创相关、动画、音乐、舞蹈、游戏、知识、科技、运动、汽车、生活、美食、动物圈、鬼畜、时尚、娱乐、影视、原创、新人
     * <p>
     * https://www.bilibili.com/v/popular/rank/all
     * <p>
     * https://api.bilibili.com/x/web-interface/ranking/v2?rid=0&type=all
     *
     * @return {@link PopularRank}
     */
    @GET("x/web-interface/ranking/v2?type=all")
    Observable<PopularRank> getPopularRank(@Query("rid") String rid);

    /**
     * 全站排行榜-全站、国创相关、动画、音乐、舞蹈、游戏、知识、科技、运动、汽车、生活、美食、动物圈、鬼畜、时尚、娱乐、影视、原创、新人
     * <p>
     * https://www.bilibili.com/v/popular/rank/all
     * <p>
     * https://api.bilibili.com/x/web-interface/ranking/v2?rid=0&type=all
     *
     * @return {@link PopularRank}
     */
    @GET("x/web-interface/ranking/v2")
    Observable<PopularRank> getPopularRank(@Query("rid") String rid, @Query("type") String type);

    /**
     * 全站排行榜-国产动画、纪录片、电影、电视剧、综艺
     * <p>
     * https://www.bilibili.com/v/popular/rank/all
     * <p>
     * https://api.bilibili.com/pgc/season/rank/web/list?day=3&season_type=4
     *
     * @return {@link PopularRankPgc}
     */
    @GET("pgc/season/rank/web/list?day=3&")
    Observable<PopularRankPgc> getPopularRankPgc(@Query("season_type") Condition.SeasonType seasonType);

    /**
     * 全站排行榜-番剧
     * <p>
     * https://www.bilibili.com/v/popular/rank/all
     * <p>
     * https://api.bilibili.com/pgc/web/rank/list?day=3&season_type=1
     *
     * @return {@link PopularRankBangumi}
     */
    @GET("pgc/web/rank/list?day=3&season_type=1")
    Observable<PopularRankBangumi> getPopularRankBangumi();


    /**
     * 用户详细数据-包含大部分信息
     * https://space.bilibili.com/38366371
     * <p>
     * http://api.bilibili.com/x/space/wbi/acc/info?mid=3602311
     * https://api.bilibili.com/x/space/acc/info?mid=3602311
     *
     * @param mid UID
     * @return {@link UserInfo}
     */
    @GET("x/space/wbi/acc/info")
    Observable<UserInfo> getUserInfo(@Query("mid") String mid);

    /**
     * 用户详细数据-主要含有粉丝数
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
     * https://api.bilibili.com/x/space/wbi/arc/search?ps=30
     * <p>
     * https://space.bilibili.com/492393/video
     *
     * @param mid     UID
     * @param pageNum 页码，从1开始
     * @param order   排序方式，默认为pubdate<br>pubdate：最新发布<br>click：最多播放<br>stow：最多收藏
     * @return {@link UserVideo}
     */
    @GET("x/space/wbi/arc/search?ps=30")
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
    Observable<FavoriteFolder> getFavoriteFolder(@Query("up_mid") String mid);

    /**
     * 用户收藏夹详情
     * <p>
     * https://space.bilibili.com/29120845/favlist?fid=1467796745&ftype=create
     * <p>
     * https://api.bilibili.com/x/v3/fav/resource/list?media_id=1570364745&pn=1&ps=20&order=mtime&platform=web
     *
     * @param folderId 收藏夹id
     * @param pageNum  页码，从1开始
     * @return {@link FavoriteFolderDetail}
     */
    @GET("x/v3/fav/resource/list?ps=20&order=mtime&platform=web")
    Observable<FavoriteFolderDetail> getFavoriteFolderDetail(@Query("media_id") String folderId, @Query("pn") int pageNum);

    /**
     * 用户订阅的 收藏和合集 文件夹
     * <p>
     * https://space.bilibili.com/37090048/favlist
     * <p>
     * https://api.bilibili.com/x/v3/fav/folder/collected/list?pn=1&ps=20&up_mid=37090048&platform=web&jsonp=jsonp
     *
     * @param pageNum 页码，从1开始
     * @param mid     UID
     * @return {@link CollectFolder}
     */
    @GET("x/v3/fav/folder/collected/list?ps=20&platform=web&jsonp=jsonp")
    Observable<CollectFolder> getCollectFolder(@Query("pn") int pageNum, @Query("up_mid") String mid);

    /**
     * 用户合集文件夹详情
     * <p>
     * https://space.bilibili.com/37090048/favlist?fid=523&ftype=collect&ctype=21
     * <p>
     * https://api.bilibili.com/x/space/fav/season/list?season_id=523&pn=1&ps=20&jsonp=jsonp
     *
     * @param folderId 文件夹id
     * @param pageNum  页码，从1开始
     * @return {@link CollectFolderDetail}
     */
    @GET("x/space/fav/season/list?ps=20&jsonp=jsonp")
    Observable<CollectFolderDetail> getCollectFolderDetail(@Query("season_id") String folderId, @Query("pn") int pageNum);

    /**
     * 用户历史记录
     * <strong>只需要Cookie</strong>
     * <p>
     * https://www.bilibili.com/account/history
     * <p>
     * http://api.bilibili.com/x/v2/history/toview
     *
     * @param max    默认为0
     * @param viewAt 默认为0
     * @return {@link WatchLater}
     */
    @GET("x/web-interface/history/cursor?business=archive")
    Observable<History> getUserHistory(@Query("max") int max, @Query("view_at") int viewAt);

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
    Observable<RelationTags> getUserRelationGroups();

    /**
     * 用户关注分组详情
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
     * 子分区Tag
     * <p>
     * https://www.bilibili.com/v/game/stand_alone
     * <p>
     * https://api.bilibili.com/x/tag/hots?rid=17&type=0
     *
     * @param rid 子分区Tag
     * @return {@link PartitionTag}
     */
    @GET("x/tag/hots?type=0")
    Observable<PartitionTag> getPartitionTags(@Query("rid") int rid);

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
    Observable<PartitionRecommend> getPartitionRecommend(@Query("pn") int pageNum, @Query("rid") int rid);

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
    Observable<PartitionData> getPartitionData(@Query("cate_id") int tagId, @Query("keyword") String keyword, @Query("page") int pageNum, @Query("time_from") String startTime, @Query("time_to") String endTime);

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
     * 搜索结果-视频
     * <p>
     * https://search.bilibili.com/video?keyword=123
     *
     * @return {@link SearchResultVideo}
     */
    @GET("x/web-interface/search/type?page_size=30&platform=Android&search_type=video")
    Observable<SearchResultVideo> getSearchResultVideo(@Query("page") int pageNum, @Query("keyword") String keyword,
                                                       @Query("order") Condition.VideoOrder order,
                                                       @Query("duration") Condition.VideoDuration duration,
                                                       @Query("tids") Condition.VideoTids tids);

    /**
     * 搜索结果-番剧
     * <p>
     * https://search.bilibili.com/bangumi?keyword=123
     *
     * @return {@link SearchResultMedia}
     */
    @GET("x/web-interface/search/type?page_size=30&platform=Android&search_type=media_bangumi")
    Observable<SearchResultMedia> getSearchResultBangumi(@Query("page") int pageNum, @Query("keyword") String keyword);

    /**
     * 搜索结果-影视
     * <p>
     * https://search.bilibili.com/pgc?keyword=123
     *
     * @return {@link SearchResultMedia}
     */
    @GET("x/web-interface/search/type?page_size=30&platform=Android&search_type=media_ft")
    Observable<SearchResultMedia> getSearchResultFt(@Query("page") int pageNum, @Query("keyword") String keyword);

    /**
     * 搜索结果-直播
     * <p>
     * https://search.bilibili.com/live?keyword=123
     *
     * @return {@link SearchResultLive}
     */
    @GET("x/web-interface/search/type?page_size=30&platform=Android&search_type=live")
    Observable<SearchResultLive> getSearchResultLive(@Query("page") int pageNum, @Query("keyword") String keyword,
                                                     @Query("order") Condition.LiveOrder order,
                                                     @Query("search_type") Condition.LiveSearchType searchType);

    /**
     * 搜索结果-专栏
     * <p>
     * https://search.bilibili.com/article?keyword=123
     *
     * @return {@link SearchResultArticle}
     */
    @GET("x/web-interface/search/type?page_size=30&platform=Android&search_type=article")
    Observable<SearchResultArticle> getSearchResultArticle(@Query("page") int pageNum, @Query("keyword") String keyword,
                                                           @Query("order") Condition.ArticleOrder order,
                                                           @Query("category_id") Condition.ArticleCategoryId categoryId);

    /**
     * 搜索结果-用户
     * <p>
     * https://search.bilibili.com/upuser?keyword=123
     *
     * @return {@link SearchResultUser}
     */
    @GET("x/web-interface/search/type?page_size=30&platform=Android&search_type=bili_user")
    Observable<SearchResultUser> getSearchResultUser(@Query("page") int pageNum, @Query("keyword") String keyword,
                                                     @Query("order") Condition.UserOrder order,
                                                     @Query("order_sort") Condition.UserOrderSort orderSort,
                                                     @Query("user_type") Condition.UserType userType);

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
     * @param channelId 频道分类ID
     * @param type      0：全部<br>2022-2009：2022-2009年精选
     * @param offset    offset
     * @return {@link ChannelDetailFeatured}
     */
    @GET("x/web-interface/web/channel/featured/list?page_size=30")
    Observable<ChannelDetailFeatured> getChannelDetailFeatured(@Query("channel_id") String channelId, @Query("filter_type") int type, @Query("offset") String offset);

    /**
     * 频道详情-综合
     *
     * <p>
     * https://www.bilibili.com/v/channel/17683
     * <p>
     * https://api.bilibili.com/x/web-interface/web/channel/multiple/list?channel_id=17683&sort_type=hot&offset=&page_size=30
     *
     * @param channelId 频道分类ID
     * @param sort      hot：近期热门<br>view：播放最多<br>new：最新投稿
     * @param offset    offset
     * @return {@link ChannelDetailMultiple}
     */
    @GET("x/web-interface/web/channel/multiple/list?page_size=30")
    Observable<ChannelDetailMultiple> getChannelDetailMultiple(@Query("channel_id") String channelId, @Query("sort_type") String sort, @Query("offset") String offset);

    /**
     * 频道详情-频道详情数据
     *
     * <p>
     * https://www.bilibili.com/v/channel/17683
     * <p>
     * https://api.bilibili.com/x/web-interface/web/channel/detail?channel_id=17683
     *
     * @param channelId 频道分类ID
     * @return {@link ChannelDetail}
     */
    @GET("x/web-interface/web/channel/detail")
    Observable<ChannelDetail> getChannelDetail(@Query("channel_id") String channelId);

    /**
     * 音频详细数据
     *
     * <p>
     * https://www.bilibili.com/audio/au305581
     * <p>
     * https://www.bilibili.com/audio/music-service-c/web/song/info?sid=305581
     *
     * @param sid 音频ID
     * @return {@link AudioInfo}
     */
    @GET("audio/music-service-c/web/song/info")
    Observable<AudioInfo> getAudioInfo(@Query("sid") String sid);

    /**
     * 音频资源链接
     *
     * <p>
     * https://www.bilibili.com/audio/au305581
     * <p>
     * https://www.bilibili.com/audio/music-service-c/web/url?sid=2478206
     *
     * @param sid 音频ID
     * @return {@link AudioResources}
     */
    @GET("audio/music-service-c/web/url")
    Observable<AudioResources> getAudioResources(@Query("sid") String sid);

    /**
     * 文章详细数据
     *
     * <p>
     * https://www.bilibili.com/read/cv17456178
     * <p>
     * https://api.bilibili.com/x/article/viewinfo?id=17456178
     *
     * @param articleId 音频ID
     * @return {@link ArticleInfo}
     */
    @GET("x/article/viewinfo")
    Observable<ArticleInfo> getArticleInfo(@Query("id") String articleId);

    /**
     * 相簿详细数据
     *
     * <p>
     * https://t.bilibili.com/645930194838749188
     * <p>
     * https://api.bilibili.com/x/polymer/web-dynamic/v1/detail?id=645930194838749188
     *
     * @param dynamicId 相簿ID
     * @return {@link PictureInfo}
     */
    @GET("x/polymer/web-dynamic/v1/detail")
    Observable<PictureInfo> getPictureInfo(@Query("id") String dynamicId);

    /**
     * 相簿详细数据
     * <strong>只需要Cookie</strong>
     * <p>
     * https://www.bilibili.com/
     * <p>
     * https://api.bilibili.com/x/web-interface/nav
     *
     * @return {@link AccountNav}
     */
    @GET("x/web-interface/nav")
    Observable<AccountNav> getAccountInfo();

    /**
     * 视频详细数据
     * <p>
     * https://www.bilibili.com/video/BV1iW4y1y7jq
     * <p>
     * https://api.bilibili.com/x/web-interface/view/detail?bvid=BV1iW4y1y7jq
     *
     * @param bvid bvid
     * @return {@link VideoDetail}
     */
    @GET("x/web-interface/view/detail")
    Observable<VideoDetail> getVideoDetail(@Query("bvid") String bvid);

    /**
     * 番剧/电影/电视剧/纪录片 详细数据
     * <p>
     * https://www.bilibili.com/bangumi/play/ss33981
     * <p>
     * http://api.bilibili.com/pgc/view/web/season?season_id=33981
     *
     * @param id seasonId
     * @return {@link PgcDetail}
     */
    @GET("pgc/view/web/season")
    Observable<PgcDetail> getPgcDetail(@Query("season_id") String id);

    /**
     * 当前选集与已登录用户的关系数据
     * <p>
     * https://www.bilibili.com/bangumi/play/ss41832
     * <p>
     * https://api.bilibili.com/pgc/season/episode/web/info?ep_id=640113
     *
     * @param epId epId: 选集ID
     * @return {@link PgcRelation}
     */
    @GET("pgc/season/episode/web/info")
    Observable<PgcRelation> getPgcRelation(@Query("ep_id") int epId);

    /**
     * 当前视频与已登录用户的关系数据
     * <p>
     * https://www.bilibili.com/video/BV1iW4y1y7jq
     * <p>
     * https://api.bilibili.com/x/web-interface/archive/relation?aid=941426915&bvid=BV1iW4y1y7jq
     *
     * @param bvid bvid
     * @return {@link VideoRelation}
     */
    @GET("x/web-interface/archive/relation")
    Observable<VideoRelation> getVideoRelation(@Query("bvid") String bvid);

    /**
     * 视频流获取
     * <p>
     * https://www.bilibili.com/video/BV1rp4y1e745
     * <p>
     * https://api.bilibili.com/x/player/playurl?bvid=BV1rp4y1e745&cid=244954665&qn=120&fnver=0&fnval=64&fourk=1&platform=pc
     *
     * @param bvid    bvid
     * @param cid     cid
     * @param quality 清晰度数值
     * @return {@link VideoStream}
     */
    @GET("x/player/playurl?fnver=0&fnval=64&fourk=1&platform=pc")
    Observable<VideoStream> getVideoStream(@Query("bvid") String bvid, @Query("cid") String cid, @Query("qn") Quality quality);

    /**
     * 番剧\电影\纪录片\国创\电视剧\综艺 视频流获取
     * <p>
     * https://www.bilibili.com/video/BV1rp4y1e745
     * <p>
     * https://api.bilibili.com/pgc/player/web/playurl?bvid=BV1ZY4y187fA&cid=800255390&qn=64&fnver=0&fnval=64&fourk=1
     *
     * @param bvid    bvid
     * @param cid     cid
     * @param quality 清晰度数值
     * @return {@link PgcStream}
     */
    @GET("pgc/player/web/playurl?fnver=0&fnval=64&fourk=1&platform=pc")
    Observable<PgcStream> getPgcVideoStream(@Query("bvid") String bvid, @Query("cid") String cid, @Query("qn") Quality quality);

    /**
     * 番剧\电影\纪录片\国创\电视剧\综艺 推荐获取
     * <p>
     * https://www.bilibili.com/bangumi/play/ss39433
     * <p>
     * https://api.bilibili.com/pgc/season/web/related/recommend?season_id=39433
     *
     * @param seasonId seasonId
     * @return {@link PgcRecommend}
     */
    @GET("pgc/season/web/related/recommend")
    Observable<PgcRecommend> getPgcRecommend(@Query("season_id") String seasonId);

    /**
     * 评论内容获取
     * <p>
     * 部分数据需要携带Cookie才能获取，如：IP属地等
     * <p>
     * https://www.bilibili.com/video/BV1tY4y1w7GQ
     * <p>
     * https://api.bilibili.com/x/v2/reply/wbi/main?mode=3&next=0&oid=644125629&plat=1&type=1
     *
     * @param oid  评论区ID
     * @param mode 0/3: 仅按热度、2: 仅按时间
     * @param paginationStr 下一页
     * @param type 评论类型
     * @return {@link Reply}
     */
    @GET("x/v2/reply/wbi/main")
    Observable<Reply> getReply(@Query("oid") String oid, @Query("mode") int mode, @Query("pagination_str") String paginationStr, @Query("type") ReplyType type);

    /**
     * 评论回复内容获取
     * <p>
     * 部分数据需要携带Cookie才能获取，如：IP属地等
     * <p>
     * https://www.bilibili.com/video/BV1tY4y1w7GQ
     * <p>
     * https://api.bilibili.com/x/v2/reply/reply?oid=856991530&pn=1&ps=10&root=125659569200&type=1
     *
     * @param oid     评论区ID
     * @param root    主评论ID
     * @param pageNum 页码
     * @return {@link SubReply}
     */
    @GET("x/v2/reply/reply?ps=10&type=1")
    Observable<SubReply> getSubReply(@Query("oid") String oid, @Query("root") String root, @Query("pn") int pageNum);

    /**
     * 直播间详情
     * <p>
     * https://live.bilibili.com/25138603
     * <p>
     * https://api.live.bilibili.com/xlive/web-room/v1/index/getInfoByRoom?room_id=25138603
     *
     * @param roomId 直播间ID
     * @return {@link LiveInfo}
     */
    @GET("xlive/web-room/v1/index/getInfoByRoom")
    Observable<LiveInfo> getLiveInfo(@Query("room_id") String roomId);

    /**
     * 直播流获取
     * <p>
     * https://live.bilibili.com/47917
     * <p>
     * http://api.live.bilibili.com/room/v1/Room/playUrl?cid=47917&qn=0&platform=h5
     *
     * @param roomId 直播间ID
     * @param qn     清晰度
     * @return {@link LiveStream}
     */
    @GET("room/v1/Room/playUrl?platform=h5")
    Observable<LiveStream> getLiveStream(@Query("cid") String roomId, @Query("qn") int qn);

    interface HttpRaw {
        /**
         * 频道页面
         * <p>
         * https://www.bilibili.com/v/channel/17683
         *
         * @param channelId 频道ID
         * @return source code
         */
        @GET("v/channel/{channelId}")
        Observable<ResponseBody> getChannelFeatured(@Path("channelId") String channelId);

        /**
         * 文章页面
         * <p>
         * https://www.bilibili.com/read/cv17456178
         *
         * @param articleId 文章ID
         * @return source code
         */
        @GET("read/cv{articleId}")
        Observable<ResponseBody> getArticleRaw(@Path("articleId") String articleId);
    }
}
