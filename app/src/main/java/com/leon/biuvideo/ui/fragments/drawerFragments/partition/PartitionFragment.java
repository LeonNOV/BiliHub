package com.leon.biuvideo.ui.fragments.drawerFragments.partition;

import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.partition.PartitionData;
import com.leon.biuvideo.beans.partition.PartitionTag;
import com.leon.biuvideo.databinding.FragmentPartitionBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.PartitionFilterAdapter;
import com.leon.biuvideo.ui.adapters.partition.PartitionDataAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionFragment extends BaseLazyFragment<FragmentPartitionBinding> {
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
    public FragmentPartitionBinding getViewBinding() {
        return FragmentPartitionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        partitionTags.add(0, new PartitionTag("首页", "0"));

        PartitionFilterAdapter adapter = new PartitionFilterAdapter(context);
        adapter.appendHead(partitionTags);
        adapter.setOnRefreshData(data -> {
            reload(data.getTitle());
        });

        binding.filter.subTab.setAdapter(adapter);
        binding.filter.subTab.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        binding.filter.subTab.setMotionEventSplittingEnabled(false);
        binding.filter.subTab.setNestedScrollingEnabled(false);
        binding.filter.subTab.setHasFixedSize(true);

        binding.filter.more.setOnClickListener(v -> {
            Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();
        });

        loader = new PaginationLoader<>(binding.content, new PartitionDataAdapter(context));

        httpApi = new RetrofitClient(BaseUrl.SEARCH).getHttpApi();
        loader.closeRefresh();
        loader.setGuide(PartitionData::getResult);
        loader.setObservable(httpApi.getPartitionData(id, null, ++pageNum, "20220708", "20220715"));
        loader.setLayoutManager(new GridLayoutManager(context, 2));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    // todo 数据存留还未解决
    private void reload(String keyword) {
        pageNum = 0;
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getPartitionData(id, keyword, ++pageNum, "20220708", "20220715")));
    }
}
