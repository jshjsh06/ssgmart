package sinc.com.ssgmartapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
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
import sinc.com.ssgmartapp.helper.Util;

public class DeleteCardListAdapter extends RecyclerView.Adapter<DeleteCardListAdapter.MyViewHolder> {


    private Context context;
    private List<MyProductListVO> list;
    private String user_Id;
    public DeleteCardListAdapter(Context context, List<MyProductListVO> list,String user_Id) {
        this.context = context;
        this.list = list;
        this.user_Id=user_Id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delete_cardlist_item, parent, false);
        Util.setGlobalFont(context, itemView);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final MyProductListVO productListVO = list.get(position);
        holder.name.setText(productListVO.getProductName());
        holder.normalPrice.setText((Integer.parseInt(String.valueOf(Math.round(productListVO.getPrice()))) + "원"));
        holder.valid.setText(productListVO.getValid());
        holder.stock.setText(Integer.parseInt(String.valueOf(Math.round(productListVO.getStock()))) + "개");
        holder.discountPrice.setText(Integer.parseInt(String.valueOf(Math.round(productListVO.getDiscountPrice()))) + "원");

        holder.picker.setMinValue(0);
        holder.picker.setMaxValue(Integer.parseInt(String.valueOf(Math.round(productListVO.getStock()))));
        holder.picker.setValue(productListVO.getCnt());

        String data = productListVO.getImage();
        byte[] bytePlainOrg = Base64.decode(data, 0);
        //byte[] 데이터 stream 데이터로 변환 후 bitmapFactory로 이미지 생성
        ByteArrayInputStream inStream = new ByteArrayInputStream(bytePlainOrg);
        Bitmap bm = BitmapFactory.decodeStream(inStream);
        holder.thumbnail.setImageBitmap(bm);


        //가격 꾸미기
        double normal = productListVO.getPrice();
        double discount = productListVO.getDiscountPrice();

        if(normal==discount){
            holder.discountTxt.setText("정상가");
            holder.discountTxt.setTextColor(context.getResources().getColor(R.color.welcome_up_background));
            holder.discountPrice.setTextColor(context.getResources().getColor(R.color.welcome_up_background));
        }else{
            holder.discountTxt.setText("할인가");
        }

        productListVO.setUser_Id(user_Id);
        holder.picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                productListVO.setCnt(newVal);
            }
        });

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

    public void restoreItem(MyProductListVO productListVO, int position) {
        list.add(position, productListVO);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, normalPrice, discountPrice, stock, valid,discountTxt;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;
        public NumberPicker picker;
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

            picker = itemView.findViewById(R.id.number_picker);
            discountTxt = itemView.findViewById(R.id.discountPrice_txt);

            picker.setWrapSelectorWheel(true);



        }
    }
}
