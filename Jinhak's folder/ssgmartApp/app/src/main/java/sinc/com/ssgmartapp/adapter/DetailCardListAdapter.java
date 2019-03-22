package sinc.com.ssgmartapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.dto.MyProductListVO;
import sinc.com.ssgmartapp.helper.Util;

public class DetailCardListAdapter extends RecyclerView.Adapter<DetailCardListAdapter.MyViewHolder> {


    private Context context;
    private List<MyProductListVO> list;
    int mCheckedPostion = -1;
    private boolean isChecked = false;

    public DetailCardListAdapter(Context context, List<MyProductListVO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_list_item, parent, false);
        Util.setGlobalFont(context, itemView);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final MyProductListVO productListVO = list.get(position);

        holder.name.setText(productListVO.getProductName());
        holder.valid.setText(productListVO.getValid());

        int cnt = productListVO.getCnt();
        int discountPrice = Integer.parseInt(String.valueOf(Math.round(productListVO.getDiscountPrice())));
        String priceStock = String.valueOf(discountPrice * cnt);
        String cntString = String.valueOf(cnt);
        String ps = priceStock + "원 / " + cntString + "개";
        holder.priceStock.setText(ps);


//        String data = productListVO.getImage();
//        byte[] bytePlainOrg = Base64.decode(data, 0);
//        //byte[] 데이터 stream 데이터로 변환 후 bitmapFactory로 이미지 생성
//        ByteArrayInputStream inStream = new ByteArrayInputStream(bytePlainOrg);
//        Bitmap bm = BitmapFactory.decodeStream(inStream);
//        holder.thumbnail.setImageBitmap(bm);

        Picasso.with(context)
                .load(productListVO.getImage())
                .into(holder.thumbnail);

        holder.checkBox.setSelected(false);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.checkBox.setSelected(true);
                    productListVO.setCheck(true);
                    holder.viewForeground.setBackgroundColor(context.getResources().getColor(R.color.dot_inactive_screen));
                    Log.d("productlist",String.valueOf(productListVO.isCheck()));
                } else {
                    holder.checkBox.setSelected(false);
                    productListVO.setCheck(false);
                    holder.viewForeground.setBackgroundColor(context.getResources().getColor(R.color.trans));
                    Log.d("productlist",String.valueOf(productListVO.isCheck()));
                }
            }
        });


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

        ImageView thumbnail;
        TextView name, valid, priceStock;
        RelativeLayout viewForeground;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            name = itemView.findViewById(R.id.name);
            valid = itemView.findViewById(R.id.valid);
            priceStock = itemView.findViewById(R.id.price_stock);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            checkBox = itemView.findViewById(R.id.checkbox);

        }
    }
}
