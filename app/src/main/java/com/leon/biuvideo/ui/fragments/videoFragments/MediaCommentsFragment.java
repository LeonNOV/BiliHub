package com.leon.biuvideo.ui.fragments.videoFragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.publicBeans.resources.Reply;
import com.leon.biuvideo.databinding.FragmentMediaCommentsBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.ReplyType;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.model.VideoPlayerModel;
import com.leon.biuvideo.ui.adapters.ReplyAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

/**
 * @Author Leon
 * @Time 2022/08/04
 * @Desc
 */
public class MediaCommentsFragment extends BaseLazyFragment<FragmentMediaCommentsBinding> {
    private String aid;

    private HttpApi httpApi;
    private ReplyAdapter<Reply.Data.Reply> adapter;
    private PaginationLoader<Reply, Reply.Data.Reply> loader;

    private VideoPlayerModel videoPlayerModel;
    private Observer<String> videoRecommendObserver;

    private int next = 0;
    private int mode = 3;

    public MediaCommentsFragment(String aid) {
        this.aid = aid;
    }

    @Override
    public FragmentMediaCommentsBinding getViewBinding() {
        return FragmentMediaCommentsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoPlayerModel.class);

        videoRecommendObserver = bvid -> {
            aid = ValueUtils.bv2av(bvid);
            mode = 3;

            binding.mode.setText(R.string.ReplyModeHot);
            binding.refresh.setText(R.string.ReplyHot);

            reload();
        };
        videoPlayerModel.getVideoRecommend().observeForever(videoRecommendObserver);

        binding.refresh.setOnClickListener(v -> {
            if (mode == 3) {
                binding.mode.setText(R.string.ReplyModeNew);
                binding.refresh.setText(R.string.ReplyNew);

                mode = 2;
            } else {
                binding.mode.setText(R.string.ReplyModeHot);
                binding.refresh.setText(R.string.ReplyHot);

                mode = 3;
            }

            reload();
        });

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        adapter = new ReplyAdapter<>(context, false);
        loader = new PaginationLoader<>(binding.content, adapter);
        loader.setGuide(reply -> {
            next = reply.getData().getCursor().getNext();
            return reply.getData().getReplies();
        });
        loader.setUpdateInterface(loadType -> httpApi.getReply(aid, mode, next, ReplyType.Video));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    private void reload () {
        if (adapter != null) {
            next = 0;
            adapter.removeAll();
            loader.setUpdateInterface(loadType -> httpApi.getReply(aid, mode, next, ReplyType.Video)).obtain();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        videoPlayerModel.getVideoRecommend().removeObserver(videoRecommendObserver);
    }
}
