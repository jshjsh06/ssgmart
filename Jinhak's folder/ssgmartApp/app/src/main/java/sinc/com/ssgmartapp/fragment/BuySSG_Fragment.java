package sinc.com.ssgmartapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.helper.Common;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelper;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelperListener;
import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.adapter.CardListAdapter;
import sinc.com.ssgmartapp.dto.Item;
import sinc.com.ssgmartapp.remote.IMenuRequest;


/**
 * 올때 쓱 Fragment
 */
public class BuySSG_Fragment extends Fragment implements RecyclerItemTouchHelperListener {

    View mFragmentView;

    private final String URL_API = "https://api.androidhive.info/json/menu.json";
    private RecyclerView recyclerView;
    private List<Item> list;
    private CardListAdapter adapter;
    private SwipeRefreshLayout swipeLayout;

    IMenuRequest mService;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("BuySSG_Fragment.onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_buy_ssg, container, false);


        mService = Common.getMenuRequest();
        recyclerView = mFragmentView.findViewById(R.id.buy_ssg_recycler_view);
        list = new ArrayList<>();
        adapter = new CardListAdapter(getContext(), list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack
                = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);

        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        addItemToCart();

        discountListRefreshed();

        return mFragmentView;
    }


    /**
     * 19/02/28 (위진학)
     * 매장 별 할인 품목 올때 쓱 리스트에 담기
     */
    private void addItemToCart() {
        mService.getMenuList(URL_API)
                .enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                        list.clear();
                        list.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {

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
            String name = list.get(viewHolder.getAdapterPosition()).getName();

            final Item deletedItem = list.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.sendBasket(deleteIndex);

            Snackbar snackbar = Snackbar.make(mFragmentView, name + "를 장바구니에 넣었어요!!", Snackbar.LENGTH_SHORT);
            snackbar.setAction("취소", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem(deletedItem, deleteIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
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
                addItemToCart();
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
}
