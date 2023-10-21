package com.leon.bilihub.ui.activities.publicActivities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.publicBeans.resources.picture.PictureInfo;
import com.leon.bilihub.databinding.ActivityPictureViewerBinding;
import com.leon.bilihub.ui.dialogs.PictureViewerDialog;
import com.leon.bilihub.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/7/29
 * @Desc 图片查看界面
 */
public class PictureViewerActivity extends BaseActivity<ActivityPictureViewerBinding> {
    public static final String PARAM_A = "images";
    public static final String PARAM_B = "index";

    @Override
    public ActivityPictureViewerBinding getViewBinding() {
        return ActivityPictureViewerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        ImmersionBar.hideStatusBar(getWindow());

        ArrayList<PictureInfo.Data.Item.Modules.ModuleDynamic.Major.Draw.Item> pictures = params.getParcelableArrayList(PARAM_A);
        int currentIndex = params.getInt(PARAM_B);

        binding.indicator.setText(String.format(Locale.CHINESE, "%d/%d", currentIndex + 1, pictures.size()));
        binding.content.setPageMargin(40);
        binding.content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                binding.indicator.setText(String.format(Locale.CHINESE, "%d/%d", position + 1, pictures.size()));
            }
        });

        PictureViewerAdapter adapter = new PictureViewerAdapter(context, pictures);

        binding.content.setAdapter(adapter);
        binding.content.setCurrentItem(currentIndex);
    }

    /**
     * 图片查看页面适配器
     */
    private class PictureViewerAdapter extends PagerAdapter {
        private final Context context;
        private final List<PictureInfo.Data.Item.Modules.ModuleDynamic.Major.Draw.Item> pictures;
        private final List<AppCompatImageView> imageViews;

        private PictureViewerAdapter(Context context, List<PictureInfo.Data.Item.Modules.ModuleDynamic.Major.Draw.Item> pictures) {
            this.context = context;
            this.pictures = pictures;

            imageViews = new ArrayList<>(pictures.size());
            for (int i = 0; i < pictures.size(); i++) {
                AppCompatImageView imageView = new AppCompatImageView(context);
                imageView.setOnClickListener(v -> PictureViewerActivity.this.backPressed());

                imageView.setOnLongClickListener(v -> {
                    new PictureViewerDialog(context).show();
                    return true;
                });
                imageViews.add(imageView);
            }
        }

        @Override
        public int getCount() {
            return pictures.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            ViewUtils.setImg(context, imageViews.get(position), pictures.get(position).getSrc());

            return imageViews.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if (position == 0 && imageViews.size() == 0) {
                return;
            }
            if (position == imageViews.size()) {
                container.removeView(imageViews.get(--position));
            } else {
                container.removeView(imageViews.get(position));
            }
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }
    }
}