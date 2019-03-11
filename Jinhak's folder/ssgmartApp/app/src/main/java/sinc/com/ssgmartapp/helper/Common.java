package sinc.com.ssgmartapp.helper;

import sinc.com.ssgmartapp.remote.RequestService;
import sinc.com.ssgmartapp.remote.RetrofitClient;

public class Common {

    public static RequestService getMenuRequest(){
        return RetrofitClient.getClient("https://api.androidhive.info/").create(RequestService.class);
    }

    public static RequestService getLoginService() {
        return RetrofitClient.getClient("http://10.149.179.175:8088/user/").create(RequestService.class);
    }

}
