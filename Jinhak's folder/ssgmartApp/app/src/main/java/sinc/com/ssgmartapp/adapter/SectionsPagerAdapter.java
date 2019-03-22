package sinc.com.ssgmartapp.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sinc.com.ssgmartapp.fragment.BasketSSG_Fragment;
import sinc.com.ssgmartapp.fragment.BuySSG_Fragment;
import sinc.com.ssgmartapp.fragment.SharedBasketSSG_Fragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new BuySSG_Fragment();
            case 1:
                return new BasketSSG_Fragment();
            case 2:
                return new SharedBasketSSG_Fragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    //Swipe Page 개수
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}