package com.sborzenko.materialtoolbarspinner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.widget.DrawableUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created at Magora Systems (http://magora-systems.com) on 14.07.16
 *
 * @author Stanislav S. Borzenko
 */
public class AndroidUtils {
    @ColorInt
    public static int getColor(Context context, @ColorRes int colorResId) {
        int color;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = context.getColor(colorResId);
        } else {
            //noinspection deprecation
            color = context.getResources().getColor(colorResId);
        }

        return color;
    }

    @ColorInt
    public static int getThemeColor(Context context, @AttrRes int resourceId) {
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data,
                new int[]{resourceId});
        try {
            return typedArray.getColor(0, 0);
        } finally {
            typedArray.recycle();
        }
    }

    public static Drawable getDrawable(Context context, int resourceId) {
        Drawable iconDrawable;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iconDrawable = context.getResources()
                    .getDrawable(resourceId, context.getTheme());
        } else {
            //noinspection deprecation
            iconDrawable = context.getResources().getDrawable(resourceId);
        }

        return iconDrawable;
    }

    public static Drawable getTintDrawableByColor(
            Context context,
            @DrawableRes int drawableResId,
            @ColorInt int color) {
        Drawable drawable = AndroidUtils.getDrawable(context, drawableResId);

        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
        }

        Drawable drawableCompat = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawableCompat, color);

        return drawableCompat;
    }

    public static Drawable getTintDrawableByColor2(
            Context context,
            @DrawableRes int drawableResId,
            @ColorInt int color) {
        Drawable drawable = AndroidUtils.getDrawable(context, drawableResId);

        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
        }

        PorterDuffColorFilter filter
                = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
        drawable.setColorFilter(filter);

        return drawable;
    }

    public static Drawable getTintDrawableByColorRes(
            Context context,
            @DrawableRes int drawableResId,
            @ColorRes int colorResId) {
        int color = AndroidUtils.getColor(context, colorResId);
        return getTintDrawableByColor(context, drawableResId, color);
    }

    public static Drawable getTintDrawableByThemeAttr(
            Context context,
            @DrawableRes int drawableResId,
            @AttrRes int attrResId) {
        int color = AndroidUtils.getThemeColor(context, attrResId);
        return getTintDrawableByColor(context, drawableResId, color);
    }

    public static Drawable getTintDrawableByThemeAttr2(
            Context context,
            @DrawableRes int drawableResId,
            @AttrRes int attrResId) {
        int color = AndroidUtils.getThemeColor(context, attrResId);
        return getTintDrawableByColor2(context, drawableResId, color);
    }

    public static int getStatusBarHeight(Context context) {
        int resource = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resource > 0) {
            return context.getResources().getDimensionPixelSize(resource);
        }

        return -1;
    }

    public static float pxToDp(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPx(final Context context, final int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public static void setMarginEnd(View view, int marginEnd) {
        ViewGroup.MarginLayoutParams layoutParams =
                (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        MarginLayoutParamsCompat.setMarginEnd(layoutParams, marginEnd);
        view.setLayoutParams(layoutParams);
    }
}
