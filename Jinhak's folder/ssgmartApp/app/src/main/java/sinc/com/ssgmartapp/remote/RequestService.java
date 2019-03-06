package sinc.com.ssgmartapp.remote;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import sinc.com.ssgmartapp.dto.Item;
import sinc.com.ssgmartapp.dto.Location;

public interface RequestService {

    @GET
    Call<List<Item>> getMenuList(@Url String uri);

    @GET
    Call<List<Location>> getLocationList(@Url String uri);

    @GET("android.do")
    Call<JsonObject> userLogin(@Query("username") String username,
                                  @Query("password") String password);

}
