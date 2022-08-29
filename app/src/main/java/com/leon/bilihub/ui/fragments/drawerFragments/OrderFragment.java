package com.leon.bilihub.ui.fragments.drawerFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.publicBeans.user.UserOrder;
import com.leon.bilihub.databinding.PageFilterRefreshBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.user.UserOrderAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.filter.FilterAdapter;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class OrderFragment extends BaseLazyFragment<PageFilterRefreshBinding> {
    private final int type;
    private final String mid;

    private final List<String> filter_items = List.of("全部", "想看", "在看", "看过");
    private int pageNum = 0;
    private int followStatus = 0;

    private HttpApi httpApi;
    private PaginationLoader<UserOrder, UserOrder.Data.Order> loader;
    private UserOrderAdapter adapter;

    public OrderFragment(int type, String mid) {
        this.type = type;
        this.mid = mid;
    }

    @Override
    public PageFilterRefreshBinding getViewBinding() {
        return PageFilterRefreshBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        FilterAdapter<String> filterAdapter = new FilterAdapter<>(context);
        filterAdapter.setOnFilterCallback(new FilterAdapter.OnFilterCallback<>() {
            @Override
            public void onReload(String s) {
                followStatus = filter_items.indexOf(s);
                reload();
            }

            @Override
            public String onGuide(String s) {
                return s;
            }
        });
        filterAdapter.appendHead(filter_items);
        binding.filter.setAdapter(filterAdapter);

        adapter = new UserOrderAdapter(context);
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new PaginationLoader<>(binding.content, adapter);
        loader.setGuide(userOrder -> userOrder.getData().getList());
        loader.setUpdateInterface(loadType -> httpApi.getUserOrder(type, followStatus, ++pageNum, mid));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    private void reload() {
        pageNum = 0;
        adapter.removeAll();
        loader.setUpdateInterface(loadType -> httpApi.getUserOrder(type, followStatus, ++pageNum, mid)).obtain();
    }
}
