package sinc.com.ssgmartapp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jxl.biff.drawing.Button;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.adapter.DeleteCardListAdapter;
import sinc.com.ssgmartapp.adapter.DetailCardListAdapter;
import sinc.com.ssgmartapp.adapter.SharedCardListAdapter;
import sinc.com.ssgmartapp.dto.MyProductListVO;
import sinc.com.ssgmartapp.dto.ProductListVO;
import sinc.com.ssgmartapp.dto.SharedProductVO;
import sinc.com.ssgmartapp.helper.Common;
import sinc.com.ssgmartapp.helper.RecyclerDeleteItemTouchHelper;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelperListener;
import sinc.com.ssgmartapp.helper.RecyclerSharedBasketTouchHelper;
import sinc.com.ssgmartapp.remote.RequestService;

/**
 * 장바구니 Fragment
 */
public class SharedBasketSSG_Fragment extends Fragment implements RecyclerItemTouchHelperListener, ValueEventListener {

    View mFragmentView;

    private RecyclerView recyclerView;
    private List<SharedProductVO> list;
    private SharedCardListAdapter adapter;
    RequestService mService;
    private int delete_index;
    private SharedProductVO delete_item;
    private List<MyProductListVO> detail_list;

    private BottomSheetDialog mBottomSheetDialog;

    private DetailCardListAdapter detailCardListAdapter;
    private String send_id;
    private int basket;

    LayoutInflater inflate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        detail_list = new ArrayList<>();
        mService = Common.getUrlService();
        inflater = LayoutInflater.from(getContext());

        mFragmentView = inflater.inflate(R.layout.fragment_shared_basket_ssg, container, false);

        recyclerView = mFragmentView.findViewById(R.id.basket_ssg_shared_recycler_view);
        adapter = new SharedCardListAdapter(getContext(), list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack
                = new RecyclerSharedBasketTouchHelper(0, ItemTouchHelper.LEFT, this);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBackRight
                = new RecyclerSharedBasketTouchHelper(0, ItemTouchHelper.RIGHT, this);

        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
        new ItemTouchHelper(itemTouchHelperCallBackRight).attachToRecyclerView(recyclerView);

        addSharedItemByMyId(getUserEmail());


        return mFragmentView;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
//        dataSnapshot.getValue();
//        list.clear();
//        for (DataSnapshot fileSnapshot : dataSnapshot.child("users").child(getUid()).child("myBasket").getChildren()) {
//            if (fileSnapshot != null) {
//                ProductListVO item = fileSnapshot.getValue(ProductListVO.class);
//                list.add(item);
//            }
//            else return;
//        }
//        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        Log.d("direction", String.valueOf(direction));
        //오른쪽으로 밀면 8(삭제) 왼쪽으로 밀면 4(상세보기)

        if (viewHolder instanceof SharedCardListAdapter.MyViewHolder) {

            String name = list.get(viewHolder.getAdapterPosition()).getSend_Id();
            Log.d("send_id", name);

            final SharedProductVO item = list.get(viewHolder.getAdapterPosition());
            final int index = viewHolder.getAdapterPosition();
            if (direction == 8) {
                deleteDialog(item, index);
            } else if (direction == 4) {
                detailDialog(item, index);
            }


        }
    }

