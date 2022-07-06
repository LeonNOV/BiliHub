package com.leon.biuvideo.ui.activities.publicActivities;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.UserInfo;
import com.leon.biuvideo.databinding.ActivityUserBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.utils.ViewUtils;
import com.scwang.smartrefresh.header.util.ColorUtils;

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

    @Override
    protected void init() {
        mid = params.getString(PARAM, null);
        binding.articleBack.setOnClickListener(v -> backPressed());

        if (mid == null) {
            Toast.makeText(context, "mid无效", Toast.LENGTH_SHORT).show();
            backPressed();
        }

        binding.follow.setOnTouchListener((v, event) -> ViewUtils.Zoom(event, binding.follow));
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
            binding.follow.setOnClickListener(v -> Toast.makeText(context, "Follow: " + userInfo.getData().getName(), Toast.LENGTH_SHORT).show());
            setLevel(userInfo.getData().getLevel(), binding.level);
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

            binding.uid.setText(String.format(Locale.CHINESE, "UID: %d", userInfo.getData().getMid()));
            binding.desc.setText(userInfo.getData().getSign());
//            binding.fans.setText(userInfo.getData().get);

            String sysNotice = "";
            switch (userInfo.getData().getSysNotice().getId()) {
                case 8:
                    sysNotice = "争议账户";
                    break;
                case 11:
                    sysNotice = "合约争议";
                    break;
                case 20:
                    sysNotice = "纪念账号";
                    break;
                case 22:
                    sysNotice = "合约诉讼";
                    break;
                case 24:
                    sysNotice = "合约争议";
                    break;
                case 25:
                    sysNotice = "严重指控";
                    break;
                default:
                    break;
            }

            binding.accountType.setText(sysNotice);
            binding.accountType.setTextColor(Color.parseColor(userInfo.getData().getSysNotice().getTextColor()));
        }).doIt();
    }

    private void setLevel(int level, AppCompatImageView imageView) {
        int img = 0;
        switch (level) {
            case 0 : img = R.drawable.bili_user_level_0; break;
            case 1 : img = R.drawable.bili_user_level_1; break;
            case 2 : img = R.drawable.bili_user_level_2; break;
            case 3 : img = R.drawable.bili_user_level_3; break;
            case 4 : img = R.drawable.bili_user_level_4; break;
            case 5 : img = R.drawable.bili_user_level_5; break;
            case 6 : img = R.drawable.bili_user_level_6; break;
            default : break;
        }

        imageView.setImageResource(img);
    }
}