package sinc.com.ssgmartapp.Helper;

import android.support.v7.widget.RecyclerView;

interface RecyclerItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction,int position);
}
