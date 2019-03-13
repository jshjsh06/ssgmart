package sinc.com.ssgmartapp.fragment;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.adapter.CardListAdapter;
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

    RequestService mService;

    private FirebaseDatabase mDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Util.setGlobalFont(getContext(), getActivity().getWindow().getDecorView());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_buy_ssg, container, false);

        mService = Common.getMenuRequestByMarketName();
        recyclerView = mFragmentView.findViewById(R.id.buy_ssg_recycler_view);

        list = new ArrayList<>();
        adapter = new CardListAdapter(getContext(), list);
        locationTextView = mFragmentView.findViewById(R.id.marker_location_textView);
        mDatabase = FirebaseDatabase.getInstance();

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        emartName = intent.getStringExtra("marker_location");

        if (emartName == null) {
            emartName ="명동센터점";
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
        setCategorySpinner();

        return mFragmentView;
    }


    /**
     * 19/02/28 (위진학)
     * 매장 별 할인 품목 올때 쓱 리스트에 담기
     */
    private void addItemToCart(String storeName) {
        Log.d("storeName",storeName);
        mService.getMenuListByMarketName(storeName)
                .enqueue(new Callback<List<ProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<ProductListVO>> call, Response<List<ProductListVO>> response) {
                        list.clear();
                        list.addAll(response.body());
                        Log.d("storeName",response.body().toString());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<ProductListVO>> call, Throwable t) {

                    }
                });
    }

    /**
     * 19/02/28 (위진학)
     * 매장 별 할인 품목 장바구니에 담기
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CardListAdapter.MyViewHolder) {
            String name = list.get(viewHolder.getAdapterPosition()).getProductName();

            final ProductListVO addItem = list.get(viewHolder.getAdapterPosition());
            final int addIndex = viewHolder.getAdapterPosition();

            mDatabase.getReference().child("users").child(getUid()).child("myBasket").push().setValue(addItem);
            adapter.sendBasket(addIndex);

            insertItem(addItem);

            Snackbar snackbar = Snackbar.make(mFragmentView, name + "를 장바구니에 넣었어요!!", Snackbar.LENGTH_SHORT);
            snackbar.setAction("취소", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem(addItem, addIndex);
                    //삭제를 어떻게 시킬것인가??
                    // mDatabase.getReference().child("users").child(getUid()).child("myBasket").removeValue();
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    private void insertItem(ProductListVO item) {

        mService.ItemInsert(item).enqueue(new Callback<Integer>() {
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
                Toast.makeText(getContext(), "Insert실패", Toast.LENGTH_LONG).show();
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

    public void setCategorySpinner() {
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    return;
                } else {
                    addItemToCart(emartName);
                    Toast.makeText(getContext(), "선택된 카테고리 : " + categorySpinner.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @NonNull
    private String getUid() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getUid();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        dataSnapshot.getValue();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
