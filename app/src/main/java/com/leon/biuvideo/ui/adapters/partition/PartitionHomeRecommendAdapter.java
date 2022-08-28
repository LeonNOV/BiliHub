package com.leon.biuvideo.ui.adapters.partition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.partition.PartitionRecommend;
import com.leon.biuvideo.databinding.ItemPartitionHomeRecommendBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.widget.loader.RecyclerViewLoader;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;
import java.util.Random;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionHomeRecommendAdapter extends BaseViewBindingAdapter<String, ItemPartitionHomeRecommendBinding> {
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
