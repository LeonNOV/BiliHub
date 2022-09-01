package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.leon.bilihub.R;
import com.leon.bilihub.databinding.DialogAboutBinding;
import com.leon.bilihub.utils.ViewUtils;
import com.leon.bilihub.utils.donation.DonationHelper;

import org.jetbrains.annotations.NotNull;

/**
 * @Author Leon
 * @Time 2021/5/16
 * @Desc 关于弹窗
 */
public class AboutDialog extends AlertDialog {
    private final static String GITEE = "https://gitee.com/leon_xf/bili-hub";
    private final static String GITHUB = "https://github.com/LeonNOV/BiliHub";
    private DialogAboutBinding binding;

    public AboutDialog(@NotNull Context context) {
        super(context, R.style.FullScreenDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImmersionBar.with(ViewUtils.scanForActivity(getContext()), this).hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();

        binding = DialogAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setWindowAnimations(R.style.paning_anim_style);

        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);

        setCanceledOnTouchOutside(false);

        initView();
    }

    private void initView() {
        String versionName = "Undefined";

        try {
            versionName = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        binding.aboutAppVersion.setText(versionName);
        binding.gitee.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(GITEE));
            getContext().startActivity(intent);
        });

        binding.github.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(GITHUB));
            getContext().startActivity(intent);
        });

        binding.group.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26jump_from%3Dwebapi%26k%3D" + "f7iJCgrDQnvPELv8QrJizOtBYTymrh5c"));
            try {
                getContext().startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getContext(), "调起QQ失败，请检查QQ是否为最新版或是否已安装QQ", Toast.LENGTH_SHORT).show();
            }
        });

        binding.weChatPay.setOnClickListener(v -> DonationHelper.WeChat(getContext()));
        binding.aliPay.setOnClickListener(v -> DonationHelper.Ali(getContext()));

        binding.close.setOnClickListener(v -> dismiss());
    }
}
