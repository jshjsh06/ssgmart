package sinc.com.ssgmartapp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okio.Utf8;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.adapter.DeleteCardListAdapter;
import sinc.com.ssgmartapp.adapter.UserComponentAdapter;
import sinc.com.ssgmartapp.dto.MyProductListVO;
import sinc.com.ssgmartapp.dto.SharedProductVO;
import sinc.com.ssgmartapp.dto.UserData;
import sinc.com.ssgmartapp.dto.UserVO;
import sinc.com.ssgmartapp.helper.Common;
import sinc.com.ssgmartapp.helper.RecyclerDeleteItemTouchHelper;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelperListener;
import sinc.com.ssgmartapp.helper.Util;
import sinc.com.ssgmartapp.remote.RequestService;

import static android.content.Context.SENSOR_SERVICE;

/**
 * 장바구니 Fragment
 */
public class BasketSSG_Fragment extends Fragment implements RecyclerItemTouchHelperListener, ValueEventListener, SensorEventListener, AdapterView.OnItemClickListener {

    private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AAAAMVZD4uA:APA91bGylAREesXJvgHZi6kGZqsVbDa3vnFAxwnAaCbSV6drUXiXKrX8SmUnTKiZzRGDMA9Xwzj0lGzPzFVo8_s22zBXGf0eGpfFfa5DtdKipGZwvZ-pyl5fiznfuQe_VlBpkoQ41e4r";

    View mFragmentView;
    private RecyclerView recyclerView;
    private List<MyProductListVO> list;
    private DeleteCardListAdapter adapter;
    private FirebaseDatabase mFirebaseDatabase;
    RequestService mService;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private long mShakeTime;
    private static final int SHAKE_SKIP_TIME = 500;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private String emartName;


    LayoutInflater inflate;
    FloatingActionButton qr_fab;
    FloatingActionButton shared_fab;

    UserComponentAdapter userComponentAdapter;
    List<UserVO> user_list;
    private Dialog user_list_dialog;
    private ListView listView;

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
        mFirebaseDatabase = FirebaseDatabase.getInstance();


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
        Log.d("매장이름", emartName);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack
                = new RecyclerDeleteItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        addItemToCart(getUserEmail(), emartName);

        qr_fab = mFragmentView.findViewById(R.id.qr_fab);

