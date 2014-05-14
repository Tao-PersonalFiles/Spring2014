package com.redcarddev.kickshot_real;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by Jordan on 3/3/14.
 */
public class GifWebView extends WebView {

    public GifWebView(Context context, String path) {
        super(context);

        loadUrl(path);
    }
}
