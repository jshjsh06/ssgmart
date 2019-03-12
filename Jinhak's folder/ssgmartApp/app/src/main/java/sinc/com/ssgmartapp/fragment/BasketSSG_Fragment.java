package sinc.com.ssgmartapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.adapter.CardListAdapter;
import sinc.com.ssgmartapp.adapter.DeleteCardListAdapter;
import sinc.com.ssgmartapp.dto.Item;
import sinc.com.ssgmartapp.helper.RecyclerDeleteItemTouchHelper;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelper;
import sinc.com.ssgmartapp.helper.RecyclerItemTouchHelperListener;

/**
 * 장바구니 Fragment
 */
public class BasketSSG_Fragment extends Fragment implements RecyclerItemTouchHelperListener, ValueEventListener {

    View mFragmentView;

    private RecyclerView recyclerView;
    private List<Item> list;
    private DeleteCardListAdapter adapter;
    private SwipeRefreshLayout swipeLayout;
    private FirebaseDatabase mDatabase;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("BasketSSG_Fragment.onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_basket_ssg, container, false);

        recyclerView = mFragmentView.findViewById(R.id.basket_ssg_recycler_view);
        list = new ArrayList<>();
        adapter = new DeleteCardListAdapter(getContext(), list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack
                = new RecyclerDeleteItemTouchHelper(0, ItemTouchHelper.LEFT, this);

        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        return mFragmentView;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        dataSnapshot.getValue();
        list.clear();
        for (DataSnapshot fileSnapshot : dataSnapshot.child("users").child(getUid()).child("myBasket").getChildren()) {
            if (fileSnapshot != null) {
                Item item = fileSnapshot.getValue(Item.class);
                list.add(item);
            }
            else return;
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof DeleteCardListAdapter.MyViewHolder) {
            String name = list.get(viewHolder.getAdapterPosition()).getName();

            final Item deleteItem = list.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.sendBasket(deleteIndex);

            Snackbar snackbar = Snackbar.make(mFragmentView, name + "를 장바구니에서 뺏어요!!", Snackbar.LENGTH_SHORT);

            snackbar.setAction("취소", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem(deleteItem, deleteIndex);
                    //삭제를 어떻게 시킬것인가??
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
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
}