        qr_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrDialog();
            }
        });


        user_list = new ArrayList<>();
        shared_fab = mFragmentView.findViewById(R.id.shared_fab);
        shared_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMyBasket(list);
                getUserList(getUserEmail());
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

            addItemToCart(getUserEmail(), emartName);
        }
    }

    /**
     * 19/03/14 (위진학)
     * 나의 장바구니 페이지에 내 장바구니 목록 담기
     */
    private void addItemToCart(String user_Id, String emartName) {

        mService.getMyBasketListByMyId(user_Id, emartName)
                .enqueue(new Callback<List<MyProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<MyProductListVO>> call, Response<List<MyProductListVO>> response) {
                        list.clear();
                        list.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MyProductListVO>> call, Throwable t) {

                    }
                });
    }


    /**
     * 19/03/14 (위진학)
     * 나의 장바구니 페이지에 내 개별 상품 삭제하기
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

    /**
     * 19/03/18 (위진학)
     * 흔들어서 QR 코드 생성하기
     */
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

    /**
     * 19/03/18 (위진학)
     * 내 장바구니에 담긴 목록 QR코드 생성하기
     */
    public void qrDialog() {

        updateMyBasket(list);

        inflate = LayoutInflater.from(getContext());
        View v = inflate.inflate(R.layout.qr_dialog, null);
        ImageView imageView = v.findViewById(R.id.qr_Dialog_imageView);

        //QR코드에 들어갈 내용 넣어주기
        JSONObject obj = new JSONObject();
        String check = "true";
        String user_id = getUserEmail();
        String store = emartName;

        try {
            obj.put("heck", check);
            obj.put("user_Id", user_id);
            obj.put("store", store);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("utf",obj.toString());

        Charset chrutf = Charset.forName("UTF-8");
        final String b = new String(obj.toString().getBytes(),chrutf);
        System.out.println(b);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(b, BarcodeFormat.QR_CODE, 500, 500);
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
                        addItemToCart(getUserEmail(), emartName);
                    }

                    @Override
                    public void onFailure(Call<List<MyProductListVO>> call, Throwable t) {

                    }
                });
    }

    /**
     * 19/03/18 (위진학)
     * 등록된 친구 리스트 다이얼로그
     */
    private void sharedFriends(List<UserVO> userList) {
        Log.d("userList", userList.toString());

        userComponentAdapter = new UserComponentAdapter(getContext(), R.layout.user_component_item, userList);


        user_list_dialog = new Dialog(getContext());
        user_list_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        user_list_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        user_list_dialog.setContentView(R.layout.user_list_dialog);

        listView = user_list_dialog.findViewById(R.id.user_component_list);
        listView.setAdapter(userComponentAdapter);
        listView.setOnItemClickListener(this);
        Button positiveButton = user_list_dialog.findViewById(R.id.positive_button);

        positiveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                user_list_dialog.dismiss();
            }
        });
        user_list_dialog.show();

    }

    /**
     * 19/03/18 (위진학)
     * 나를 제외한 등록된 사용자 리스트 가져오기
     */
    private void getUserList(String userEmail) {
        mService.getUserListByMyID(userEmail)
                .enqueue(new Callback<List<UserVO>>() {
                    @Override
                    public void onResponse(Call<List<UserVO>> call, Response<List<UserVO>> response) {
                        user_list.clear();
                        user_list.addAll(response.body());
                        sharedFriends(user_list);
                    }

                    @Override
                    public void onFailure(Call<List<UserVO>> call, Throwable t) {

                    }
                });
    }

    /**
     * 19/03/18 (위진학)
     * FCM 다이얼로그 띄우기
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final UserVO chatData = (UserVO) parent.getItemAtPosition(position);

        if (!TextUtils.isEmpty(chatData.getUser_Id())) {
            final EditText editText = new EditText(getContext());
            new AlertDialog.Builder(getContext())
                    .setMessage(chatData.getUser_Name() + "님 에게 올 때 사오라고 메시지 보내기")
                    .setView(editText)
                    .setPositiveButton("보내기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendPostToFCM(chatData, editText.getText().toString());
                            sendSharedBasket(getUserEmail(), chatData.getUser_Id());
                            user_list_dialog.dismiss();
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
        }

    }

    /**
     * 19/03/18 (위진학)
     * 상대방에게 장바구니 공유하기
     */
    private void sendSharedBasket(String my_Id, String your_id) {
        mService.sendSharedBasketByMyID(my_Id, your_id)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });

    }

    /**
     * 19/03/18 (위진학)
     * FCM 메시지 보내기
     */
    private void sendPostToFCM(final UserVO chatData, final String message) {

        mFirebaseDatabase.getReference("users")
                .child(chatData.getUser_Id().substring(0, chatData.getUser_Id().indexOf('@')))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final UserData userData = dataSnapshot.getValue(UserData.class);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    // FMC 메시지 생성 start
                                    JSONObject root = new JSONObject();
                                    JSONObject notification = new JSONObject();
                                    notification.put("body", message);
                                    notification.put("title", getString(R.string.app_name));
                                    root.put("notification", notification);
                                    root.put("to", userData.fcmToken);
                                    Log.d("FCM", userData.fcmToken);
                                    // FMC 메시지 생성 end
                                    URL Url = new URL(FCM_MESSAGE_URL);
                                    HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                                    conn.setRequestMethod("POST");
                                    conn.setDoOutput(true);
                                    conn.setDoInput(true);
                                    conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                                    conn.setRequestProperty("Accept", "application/json");
                                    conn.setRequestProperty("Content-type", "application/json");
                                    OutputStream os = conn.getOutputStream();
                                    os.write(root.toString().getBytes("utf-8"));
                                    os.flush();
                                    conn.getResponseCode();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

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
}
