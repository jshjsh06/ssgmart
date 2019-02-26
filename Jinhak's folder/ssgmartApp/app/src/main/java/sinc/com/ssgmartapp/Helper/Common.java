package sinc.com.ssgmartapp.helper;

import sinc.com.ssgmartapp.remote.IMenuRequest;
import sinc.com.ssgmartapp.remote.RetrofitClient;

public class Common {

    public static IMenuRequest getMenuRequest(){
        return RetrofitClient.getClient("https://api.androidhive.info/").create(IMenuRequest.class);
    }
}
