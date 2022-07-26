package com.leon.biuvideo.ui.fragments.drawerFragments.order;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.publicBeans.user.UserOrder;
import com.leon.biuvideo.databinding.FragmentDrawerOrderBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.adapters.user.UserOrderAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class OrderFragment extends BaseLazyFragment<FragmentDrawerOrderBinding> {
    private final int type;
    private final String mid;

    private final String[] FILTER_ITEMS = {"全部", "想看", "在看", "看过"};
    private int pageNum = 0;

    private HttpApi httpApi;
    private PaginationLoader<UserOrder, UserOrder.Data.Order> loader;
    private UserOrderAdapter adapter;

    public OrderFragment(int type, String mid) {
        this.type = type;
        this.mid = mid;
    }

    @Override
    public FragmentDrawerOrderBinding getViewBinding() {
        return FragmentDrawerOrderBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.filter.setFilterItem(this.FILTER_ITEMS).setOnSelected(this::reload);

        adapter = new UserOrderAdapter(context);
        httpApi = new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi();
        loader = new PaginationLoader<>(binding.content, adapter);
        loader.closeRefresh();
        loader.setGuide(userOrder -> userOrder.getData().getList());
        reload(0);
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    //todo 筛选功能待完善
    private void reload(int followStatus) {
        pageNum = 0;
        adapter.removeAll();
        loader.setUpdateInterface(loadType -> httpApi.getUserOrder(type, followStatus, ++pageNum, mid)).obtain();
    }
}
