package com.leon.bilihub.ui.fragments.videoFragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.publicBeans.resources.Reply;
import com.leon.bilihub.databinding.FragmentMediaCommentsBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.ReplyType;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.model.VideoPlayerModel;
import com.leon.bilihub.ui.adapters.ReplyAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

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
            if (bvid != null) {
                aid = ValueUtils.bv2av(bvid);
                mode = 3;

                binding.mode.setText(R.string.reply_mode_hot);
                binding.refresh.setText(R.string.reply_hot);

                reload();
            } else {
                if (adapter != null) {
                    adapter.removeAll();
                }
            }
        };
        videoPlayerModel.getVideoRecommend().observeForever(videoRecommendObserver);

        binding.refresh.setOnClickListener(v -> {
            if (mode == 3) {
                binding.mode.setText(R.string.reply_mode_new);
                binding.refresh.setText(R.string.reply_new);

                mode = 2;
            } else {
                binding.mode.setText(R.string.reply_mode_hot);
                binding.refresh.setText(R.string.reply_hot);

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
        if (aid != null) {
            loader.setUpdateInterface(loadType -> httpApi.getReply(aid, mode, next, ReplyType.Video));
        }
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
