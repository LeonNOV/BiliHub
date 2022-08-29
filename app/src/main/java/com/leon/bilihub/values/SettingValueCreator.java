package com.leon.bilihub.values;

import com.leon.bilihub.http.Quality;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/25
 * @Desc
 */
public class SettingValueCreator {
    /**
     * 设置界面直播画质选项
     *
     * @return SettingLiveQualityList
     */
    public static List<SettingLiveQuality> createLiveQualityList() {
        return List.of(
                new SettingLiveQuality(30000, "杜比"),
                new SettingLiveQuality(20000, "4K"),
                new SettingLiveQuality(10000, "原画"),
                new SettingLiveQuality(400, "蓝光"),
                new SettingLiveQuality(250, "超清"),
                new SettingLiveQuality(150, "高清"),
                new SettingLiveQuality(80, "流畅")
        );
    }

    /**
     * 设置界面视频画质选项
     *
     * @return SettingVideoQualityList
     */
    public static List<SettingVideoQuality> createVideoQualityList() {
        return List.of(
                new SettingVideoQuality(Quality.Q127, "8K 超高清"),
                new SettingVideoQuality(Quality.Q126, "杜比视界"),
                new SettingVideoQuality(Quality.Q125, "HDR 真彩色"),
                new SettingVideoQuality(Quality.Q120, "4K 超清"),
                new SettingVideoQuality(Quality.Q116, "1080P60 高帧率"),
                new SettingVideoQuality(Quality.Q112, "1080P+ 高码率"),
                new SettingVideoQuality(Quality.Q80, "1080P 高清"),
                new SettingVideoQuality(Quality.Q74, "720P60 高帧率"),
                new SettingVideoQuality(Quality.Q64, "720P 高清"),
                new SettingVideoQuality(Quality.Q32, "480P 清晰"),
                new SettingVideoQuality(Quality.Q16, "360P 流畅"),
                new SettingVideoQuality(Quality.Q6, "240P 极速")
        );
    }

    public static class SettingVideoQuality {
        private final Quality quality;
        private final String display;
        private boolean selected;

        public SettingVideoQuality(Quality quality, String display) {
            this.quality = quality;
            this.display = display;
        }

        public Quality getQuality() {
            return quality;
        }

        public String getDisplay() {
            return display;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }

    public static class SettingLiveQuality {
        private final int qn;
        private final String display;
        private boolean selected;

        public SettingLiveQuality(int qn, String display) {
            this.qn = qn;
            this.display = display;
            this.selected = selected;
        }

        public int getQn() {
            return qn;
        }

        public String getDisplay() {
            return display;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}
