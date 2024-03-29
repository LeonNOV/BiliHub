package com.leon.bilihub.ui.activities.publicActivities;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.leon.bilihub.base.baseActivity.AsyncHttpActivity;
import com.leon.bilihub.beans.publicBeans.resources.picture.PictureInfo;
import com.leon.bilihub.databinding.ActivityPictureBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.RequestData;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.user.UserPictureDetailAdapter;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class PictureActivity extends AsyncHttpActivity<ActivityPictureBinding, PictureInfo> {
    public static final String PARAM = "dynamic";
    private String dynamicId;

    @Override
    public ActivityPictureBinding getViewBinding() {
        return ActivityPictureBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.dynamicId = params.getString(PARAM);
    }

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.API, context);
    }

    @Override
    protected Observable<PictureInfo> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getPictureInfo(dynamicId);
    }

    @Override
    protected void onAsyncResult(PictureInfo pictureInfo) {
        PictureInfo.Data.Item.Modules.ModuleAuthor moduleAuthor = pictureInfo.getData().getItem().getModules().getModuleAuthor();

        ViewUtils.setImg(context, binding.face, moduleAuthor.getFace());
        binding.face.setOnClickListener(v -> startActivity(UserActivity.class, Map.of(UserActivity.PARAM, moduleAuthor.getMid())));
        binding.name.setText(moduleAuthor.getName());
        binding.time.setText(moduleAuthor.getPubTime());
        binding.describe.setText(pictureInfo.getData().getItem().getModules().getModuleDynamic().getDesc().getText());

        PictureInfo.Data.Item.Modules.ModuleStat moduleStat = pictureInfo.getData().getItem().getModules().getModuleStat();
        binding.share.setText(ValueUtils.generateCN(moduleStat.getForward().getCount()));
        binding.comment.setText(ValueUtils.generateCN(moduleStat.getComment().getCount()));
        binding.like.setText(ValueUtils.generateCN(moduleStat.getLike().getCount()));

        int imgSize = pictureInfo.getData().getItem().getModules().getModuleDynamic().getMajor().getDraw().getItems().size();
        int spanCount;
        //判断要显示的列数
        if (imgSize % 3 == 0) {
            spanCount = 3;
        } else if (imgSize % 2 == 0) {
            spanCount = 2;
        } else {
            spanCount = 1;
        }

        if (spanCount == 1) {
            binding.content.setLayoutManager(new LinearLayoutManager(context));
        } else {
            binding.content.setLayoutManager(new GridLayoutManager(context, spanCount));
        }

        UserPictureDetailAdapter adapter = new UserPictureDetailAdapter(context, pictureInfo.getData().getItem().getModules().getModuleDynamic().getMajor().getDraw().getItems());
        adapter.appendHead(pictureInfo.getData().getItem().getModules().getModuleDynamic().getMajor().getDraw().getItems());

        ViewUtils.listInitializer(binding.content, adapter);
    }
}