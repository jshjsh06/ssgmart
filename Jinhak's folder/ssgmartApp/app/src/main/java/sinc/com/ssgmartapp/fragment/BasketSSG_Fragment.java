package sinc.com.ssgmartapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sinc.com.ssgmartapp.R;

/**
 * 장바구니 Fragment
 */
public class BasketSSG_Fragment extends Fragment {

    View mFragmentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("BasketSSG_Fragment.onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_basket_ssg,container,false);
        return mFragmentView;
    }
}
