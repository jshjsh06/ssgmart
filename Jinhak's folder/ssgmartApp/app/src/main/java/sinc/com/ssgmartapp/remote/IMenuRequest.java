package sinc.com.ssgmartapp.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import sinc.com.ssgmartapp.dto.Item;
import sinc.com.ssgmartapp.dto.Location;

public interface IMenuRequest {

    @GET
    Call<List<Item>> getMenuList(@Url String uri);

    @GET
    Call<List<Location>> getLocationList(@Url String uri);

}
