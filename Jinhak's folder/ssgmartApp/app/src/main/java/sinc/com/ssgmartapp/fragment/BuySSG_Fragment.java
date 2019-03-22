package sinc.com.ssgmartapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.adapter.CardListAdapter;
import sinc.com.ssgmartapp.dto.MyBasketVO;
import sinc.com.ssgmartapp.dto.ProductListVO;
import sinc.com.ssgmartapp.helper.Common;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelper;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelperListener;
import sinc.com.ssgmartapp.helper.Util;
import sinc.com.ssgmartapp.remote.RequestService;


/**
 * 올때 쓱 Fragment
 */
public class BuySSG_Fragment extends Fragment implements RecyclerItemTouchHelperListener, ValueEventListener {

    View mFragmentView;

    private RecyclerView recyclerView;
    private List<ProductListVO> list;
    private CardListAdapter adapter;
    private SwipeRefreshLayout swipeLayout;
    private TextView locationTextView;
    private Spinner categorySpinner;
    private String emartName;
    private String emartId;

    RequestService mService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Util.setGlobalFont(getContext(), getActivity().getWindow().getDecorView());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_buy_ssg, container, false);
        recyclerView = mFragmentView.findViewById(R.id.buy_ssg_recycler_view);
        mService = Common.getUrlService();

        list = new ArrayList<>();
        adapter = new CardListAdapter(getContext(), list);
        locationTextView = mFragmentView.findViewById(R.id.marker_location_textView);

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        emartName = intent.getStringExtra("marker_location");
        emartId = intent.getStringExtra("marker_id");

        if (emartName == null) {
            emartName = "명동센터점";
            emartId = "LOC_1";
            locationTextView.setText(emartName);
        }
        {
            locationTextView.setText(emartName);
        }


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack
                = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);

        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        addItemToCart(emartName);

        discountListRefreshed();

        //category 선택시
        categorySpinner = mFragmentView.findViewById(R.id.category_spinner);
        String[] str = getResources().getStringArray(R.array.category);

        Util.setGlobalFont(getContext(), mFragmentView);
        Util.setGlobalFont(getContext(), categorySpinner);

        setCategorySpinner();


        return mFragmentView;
    }


    /**
     * 19/02/28 (위진학)
     * 매장 별 할인 품목 올때 쓱 리스트에 담기
     */
    private void addItemToCart(String storeName) {
        Log.d("storeName", storeName);
        mService.getMenuListByMarketName(storeName)
                .enqueue(new Callback<List<ProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<ProductListVO>> call, Response<List<ProductListVO>> response) {
                        list.clear();
                        list.addAll(response.body());
                        Log.d("storeName", response.body().toString());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<ProductListVO>> call, Throwable t) {

                    }
                });
    }

    /**
     * 19/03/14 (위진학)
     * 매장 별,카테고리별 할인 품목 올때 쓱 리스트에 담기
     */
    private void addItemToCartByCategory(String storeName, String category) {
        mService = Common.getUrlService();

        Log.d("storeName", storeName);
        mService.getMenuListByCategory(storeName, category)
                .enqueue(new Callback<List<ProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<ProductListVO>> call, Response<List<ProductListVO>> response) {
                        list.clear();
                        list.addAll(response.body());
                        Log.d("storeName", response.body().toString());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<ProductListVO>> call, Throwable t) {

                    }
                });
    }


    /**
     * 19/02/28 (위진학)
     * 매장 별 할인 품목 내 장바구니에 담는 스와이프
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CardListAdapter.MyViewHolder) {
            String name = list.get(viewHolder.getAdapterPosition()).getProductName();

            final ProductListVO addItem = list.get(viewHolder.getAdapterPosition());
            final int addIndex = viewHolder.getAdapterPosition();

            //mDatabase.getReference().child("users").child(getUid()).child("myBasket").push().setValue(addItem);
            adapter.sendBasket(addIndex);

            insertItem(addItem);
            addItemToCart(emartName);

            Snackbar snackbar = Snackbar.make(mFragmentView, name + "를 장바구니에 넣었어요!!", Snackbar.LENGTH_SHORT);

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    /**
     * 19/03/15 (위진학)
     * 상품 장바구니에 넣기
     */
    private void insertItem(ProductListVO item) {

        MyBasketVO myBasketVO = new MyBasketVO();
        myBasketVO.setCnt(1);
        myBasketVO.setUser_Id(getUserEmail());
        myBasketVO.setDiscountPrice(Integer.parseInt(String.valueOf(Math.round(item.getDiscountPrice()))));
        myBasketVO.setProduct_Id(item.getProduct_Id());
        myBasketVO.setStoreName(emartName);

        mService.insertMyBasket(myBasketVO).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                try {
                    Log.d("InsertItem", response.body().toString());
                    Toast.makeText(getContext(), "Insert 성공", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
            }
        });

    }

    /**
     * 19/02/28 (위진학)
     * 매장 별 할인 품목 새로고침
     */
    public void discountListRefreshed() {
        swipeLayout = mFragmentView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addItemToCart(emartName);
                swipeLayout.setRefreshing(false);
            }
        });
        swipeLayout.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light
        );
    }


    /**
     * 19/03/14 (위진학)
     * 스피너 카테고리 선택시 매장별,할인별 제품 새로고침
     */
    public void setCategorySpinner() {

        String[] str = getResources().getStringArray(R.array.category);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, str) {
            @NonNull
            @Override
            public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/BMDOHYEON.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }

            @NonNull
            @Override
            public View getDropDownView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/BMDOHYEON.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };

        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    addItemToCart(emartName);
                } else {
                    addItemToCartByCategory(emartName, categorySpinner.getItemAtPosition(position).toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @NonNull
    private String getUserEmail() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getEmail();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        dataSnapshot.getValue();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
