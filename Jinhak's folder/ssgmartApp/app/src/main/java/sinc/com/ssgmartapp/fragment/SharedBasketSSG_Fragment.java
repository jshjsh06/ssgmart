package sinc.com.ssgmartapp.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.adapter.DeleteCardListAdapter;
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

            String name = list.get(viewHolder.getAdapterPosition()).getUserName();

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
//        adapter.sendBasket(index);
//        adapter.restoreItem(item, index);
        showSharedListByYourId(item);
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
     * 공유 장바구니 페이지에 내 상대방이 보낸 장비구니 삭제하기
     */
    private void showSharedListByYourId(SharedProductVO sharedProductVO) {

        mService.showSharedListByYourId(sharedProductVO)
                .enqueue(new Callback<List<MyProductListVO>>() {
                    @Override
                    public void onResponse(Call<List<MyProductListVO>> call, Response<List<MyProductListVO>> response) {
                        detail_list.addAll(response.body());
                        Log.d("Response",detail_list.toString());

                        adapter.notifyDataSetChanged();
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
