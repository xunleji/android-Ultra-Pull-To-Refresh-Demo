package com.example.android_ultra_pull_to_refresh_demo.ui.classic;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import android.view.View;

import com.example.android_ultra_pull_to_refresh_demo.R;
import com.example.android_ultra_pull_to_refresh_demo.ui.Utils;

public class HideHeader extends WithTextViewInFrameLayoutFragment {

    @Override
    protected void setupViews(final PtrClassicFrameLayout ptrFrame) {
        setHeaderTitle(R.string.ptr_demo_block_hide_header);
        ptrFrame.setKeepHeaderWhenRefresh(false);

        final View loading = Utils.createSimpleLoadingTip(getContext());
        mTitleHeaderBar.getRightViewContainer().addView(loading);

        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loading.setVisibility(View.VISIBLE);
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.setVisibility(View.INVISIBLE);
                        ptrFrame.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
    }
}