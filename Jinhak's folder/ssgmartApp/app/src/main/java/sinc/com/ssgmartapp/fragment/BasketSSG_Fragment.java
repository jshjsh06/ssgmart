package sinc.com.ssgmartapp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.adapter.DeleteCardListAdapter;
import sinc.com.ssgmartapp.dto.MyProductListVO;
import sinc.com.ssgmartapp.dto.ProductListVO;
import sinc.com.ssgmartapp.helper.Common;
import sinc.com.ssgmartapp.helper.RecyclerDeleteItemTouchHelper;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelperListener;
import sinc.com.ssgmartapp.remote.RequestService;

import static android.content.Context.SENSOR_SERVICE;

/**
 * 장바구니 Fragment
 */
public class BasketSSG_Fragment extends Fragment implements RecyclerItemTouchHelperListener, ValueEventListener, SensorEventListener {

    View mFragmentView;
    private RecyclerView recyclerView;
    private List<MyProductListVO> list;
    private DeleteCardListAdapter adapter;
    private SwipeRefreshLayout swipeLayout;
    private FirebaseDatabase mDatabase;
    RequestService mService;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private long mShakeTime;
    private static final int SHAKE_SKIP_TIME = 500;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private String emartName;


    LayoutInflater inflate;
    FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("BasketSSG_Fragment.onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        mService = Common.getUrlService();
        inflater = LayoutInflater.from(getContext());


        mFragmentView = inflater.inflate(R.layout.fragment_basket_ssg, container, false);

        recyclerView = mFragmentView.findViewById(R.id.basket_ssg_recycler_view);
        adapter = new DeleteCardListAdapter(getContext(), list, getUserEmail());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        //SensorManager
        mSensorManager = (SensorManager) this.getActivity().getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        emartName = intent.getStringExtra("marker_location");


        if (emartName == null) {
            emartName = "명동센터점";
        }
        Log.d("매장이름",emartName);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack
                = new RecyclerDeleteItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        addItemToCart(getUserEmail());

        fab = mFragmentView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrDialog();
            }
        });

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


        if (viewHolder instanceof DeleteCardListAdapter.MyViewHolder) {

            final MyProductListVO deleteItem = list.get(viewHolder.getAdapterPosition());
            deleteItem.setUser_Id(getUserEmail());

            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.sendBasket(deleteIndex);
            deleteItemToCart(deleteItem);

            addItemToCart(getUserEmail());
        }
    }

    /**
     * 19/03/14 (위진학)
     * 나의 장바구니 페이지에 내 장바구니 목록 담기
     */
    private void addItemToCart(String user_Id) {

        mService.getMyBasketListByMyId(user_Id)
                .enqueue(new Callback<List<MyProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<MyProductListVO>> call, Response<List<MyProductListVO>> response) {
                        list.clear();
                        list.addAll(response.body());
                        Log.d("storeName", response.body().toString());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MyProductListVO>> call, Throwable t) {

                    }
                });
    }


    /**
     * 19/03/14 (위진학)
     * 나의 장바구니 페이지에 내 장바구니 목록 담기
     */
    private void deleteItemToCart(MyProductListVO myProductListVO) {

        mService.deleteMyBasketListByMyId(myProductListVO)
                .enqueue(new Callback<List<MyProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<MyProductListVO>> call, Response<List<MyProductListVO>> response) {
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MyProductListVO>> call, Throwable t) {

                    }
                });
    }

    @NonNull
    private String getUserEmail() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getEmail();
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            float gravityX = axisX / SensorManager.GRAVITY_EARTH;
            float gravityY = axisY / SensorManager.GRAVITY_EARTH;
            float gravityZ = axisZ / SensorManager.GRAVITY_EARTH;

            Float f = gravityX * gravityX + gravityY * gravityY + gravityZ + gravityZ;
            double sqaredD = Math.sqrt(f.doubleValue());
            float gForce = (float) sqaredD;

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                long currentTime = System.currentTimeMillis();
                if (mShakeTime + SHAKE_SKIP_TIME > currentTime) {
                    return;
                }
                mShakeTime = currentTime;
                qrDialog();
                Log.d("shake", "shake");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    //QR 코드 다이얼로그
    public void qrDialog() {

        updateMyBasket(list);

        inflate = LayoutInflater.from(getContext());
        View v = inflate.inflate(R.layout.qr_dialog, null);
        ImageView imageView = v.findViewById(R.id.qr_Dialog_imageView);

        //QR코드에 들어갈 내용 넣어주기
        String qr_text = "wlsgkr@gmail.com";

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(qr_text, BarcodeFormat.QR_CODE, 500, 500);
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
    private void updateMyBasket(List<MyProductListVO> list) {

        mService.updateMyBasketById(list)
                .enqueue(new Callback<List<MyProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<MyProductListVO>> call, Response<List<MyProductListVO>> response) {
                        addItemToCart(getUserEmail());
                    }

                    @Override
                    public void onFailure(Call<List<MyProductListVO>> call, Throwable t) {

                    }
                });
    }
}
