package com.leon.bilihub.http

/**
 * @Author Leon
 * @Time 2022/08/13
 * @Desc
 */
enum class ReplyType {
    /**
     * 视频
     */
    Video {
        override fun toString(): String {
            return "1"
        }
    },

    /**
     * 相簿
     */
    Picture {
        override fun toString(): String {
            return "11"
        }
    },

    /**
     * 专栏
     */
    Article {
        override fun toString(): String {
            return "12"
        }
    }
}
