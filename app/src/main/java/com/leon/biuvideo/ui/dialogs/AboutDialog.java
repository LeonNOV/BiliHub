package com.leon.biuvideo.ui.dialogs;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.leon.biuvideo.R;
import com.leon.biuvideo.databinding.DialogAboutBinding;

import org.jetbrains.annotations.NotNull;

/**
 * @Author Leon
 * @Time 2021/5/16
 * @Desc 关于弹窗
 */
public class AboutDialog extends AlertDialog {
    private final static String GITEE = "https://gitee.com/leon_xf/biu-video";
    private final static String GITHUB = "https://github.com/LeonNOV/BiuVideo";
    private com.leon.biuvideo.databinding.DialogAboutBinding binding;

    public AboutDialog(@NotNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DialogAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setWindowAnimations(R.style.paning_anim_style);
        window.setBackgroundDrawableResource(android.R.color.transparent);

        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);

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
        binding.versionContainer.setOnClickListener(v -> {
//            OpenScreenDialog openScreenDialog = new OpenScreenDialog(getContext());
//            openScreenDialog.show();
            dismiss();
        });

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

        binding.close.setOnClickListener(v -> dismiss());
    }
}
