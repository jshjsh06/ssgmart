package sinc.com.ssgmartapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.dto.MyProductListVO;
import sinc.com.ssgmartapp.dto.SharedProductVO;
import sinc.com.ssgmartapp.helper.Util;

public class SharedCardListAdapter extends RecyclerView.Adapter<SharedCardListAdapter.MyViewHolder> {


    private Context context;
    private List<SharedProductVO> list;
    private String user_Id;
    public SharedCardListAdapter(Context context, List<SharedProductVO> list, String user_Id) {
        this.context = context;
        this.list = list;
        this.user_Id=user_Id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shared_cardlist_item, parent, false);
        Util.setGlobalFont(context, itemView);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final SharedProductVO sharedProductVO = list.get(position);


/*        Picasso.with(context)
                .load(productListVO.getImage())
                .into(holder.thumbnail);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void sendBasket(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(SharedProductVO sharedProductVO, int position) {
        list.add(position, sharedProductVO);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView userName, totalPrice, arrivalTime, validity, stock;
        public ImageView userImg;
        public RelativeLayout viewBackground, viewForeground;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            totalPrice = itemView.findViewById(R.id.total_price);
            arrivalTime = itemView.findViewById(R.id.arrival_time);
            validity = itemView.findViewById(R.id.validity);
            stock = itemView.findViewById(R.id.stock);
            userImg = itemView.findViewById(R.id.user_img);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

        }
    }
}
