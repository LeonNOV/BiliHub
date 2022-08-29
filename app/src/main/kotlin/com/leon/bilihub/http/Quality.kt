package com.leon.bilihub.http

/**
 * @Author Leon
 * @Time 2022/08/02
 * @Desc 当前支持的视频分辨率
 */
enum class Quality {
    /**
     * 240p 极速
     */
    Q6 {
        override fun toString(): String {
            return "6"
        }
    },

    /**
     * 360p 流畅
     */
    Q16 {
        override fun toString(): String {
            return "16"
        }
    },

    /**
     * 480p 清晰
     */
    Q32 {
        override fun toString(): String {
            return "32"
        }
    },

    /**
     * 720p 高清
     */
    Q64 {
        override fun toString(): String {
            return "64"
        }
    },

    /**
     * 720P60 高帧率
     */
    Q74 {
        override fun toString(): String {
            return "74"
        }
    },

    /**
     * 1080p 高清
     */
    Q80 {
        override fun toString(): String {
            return "80"
        }
    },

    /**
     * 1080p+高码率
     */
    Q112 {
        override fun toString(): String {
            return "112"
        }
    },

    /**
     * 1080P60 高帧率
     */
    Q116 {
        override fun toString(): String {
            return "116"
        }
    },

    /**
     * 4K 超清
     */
    Q120 {
        override fun toString(): String {
            return "120"
        }
    },

    /**
     * HDR 真彩色
     */
    Q125 {
        override fun toString(): String {
            return "125"
        }
    },

    /**
     * 杜比视界
     */
    Q126 {
        override fun toString(): String {
            return "126"
        }
    },

    /**
     * 8K 超高清
     */
    Q127 {
        override fun toString(): String {
            return "127"
        }
    }
}