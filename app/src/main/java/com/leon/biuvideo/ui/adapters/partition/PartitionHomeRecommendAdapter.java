package com.leon.biuvideo.ui.adapters.partition;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.partition.PartitionRecommend;
import com.leon.biuvideo.databinding.ItemPartitionHomeRecommendBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.utils.RecyclerViewLoader;

import java.util.List;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionHomeRecommendAdapter extends BaseViewBindingAdapter<String, ItemPartitionHomeRecommendBinding> {
    private final List<String> ridList;
    private final HttpApi httpApi;
    private OnMoveToPage onMoveToPage;

    public PartitionHomeRecommendAdapter(Context context, List<String> ridList) {
        super(context);
        this.ridList = ridList;
        this.httpApi = new RetrofitClient(BaseUrl.API, Map.of(HttpApi.Cookie, HttpApi.DEFAULT_COOKIE)).getHttpApi();

    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_partition_home_recommend;
    }

    @Override
    protected ItemPartitionHomeRecommendBinding getItemViewBinding() {
        return ItemPartitionHomeRecommendBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(String data, ItemPartitionHomeRecommendBinding binding, int position) {
        RecyclerViewLoader<PartitionRecommend, PartitionRecommend.Data.Archive> loader = new RecyclerViewLoader<>(binding.content,
                new PartitionHomeRecommendChildAdapter(context));
        loader.setGuide(partitionRecommend -> partitionRecommend.getData().getArchives());

        binding.refresh.setOnClickListener(v -> reload(loader, ridList.get(position)));
        binding.more.setOnClickListener(v -> {
            if (onMoveToPage != null) {
                onMoveToPage.onMove(ridList.get(position));
            }
        });
        binding.name.setText(data);

        reload(loader, ridList.get(position));
    }

    private void reload(RecyclerViewLoader<PartitionRecommend, PartitionRecommend.Data.Archive> loader, String rid) {
        loader.setObservable(httpApi.getPartitionRecommend(1, rid)).obtain(false);
    }

    public void setOnMoveToPage(OnMoveToPage onMoveToPage) {
        this.onMoveToPage = onMoveToPage;
    }

    public interface OnMoveToPage {
        void onMove(String rid);
    }
}