    /**
     * 19/03/19 (위진학)
     * 공유 장바구니 삭제 다이얼로그
     */
    private void deleteDialog(final SharedProductVO item, int index) {

        delete_index = index;
        delete_item = item;

        ImageView imageView = new ImageView(getContext());

        Picasso.with(getContext())
                .load(item.getUser_image())
                .into(imageView);

        new AlertDialog.Builder(getContext())
                .setIcon(imageView.getDrawable())
                .setTitle(item.getUserName() + "님이 공유한 장바구니")
                .setMessage("정말 삭제하시겠습니까?")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar snackbar = Snackbar.make(mFragmentView, item.getUserName() + "님의 장바구니를 삭제했어요!!", Snackbar.LENGTH_SHORT);
                        snackbar.setActionTextColor(Color.YELLOW);
                        snackbar.show();

                        deleteSharedCartById(delete_item);
                        adapter.sendBasket(delete_index);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "너는 취소 했어!", Toast.LENGTH_SHORT).show();
                        adapter.sendBasket(delete_index);
                        adapter.restoreItem(delete_item, delete_index);
                    }
                }).show();

    }

    /**
     * 19/03/19 (위진학)
     * 공유 장바구니 자세히 보기 다이얼로그
     */
    private void detailDialog(SharedProductVO item, int index) {
        adapter.sendBasket(index);
        adapter.restoreItem(item, index);

        send_id = item.getSend_Id();
        basket = item.getBasket();

        showSharedListByYourId(item);

        detailCardListAdapter = new DetailCardListAdapter(getContext(), detail_list);

        mBottomSheetDialog = new BottomSheetDialog(getContext());
        View v = getLayoutInflater().inflate(R.layout.bottom_dialog, null);
        RecyclerView recyclerView = v.findViewById(R.id.detail_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(detailCardListAdapter);

        mBottomSheetDialog.setContentView(v);
        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });

        mBottomSheetDialog.findViewById(R.id.create_shared_qr_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueDetailList(detail_list,"Q");
                Toast.makeText(getContext(), "QR 코드 생성!", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.findViewById(R.id.get_my_basket_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueDetailList(detail_list,"B");
                Toast.makeText(getContext(), "내 장바구니에 담겼습니다.", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();

                adapter.sendBasket(delete_index);
            }
        });
    }


    /**
     * 19/03/20 (위진학)
     * 내가 체크한 상품들 맞는지 검사하기
     */
    private void checkTrueDetailList(List<MyProductListVO> detail_list,String check) {

        Log.d("product", String.valueOf(detail_list.size()));
        List<MyProductListVO> myProductListVOList = new ArrayList<>();

        for (int i = 0; i < detail_list.size(); i++) {
            if (detail_list.get(i).isCheck()) {
                detail_list.get(i).setSend_Id(send_id);
                detail_list.get(i).setUser_Id(getUserEmail());
                detail_list.get(i).setBasket(basket);
                myProductListVOList.add(detail_list.get(i));
            }
            detail_list.get(i).setCheck(false);
        }
        if (myProductListVOList.size() == 0) {
            Toast.makeText(getContext(), "선택한 상품이 없어요", Toast.LENGTH_SHORT).show();
        } else if(check.equals("B")) {
            setSharedBasketInMyBasket(myProductListVOList);
        } else if(check.equals("Q")){
            createQRCode(myProductListVOList);
        }
    }


    /**
     * 19/03/20 (위진학)
     * 공유된 장바구니 펼쳐서 나의 장바구니에 담기
     */
    private void setSharedBasketInMyBasket(List<MyProductListVO> myProductListVOList) {

        mService.showSharedListByYourId(myProductListVOList)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

    }

    /**
     * 19/03/20 (위진학)
     * 상대방이 보내준 장바구니에서 내가 체크한 상품들 QR코드 생성하기
     */
    private void createQRCode(List<MyProductListVO> myProductListVOList) {

        inflate = LayoutInflater.from(getContext());
        View v = inflate.inflate(R.layout.qr_dialog, null);
        ImageView imageView = v.findViewById(R.id.qr_Dialog_imageView);

        JSONArray array = new JSONArray();
        array.put(myProductListVOList);

        //QR코드에 들어갈 내용 넣어주기
        JSONObject obj = new JSONObject();
        try {
            obj.put("QR_Info",array);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(String.valueOf(obj), BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        AlertDialog alertDialog = new AlertDialog.Builder(inflate.getContext())
                .setView(v)
                .setTitle(R.string.qr_code_title)
                .setCancelable(true)
                .create();

        alertDialog.show();
    }


    /**
     * 19/03/14 (위진학)
     * 나의 장바구니 페이지에 내 장바구니 목록 담기
     */
    private void addSharedItemByMyId(String user_Id) {
        Log.d("SharedList", user_Id);

        mService.getSharedListByMyID(user_Id)
                .enqueue(new Callback<List<SharedProductVO>>() {
                    @Override
                    public void onResponse(Call<List<SharedProductVO>> call, Response<List<SharedProductVO>> response) {
                        list.clear();
                        list.addAll(response.body());
                        //Log.d("SharedList",response.body().toString());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<SharedProductVO>> call, Throwable t) {

                    }
                });
    }

    /**
     * 19/03/14 (위진학)
     * 공유 장바구니 페이지에 내 상대방이 보낸 장비구니 삭제하기
     */
    private void deleteSharedCartById(SharedProductVO sharedProductVO) {

        mService.deleteSharedCartById(sharedProductVO)
                .enqueue(new Callback<SharedProductVO>() {
                    @Override
                    public void onResponse(Call<SharedProductVO> call, Response<SharedProductVO> response) {
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<SharedProductVO> call, Throwable t) {

                    }
                });
    }

    /**
     * 19/03/14 (위진학)
     * 공유 장바구니 페이지에 자세히보기
     */
    private void showSharedListByYourId(SharedProductVO sharedProductVO) {

        mService.showSharedListByYourId(sharedProductVO)
                .enqueue(new Callback<List<MyProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<MyProductListVO>> call, Response<List<MyProductListVO>> response) {
                        detail_list.clear();
                        detail_list.addAll(response.body());
                        Log.d("here","here");
                        adapter.notifyDataSetChanged();
                        detailCardListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MyProductListVO>> call, Throwable t) {

                    }
                });
    }

    @NonNull
    private String getUid() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getUid();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseDatabase.getInstance().getReference().addValueEventListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        FirebaseDatabase.getInstance().getReference().removeEventListener(this);
    }

    @NonNull
    private String getUserEmail() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getEmail();
    }


}
