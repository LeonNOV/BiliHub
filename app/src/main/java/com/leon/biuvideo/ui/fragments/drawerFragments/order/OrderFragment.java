package com.leon.biuvideo.ui.fragments.drawerFragments.order;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.publicBeans.user.UserOrder;
import com.leon.biuvideo.databinding.FragmentDrawerOrderBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.userAdapters.UserOrderAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

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
    private UserOrderAdapter userOrderAdapter;

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

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();

        userOrderAdapter = new UserOrderAdapter(context);
        loader = new PaginationLoader<>(binding.content, userOrderAdapter);
        loader.closeRefresh();
        loader.setGuide(userOrder -> userOrder.getData().getList());

        reload(0);
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    private void reload(int followStatus) {
        pageNum = 0;
        userOrderAdapter.removeAll();
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getUserOrder(type, followStatus, pageNum, mid)));
    }
}
