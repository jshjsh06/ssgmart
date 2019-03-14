package sinc.com.ssgmartapp.helper;

import sinc.com.ssgmartapp.remote.RequestService;
import sinc.com.ssgmartapp.remote.RetrofitClient;

public class Common {

    public static RequestService getMenuRequest(){
        return RetrofitClient.getClient("https://ssg-mart-app.firebaseio.com/").create(RequestService.class);
    }

    public static RequestService getLoginService() {
        return RetrofitClient.getClient("http://10.149.178.22:8088/").create(RequestService.class);
    }

    public static RequestService getMenuRequestByMarketName(){
        return RetrofitClient.getClient("http://10.149.178.22:8088/").create(RequestService.class);
    }

    public static RequestService getUrlService(){
        return RetrofitClient.getClient("http://10.149.178.22:8088/").create(RequestService.class);
    }

}