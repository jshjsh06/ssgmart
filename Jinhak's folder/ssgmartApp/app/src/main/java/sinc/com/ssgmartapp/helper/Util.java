package sinc.com.ssgmartapp.helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jo on 2017-06-04.
 */

public class Util {

    private Context _con;
    public View v;

    Util() {
    }

    public Util(Context _con) {
        this._con = _con;
    }

    public static void setGlobalFont(Context context, View view) {
        if (view != null) {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                int len = vg.getChildCount();
                for (int i = 0; i < len; i++) {
                    View v = vg.getChildAt(i);
                    if (v instanceof TextView) {
                        ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/BMDOHYEON.ttf"));
                    }
                    setGlobalFont(context, v);
                }
            }
        } else {
            // L.m("This is null");
        }
    }

    public void snackBar(String msg, View v) {
        Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setActionTextColor(Color.YELLOW).show();

    }

}
