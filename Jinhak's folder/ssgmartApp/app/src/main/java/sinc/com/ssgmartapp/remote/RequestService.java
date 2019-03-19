package sinc.com.ssgmartapp.remote;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import sinc.com.ssgmartapp.dto.Location;
import sinc.com.ssgmartapp.dto.MyBasketVO;
import sinc.com.ssgmartapp.dto.MyProductListVO;
import sinc.com.ssgmartapp.dto.ProductListVO;
import sinc.com.ssgmartapp.dto.SharedProductVO;
import sinc.com.ssgmartapp.dto.UserVO;

public interface RequestService {

    @GET
    Call<List<ProductListVO>> getMenuList(@Url String uri);

    //지도 가져오기
    @GET
    Call<List<Location>> getLocationList(@Url String uri);

    //로그인
    @GET("user/android.do")
    Call<JsonObject> userLogin(@Query("username") String username,
                               @Query("password") String password);

    //매장 이름으로 할인상품 가져오기
    @GET("productList/productList1.do")
    Call<List<ProductListVO>> getMenuListByMarketName(@Query("storeName") String storeName);

    //매장 이름이랑 카테고리로 할인 상품 가져오기
    @GET("productList/productList2.do")
    Call<List<ProductListVO>> getMenuListByCategory(@Query("storeName") String storeName, @Query("category") String category);

    //내 장바구니에 담기
    @POST("basket/insertMyBasket.do")
    Call<Integer> insertMyBasket(@Body MyBasketVO myBasketVO);

    //내 장바구니 리스트 가져오기
    @GET("basket/selectMyBasket.do")
    Call<List<MyProductListVO>> getMyBasketListByMyId(@Query("user_Id") String user_Id, @Query("storeName") String storeName);

    //내 장바구니 리스트 삭제하기
    @POST("basket/deleteMyBasket.do")
    Call<List<MyProductListVO>> deleteMyBasketListByMyId(@Body MyProductListVO myProductListVO);

    //내 장바구니 리스트 업데이트하기
    @POST("basket/updateBasket.do")
    Call<List<MyProductListVO>> updateMyBasketById(@Body List<MyProductListVO> list);

    //내 아이디를 제외한 USER List 가져오기
    @GET("shareBasket/returnAddress.do")
    Call<List<UserVO>> getUserListByMyID(@Query("username") String username);

    //상대방 장바구니에 공유하기
    @GET("shareBasket/insertShareBasket.do")
    Call<JsonObject> sendSharedBasketByMyID(@Query("my_id") String my_id, @Query("your_id") String your_id);

    //상대방 장바구니에 공유하기
    @GET("shareBasket/selectSharedBasket.do")
    Call<List<SharedProductVO>> getSharedListByMyID(@Query("user_Id") String my_id);

    //공유 장바구니에 상대방 장바구니 삭제하기
    @POST("/shareBasket/deleteSharedBasket.do")
    Call<SharedProductVO> deleteSharedCartById(@Body SharedProductVO sharedProductVO);

    //공유 장바구니 각각 공유한 사용자 장바구니 상세보기
    @POST("shareBasket/detailSharedBasket.do")
    Call<List<MyProductListVO>> showSharedListByYourId(@Body SharedProductVO sharedProductVO);


}
