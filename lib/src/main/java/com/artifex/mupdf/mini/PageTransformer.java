package com.artifex.mupdf.mini;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Represents page swipe animation
 */
public class PageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 1f;
    private static final float MIN_ALPHA = 1f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();


        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            //float alpha = 1 - position + MIN_ALPHA;
            //view.setAlpha(alpha<1 ? alpha : 1);
            view.setAlpha(1f);

            view.setTranslationX(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
//            float alpha = 1 - position + MIN_ALPHA;
//            view.setAlpha(alpha<1 ? alpha : 1);
            
            view.setAlpha(1f);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
