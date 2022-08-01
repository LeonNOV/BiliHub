package com.leon.biuvideo.http;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.navigation.PopUpToBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Leon
 * @Time 2022/08/01
 * @Desc 搜索结果条件
 */
public class SearchResultCondition {
    /**
     * 搜索结果-视频-排序方式
     */
    public enum VideoOrder {
        /**
         * 最多点击
         */
        Click {
            @NonNull
            @Override
            public String toString() {
                return "click";
            }
        },

        /**
         * 最新发布
         */
        Pubdate {
            @NonNull
            @Override
            public String toString() {
                return "pubdate";
            }
        },

        /**
         * 最多弹幕
         */
        Dm {
            @NonNull
            @Override
            public String toString() {
                return "dm";
            }
        },

        /**
         * 最多收藏
         */
        Stow {
            @NonNull
            @Override
            public String toString() {
                return "stow";
            }
        };
    }

    enum Duration {

    }

    enum Tids {

    }

    enum SearchType {

    }

    enum CategoryId {

    }

    enum OrderSort {

    }

    enum UserType {

    }
}
