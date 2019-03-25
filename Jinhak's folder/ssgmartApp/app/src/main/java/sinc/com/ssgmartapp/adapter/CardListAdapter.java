package sinc.com.ssgmartapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.dto.ProductListVO;
import sinc.com.ssgmartapp.helper.Util;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewHolder> {


    private Context context;
    private List<ProductListVO> list;

    public CardListAdapter(Context context, List<ProductListVO> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardlist_item, parent, false);
        Util.setGlobalFont(context, itemView);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductListVO productListVO = list.get(position);
        holder.name.setText(productListVO.getProductName());
        holder.normalPrice.setText((Integer.parseInt(String.valueOf(Math.round(productListVO.getPrice()))) + "원"));
        holder.valid.setText(productListVO.getValid());
        holder.stock.setText(Integer.parseInt(String.valueOf(Math.round(productListVO.getStock()))) + "개");
        holder.discountPrice.setText(Integer.parseInt(String.valueOf(Math.round(productListVO.getDiscountPrice()))) + "원");

        Picasso.with(context)
                .load(productListVO.getImage())
                .into(holder.thumbnail);

        //가격 꾸미기
        double normal = productListVO.getPrice();
        double discount = productListVO.getDiscountPrice();

        if (normal == discount) {
            holder.discountTxt.setText("정상가");
            holder.discountTxt.setTextColor(context.getResources().getColor(R.color.welcome_up_background));
            holder.discountPrice.setTextColor(context.getResources().getColor(R.color.welcome_up_background));
        } else {
            holder.discountTxt.setText("할인가");
            holder.discountLogo.setImageResource(R.drawable.sale_logo);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void sendBasket(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ProductListVO productListVO, int position) {
        list.remove(position);
        list.add(position, productListVO);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, normalPrice, discountPrice, stock, valid, discountTxt;
        public ImageView thumbnail, discountLogo;
        public RelativeLayout viewBackground, viewForeground;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            normalPrice = itemView.findViewById(R.id.normal_price);
            valid = itemView.findViewById(R.id.valid);
            stock = itemView.findViewById(R.id.stock);
            discountPrice = itemView.findViewById(R.id.discountPrice);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

            discountTxt = itemView.findViewById(R.id.discountPrice_txt);
            discountLogo = itemView.findViewById(R.id.discount_logo);
        }
    }
}
