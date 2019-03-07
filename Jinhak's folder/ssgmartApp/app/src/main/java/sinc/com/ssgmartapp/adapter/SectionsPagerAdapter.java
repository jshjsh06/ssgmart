package sinc.com.ssgmartapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import sinc.com.ssgmartapp.fragment.BasketSSG_Fragment;
import sinc.com.ssgmartapp.fragment.BuySSG_Fragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
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
            default:
                break;
        }
        return null;
    }

    //Swipe Page 개수
    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}