package com.leon.biuvideo.ui.activities.drawerFunction;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.account.History;
import com.leon.biuvideo.databinding.ActivityHistoryBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.adapters.drawer.HistoryAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/7/13
 * @Desc
 */
public class HistoryActivity extends BaseActivity<ActivityHistoryBinding> {
    private int max = 0;
    private int viewAt = 0;

    @Override
    public ActivityHistoryBinding getViewBinding() {
        return ActivityHistoryBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        PaginationLoader<History, History.Data.Data> paginationLoader = new PaginationLoader<>(binding.content, new HistoryAdapter(context));
        paginationLoader.closeRefresh();
        paginationLoader.setGuide(history -> {
            History.Data.Cursor cursor = history.getData().getCursor();
            max = cursor.getMax();
            viewAt = cursor.getViewAt();

            return history.getData().getList();
        });
        paginationLoader.setUpdateInterface(loadType -> new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi().getUserHistory(max, viewAt)).obtain();
    }
}