package sinc.com.ssgmartapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import sinc.com.ssgmartapp.R;

public class SlideAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;

    public SlideAdapter(Context context){
        this.context=context;
    }

    //Array
    private int[] list_images={

            R.drawable.img_device_1,
            R.drawable.img_device_4,
            R.drawable.img_device_ssgmart
    };
    private int[] list_textImages={
            R.drawable.img_text_1,
            R.drawable.img_text_4,
            R.drawable.img_text_ssgmart
    };


    private int[] list_color={

            Color.rgb(193, 66, 44),
            Color.rgb(193, 172, 44),
            Color.rgb(193, 66, 44)

    };

    @Override
    public int getCount() {
        return list_images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view== obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        LinearLayout linearLayout = view.findViewById(R.id.slidelinearlayout);
        ImageView img = view.findViewById(R.id.slideimg);
        ImageView txtImg = view.findViewById(R.id.slideTextImage);

        img.setImageResource(list_images[position]);
        txtImg.setImageResource(list_textImages[position]);
        linearLayout.setBackgroundColor(list_color[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}