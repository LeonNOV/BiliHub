package com.leon.bilihub.http;

import com.leon.bilihub.beans.post.CreateGroup;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author Leon
 * @Time 2022/09/15
 * @Desc
 */
public interface PostApi {
    /**
     * 创建分组
     * <p>
     * https://api.bilibili.com/x/relation/tag/create
     *
     * @param groupName 新建组名称
     * @param csrf      csrf
     * @return {@link CreateGroup}
     */
    @POST("x/relation/tag/create")
    @FormUrlEncoded
    Observable<CreateGroup> createRelationGroup(@Field("tag") String groupName, @Field("csrf") String csrf);
}
