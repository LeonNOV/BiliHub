package com.leon.biuvideo.http

/**
 * @Author Leon
 * @Time 2022/08/02
 * @Desc
 */
class Condition {
    /**
     * 搜索结果-视频-排序方式
     */
    enum class VideoOrder {
        /**
         * 最多点击
         */
        Click {
            override fun toString(): String {
                return "click"
            }
        },

        /**
         * 最新发布
         */
        Pubdate {
            override fun toString(): String {
                return "pubdate"
            }
        },

        /**
         * 最多弹幕
         */
        Dm {
            override fun toString(): String {
                return "dm"
            }
        },

        /**
         * 最多收藏
         */
        Stow {
            override fun toString(): String {
                return "stow"
            }
        }
    }

    /**
     * @Author Leon
     * @Time 2022/8/2
     * @Desc 搜索结果-视频-时长筛选
     */
    enum class VideoDuration {
        /**
         * 10分钟以下
         */
        A {
            override fun toString(): String {
                return "1"
            }
        },

        /**
         * 10-30分钟
         */
        B {
            override fun toString(): String {
                return "2"
            }
        },

        /**
         * 30-60分钟
         */
        C {
            override fun toString(): String {
                return "3"
            }
        },

        /**
         * 60分钟以上
         */
        D {
            override fun toString(): String {
                return "4"
            }
        }
    }

    enum class VideoTids {
        Douga {
            override fun toString(): String {
                return "1"
            }
        },

        Game {
            override fun toString(): String {
                return "4"
            }
        },

        Kichiku {
            override fun toString(): String {
                return "119"
            }
        },

        Music {
            override fun toString(): String {
                return "3"
            }
        },

        Dance {
            override fun toString(): String {
                return "129"
            }
        },

        Cinephile {
            override fun toString(): String {
                return "181"
            }
        },

        Ent {
            override fun toString(): String {
                return "5"
            }
        },

        Knowledge {
            override fun toString(): String {
                return "36"
            }
        },

        Tech {
            override fun toString(): String {
                return "188"
            }
        },

        Information {
            override fun toString(): String {
                return "202"
            }
        },

        Food {
            override fun toString(): String {
                return "211"
            }
        },

        Life {
            override fun toString(): String {
                return "160"
            }
        },

        Car {
            override fun toString(): String {
                return "223"
            }
        },

        Fashion {
            override fun toString(): String {
                return "155"
            }
        },

        Sports {
            override fun toString(): String {
                return "234"
            }
        },

        Animal {
            override fun toString(): String {
                return "217"
            }
        };
    }

    enum class LiveSearchType {
        /**
         * 直播间
         */
        Live {
            override fun toString(): String {
                return "live"
            }
        },

        /**
         * 主播
         */
        LiveUser {
            override fun toString(): String {
                return "live_user"
            }
        };
    }

    /**
     * 搜索结果-直播-排序方式
     */
    enum class LiveOrder {
        /**
         * 综合排序
         */
        Online {
            override fun toString(): String {
                return "online"
            }
        },

        /**
         * 最新开播
         */
        LiveTime {
            override fun toString(): String {
                return "live_time"
            }
        };
    }

    /**
     * 搜索结果-专栏-排序方式
     */
    enum class ArticleOrder {
        /**
         * 综合排序
         */
        TotalRank {
            override fun toString(): String {
                return "totalrank"
            }
        },

        /**
         * 最新发布
         */
        Pubdate {
            override fun toString(): String {
                return "pubdate"
            }
        },

        /**
         * 最多点击
         */
        Click {
            override fun toString(): String {
                return "click"
            }
        },

        /**
         * 最多喜欢
         */
        Attention {
            override fun toString(): String {
                return "attention"
            }
        },

        /**
         * 最多评论
         */
        Scores {
            override fun toString(): String {
                return "scores"
            }
        };
    }

    enum class ArticleCategoryId {
        /**
         * 全部分区
         */
        All {
            override fun toString(): String {
                return "0"
            }
        },

        /**
         * 动画
         */
        Douga {
            override fun toString(): String {
                return "2"
            }
        },

        /**
         * 游戏
         */
        Game {
            override fun toString(): String {
                return "1"
            }
        },

        /**
         * 影视
         */
        Ft {
            override fun toString(): String {
                return "28"
            }
        },

        /**
         * 生活
         */
        Life {
            override fun toString(): String {
                return "3"
            }
        },

        /**
         * 爱好
         */
        Hobby {
            override fun toString(): String {
                return "29"
            }
        },

        /**
         * 轻小说
         */
        Novel {
            override fun toString(): String {
                return "16"
            }
        },

        /**
         * 科技
         */
        Technology {
            override fun toString(): String {
                return "17"
            }
        },

        /**
         * 笔记
         */
        Note {
            override fun toString(): String {
                return "41"
            }
        };
    }

    /**
     * 搜索结果-用户-排序方式
     */
    enum class UserOrder {
        /**
         * 按粉丝数量排序
         */
        Fans {
            override fun toString(): String {
                return "fans"
            }
        },

        /**
         * 按用户等级排序
         */
        Level {
            override fun toString(): String {
                return "level"
            }
        };
    }

    /**
     * 搜索结果-用户-排序方式规则
     */
    enum class UserOrderSort {
        /**
         * 降序排序（从高到低）
         */
        DESC {
            override fun toString(): String {
                return "0"
            }
        },

        /**
         * 升序排序（从低到高）
         */
        ASC {
            override fun toString(): String {
                return "1"
            }
        }
    }

    enum class UserType {
        /**
         * 全部用户
         */
        All {
            override fun toString(): String {
                return "0"
            }
        },

        /**
         * UP主用户
         */
        Master {
            override fun toString(): String {
                return "1"
            }
        },

        /**
         * 普通用户
         */
        Ordinary {
            override fun toString(): String {
                return "2"
            }
        },

        /**
         * 认证用户
         */
        Verify {
            override fun toString(): String {
                return "3"
            }
        }
    }

    enum class SeasonType {
        /**
         * 国创动画
         */
        GcAnime {
            override fun toString(): String {
                return "4"
            }
        },

        /**
         * 纪录片
         */
        Document {
            override fun toString(): String {
                return "3"
            }
        },

        /**
         * 电影
         */
        Movie {
            override fun toString(): String {
                return "2"
            }
        },

        /**
         * 电视剧
         */
        TelePlay {
            override fun toString(): String {
                return "5"
            }
        },

        /**
         * 综艺
         */
        Variety {
            override fun toString(): String {
                return "7"
            }
        }
    }
}