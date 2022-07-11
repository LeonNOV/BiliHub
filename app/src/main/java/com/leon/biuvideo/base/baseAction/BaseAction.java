package com.leon.biuvideo.base.baseAction;

import android.os.Parcelable;

import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.ui.fragments.DataListFragment;
import com.leon.biuvideo.utils.PaginationLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/06/18
 * @Desc
 */
public abstract class BaseAction implements DataListFragment.ExternalInterface {
    protected static final String TAG = "BaseAction";
    public static final String ACTION = "action";

    /**
     * create a bundle
     *
     * @return Bundle
     */
    public abstract ActionData createActionData();

//    public static class ActionDataRequester<T extends Parcelable, B extends Parcelable> {
//        private final HttpApi httpApi;
//        private final PaginationLoader<T, B> paginationLoader;
//
//        public ActionDataRequester(HttpApi httpApi, PaginationLoader<T, B> paginationLoader) {
//            this.httpApi = httpApi;
//            this.paginationLoader = paginationLoader;
//        }
//
//        public HttpApi getHttpApi() {
//            return httpApi;
//        }
//
//        public PaginationLoader<T, B> getPaginationLoader() {
//            return paginationLoader;
//        }
//    }
}
