package com.leon.biuvideo.ui.fragments.drawerFragments.partition;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.partition.PartitionData;
import com.leon.biuvideo.beans.partition.PartitionTag;
import com.leon.biuvideo.databinding.PageFilterRefreshBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.partition.PartitionDataAdapter;
import com.leon.biuvideo.utils.PaginationLoader;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.filter.FilterAdapter;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionFragment extends BaseLazyFragment<PageFilterRefreshBinding> {
    private final List<PartitionTag> partitionTags;
    private final String id;

    private HttpApi httpApi;
    private int pageNum = 0;
    private PaginationLoader<PartitionData, PartitionData.Result> loader;

    public PartitionFragment(List<PartitionTag> partitionTags, String id) {
        this.partitionTags = partitionTags;
        this.id = id;
    }

    @Override
    public PageFilterRefreshBinding getViewBinding() {
        return PageFilterRefreshBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        partitionTags.add(0, new PartitionTag("首页", "0"));
        FilterAdapter<PartitionTag> filterAdapter = new FilterAdapter<>(context);
        filterAdapter.setOnFilterCallback(new FilterAdapter.OnFilterCallback<>() {
            @Override
            public void onReload(PartitionTag partitionTag) {
                reload(partitionTag.getTitle());
            }

            @Override
            public String onGuide(PartitionTag partitionTag) {
                return partitionTag.getTitle();
            }
        });
        filterAdapter.appendHead(partitionTags);
        binding.filter.setAdapter(filterAdapter);

        httpApi = new RetrofitClient(BaseUrl.SEARCH).getHttpApi();

        loader = new PaginationLoader<>(binding.content, new PartitionDataAdapter(context), new GridLayoutManager(context, 2));
        loader.setGuide(PartitionData::getResult);
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    private void reload(String keyword) {
        pageNum = 0;
        long end = System.currentTimeMillis()/1000;
        loader.setUpdateInterface(loadType -> httpApi.getPartitionData(id, keyword, ++pageNum, ValueUtils.generateTime(end - 604800, "yyyyMMdd", false), ValueUtils.generateTime(end, "yyyyMMdd", true)));
    }
}
