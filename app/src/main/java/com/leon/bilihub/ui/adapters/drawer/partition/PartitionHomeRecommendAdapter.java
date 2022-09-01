package com.leon.bilihub.ui.adapters.drawer.partition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.partition.PartitionRecommend;
import com.leon.bilihub.databinding.ItemPartitionHomeRecommendBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.widget.loader.RecyclerViewLoader;
import com.leon.bilihub.utils.ViewUtils;

import java.util.List;
import java.util.Random;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionHomeRecommendAdapter extends ViewBindingAdapter<String, ItemPartitionHomeRecommendBinding> {
    private final List<Integer> ridList;
    private final HttpApi httpApi;
    private OnMoveToPage onMoveToPage;

    /**
     * pageNum不用自增
     */
    Random pageNum = new Random();

    public PartitionHomeRecommendAdapter(Context context, List<Integer> ridList) {
        super(context);
        this.ridList = ridList;
        this.httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
    }

    @Override
    protected ItemPartitionHomeRecommendBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPartitionHomeRecommendBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_partition_home_recommend, parent, false));
    }

    @Override
    protected void onBindViewHolder(String data, ItemPartitionHomeRecommendBinding binding, int position) {
        RecyclerViewLoader<PartitionRecommend, PartitionRecommend.Data.Archive> loader = new RecyclerViewLoader<>(binding.content,
                new PartitionHomeRecommendChildAdapter(context), ViewUtils::listInitializer);
        loader.setGuide(partitionRecommend -> {
            if (partitionRecommend.getCode() == -404) {
                return null;
            } else {
                return partitionRecommend.getData().getArchives();
            }
        });

        binding.refresh.setOnClickListener(v -> reload(loader, ridList.get(position)));
        binding.more.setOnClickListener(v -> {
            if (onMoveToPage != null) {
                onMoveToPage.onMove(position);
            }
        });
        binding.name.setText(data);

        reload(loader, ridList.get(position));
    }

    private void reload(RecyclerViewLoader<PartitionRecommend, PartitionRecommend.Data.Archive> loader, int rid) {
        loader.setObservable(httpApi.getPartitionRecommend(pageNum.nextInt(5) + 1, rid)).obtain(true);
    }

    public void setOnMoveToPage(OnMoveToPage onMoveToPage) {
        this.onMoveToPage = onMoveToPage;
    }

    public interface OnMoveToPage {
        void onMove(Integer rid);
    }
}
