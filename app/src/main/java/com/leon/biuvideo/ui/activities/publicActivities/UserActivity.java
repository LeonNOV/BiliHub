package com.leon.biuvideo.ui.activities.publicActivities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.user.UserInfo;
import com.leon.biuvideo.databinding.ActivityUserBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.fragments.userFragments.UserMediaFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class UserActivity extends AsyncHttpActivity<ActivityUserBinding, UserInfo> {
    public static final String PARAM = "MID";
    private String mid;

    @Override
    public ActivityUserBinding getViewBinding() {
        return ActivityUserBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {
        mid = params.getString(PARAM, null);
        binding.back.setOnClickListener(v -> backPressed());
        binding.music.setOnClickListener(v -> Toast.makeText(context, "Music", Toast.LENGTH_SHORT).show());

        if (mid == null) {
            Toast.makeText(context, "mid无效", Toast.LENGTH_SHORT).show();
            backPressed();
        }

        binding.follow.setOnTouchListener((v, event) -> ViewUtils.Zoom(event, binding.follow));
        binding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                crossFade(true);
            } else {
                if (binding.toolBarName.getVisibility() != View.GONE) {
                    crossFade(false);
                }
            }
        });

        ViewUtils.initTabLayout(this, binding.userWorks.tabLayout, binding.userWorks.viewPager,
                List.of(new UserMediaFragment(mid), new UserMediaFragment(mid), new UserMediaFragment(mid)), "视频", "专栏", "相簿");
    }

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.API);
    }

    @Override
    protected Observable<UserInfo> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getUserInfo(mid);
    }

    @Override
    protected void async(ApiHelper<UserInfo> apiHelper) {
        apiHelper.setOnResult(userInfo -> {
            ViewUtils.setImg(context, binding.userBanner, userInfo.getData().getTopPhoto());
            ViewUtils.setImg(context, binding.userFace, userInfo.getData().getFace());
            binding.name.setText(userInfo.getData().getName());
            binding.toolBarName.setText(userInfo.getData().getName());

            setLevel(userInfo.getData().getLevel());
            if (userInfo.getData().getVip().getStatus() == 1) {
                binding.vip.setVisibility(View.VISIBLE);
                binding.vip.setText(userInfo.getData().getVip().getLabel().getText());
            }

            switch (userInfo.getData().getSex()) {
                case "男":
                    binding.gender.setImageResource(R.drawable.gender_man);
                    break;
                case "女":
                    binding.gender.setImageResource(R.drawable.gender_woman);
                    break;
                default:
                    binding.gender.setVisibility(View.GONE);
                    break;
            }

            binding.follow.setText(userInfo.getData().isFollowed() ? "已关注" : "关注");
            binding.follow.setOnClickListener(v -> {
                if (userInfo.getData().isFollowed()) {
                    binding.follow.setText("关注");
                    userInfo.getData().setFollowed(false);
                } else {
                    binding.follow.setText("已关注");
                    userInfo.getData().setFollowed(true);
                }
            });

            binding.uid.setText(String.format(Locale.CHINESE, "UID: %d", userInfo.getData().getMid()));
            binding.desc.setText(userInfo.getData().getSign());

            if (userInfo.getData().getSysNotice().getId() != 0) {
                String sysNotice = "";
                switch (userInfo.getData().getSysNotice().getId()) {
                    case 8:
                        sysNotice = "争议账户";
                        break;
                    case 11:
                    case 24:
                        sysNotice = "合约争议";
                        break;
                    case 20:
                        sysNotice = "纪念账号";
                        break;
                    case 22:
                        sysNotice = "合约诉讼";
                        break;
                    case 25:
                        sysNotice = "严重指控";
                        break;
                    default:
                        break;
                }

                binding.accountType.setText(sysNotice);
                binding.accountType.setTextColor(Color.parseColor(userInfo.getData().getSysNotice().getTextColor()));
            }

            getStat();
        }).doIt();
    }

    private void getStat() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API).getHttpApi().getUserStat(mid))
                .setOnResult(userStat -> {
                    binding.fans.setText(String.valueOf(userStat.getData().getFollower()));
                    binding.following.setText(String.valueOf(userStat.getData().getFollowing()));
                }).doIt();
    }

    private void setLevel(int level) {
        int img = 0;
        switch (level) {
            case 0:
                img = R.drawable.bili_user_level_0;
                break;
            case 1:
                img = R.drawable.bili_user_level_1;
                break;
            case 2:
                img = R.drawable.bili_user_level_2;
                break;
            case 3:
                img = R.drawable.bili_user_level_3;
                break;
            case 4:
                img = R.drawable.bili_user_level_4;
                break;
            case 5:
                img = R.drawable.bili_user_level_5;
                break;
            case 6:
                img = R.drawable.bili_user_level_6;
                break;
            default:
                break;
        }

        binding.level.setImageResource(img);
    }

    private void crossFade(boolean isShow) {
        binding.toolBarName.animate()
                .alpha(isShow ? 1f : 0f)
                .setDuration(700)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.toolBarName.setVisibility(isShow ? View.VISIBLE : View.GONE);
                    }
                });
    }
}