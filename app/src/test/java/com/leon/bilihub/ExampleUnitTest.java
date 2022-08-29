package com.leon.bilihub;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBindings;

import com.leon.bilihub.databinding.ItemChannelBinding;

import org.junit.Test;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc
 */
public class ExampleUnitTest {
    @Test
    public void test () {

    }

    public static ItemChannelBinding bind(@NonNull View rootView) {
        // The body of this method is generated in a way you would not otherwise write.
        // This is done to optimize the compiled bytecode for size and performance.
        int id;
        missingId: {
            id = R.id.container;
            LinearLayoutCompat container = ViewBindings.findChildViewById(rootView, id);
            if (container == null) {
                break missingId;
            }

            id = R.id.extraA;
            AppCompatTextView extraA = ViewBindings.findChildViewById(rootView, id);
            if (extraA == null) {
                break missingId;
            }

            id = R.id.extraB;
            AppCompatTextView extraB = ViewBindings.findChildViewById(rootView, id);
            if (extraB == null) {
                break missingId;
            }

            id = R.id.extraContainer;
            LinearLayoutCompat extraContainer = ViewBindings.findChildViewById(rootView, id);
            if (extraContainer == null) {
                break missingId;
            }

            id = R.id.face;
            CircleImageView face = ViewBindings.findChildViewById(rootView, id);
            if (face == null) {
                break missingId;
            }

            id = R.id.name;
            AppCompatTextView name = ViewBindings.findChildViewById(rootView, id);
            if (name == null) {
                break missingId;
            }

            id = R.id.subscribe;
            AppCompatTextView subscribe = ViewBindings.findChildViewById(rootView, id);
            if (subscribe == null) {
                break missingId;
            }

            return null;
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
