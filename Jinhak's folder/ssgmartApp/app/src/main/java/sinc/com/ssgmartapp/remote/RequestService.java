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
import sinc.com.ssgmartapp.dto.ProductListVO;

public interface RequestService {

    @GET
    Call<List<ProductListVO>> getMenuList(@Url String uri);

    @GET
    Call<List<Location>> getLocationList(@Url String uri);

    @GET("user/android.do")
    Call<JsonObject> userLogin(@Query("username") String username,
                               @Query("password") String password);

    @POST("androidInsert.do")
    Call<Integer> ItemInsert(@Body ProductListVO item);

    @GET("productList/productList1.do")
    Call<List<ProductListVO>> getMenuListByMarketName(@Query("storeName") String storeName);


}
