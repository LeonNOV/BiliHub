package com.leon.bilihub.ui.activities.drawerFunction;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.account.History;
import com.leon.bilihub.databinding.ActivityHistoryBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.HistoryAdapter;
import com.leon.bilihub.ui.dialogs.TipDialog;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.PreferenceUtils;

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
        if (PreferenceUtils.getLoginStatus(context)) {
            HttpApi httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
            PaginationLoader<History, History.Data.Data> loader = new PaginationLoader<>(binding.content, new HistoryAdapter(context));
            loader.setGuide(history -> {
                History.Data.Cursor cursor = history.getData().getCursor();
                max = cursor.getMax();
                viewAt = cursor.getViewAt();

                return history.getData().getList();
            });
            loader.setUpdateInterface(loadType -> httpApi.getUserHistory(max, viewAt)).obtain();
        } else {
            TipDialog.showLoginTipDialog(context);
        }
    }
}