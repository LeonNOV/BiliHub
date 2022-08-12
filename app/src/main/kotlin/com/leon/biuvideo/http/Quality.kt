package com.leon.biuvideo.http

/**
 * @Author Leon
 * @Time 2022/08/02
 * @Desc 当前支持的视频分辨率
 */
enum class Quality {
    /**
     * 240p 极速
     */
    Q240 {
        override fun toString(): String {
            return "6"
        }
    },

    /**
     * 360p 流畅
     */
    Q360 {
        override fun toString(): String {
            return "16"
        }
    },

    /**
     * 480p 清晰
     */
    Q480 {
        override fun toString(): String {
            return "32"
        }
    },

    /**
     * 720p 高清
     */
    Q720 {
        override fun toString(): String {
            return "64"
        }
    },

    /**
     * 720P60 高帧率
     */
    Q720P60 {
        override fun toString(): String {
            return "74"
        }
    },

    /**
     * 1080p 高清
     */
    Q1080 {
        override fun toString(): String {
            return "80"
        }
    },

    /**
     * 1080p+高码率
     */
    Q1080P {
        override fun toString(): String {
            return "112"
        }
    },

    /**
     * 1080P60 高帧率
     */
    Q1080P60 {
        override fun toString(): String {
            return "116"
        }
    },

    /**
     * 4K 超清
     */
    Q4K {
        override fun toString(): String {
            return "120"
        }
    }
}