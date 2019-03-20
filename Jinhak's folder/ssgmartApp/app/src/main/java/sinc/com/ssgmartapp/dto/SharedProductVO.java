package sinc.com.ssgmartapp.dto;


public class SharedProductVO {
    private int basket;
    private String user_image;
    private int total_price;
    private String arr_time;
    private String deadline;
    private int total_cnt;
    private String store;
    private String userName;
    private String user_Id;
    private String send_Id;

    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getArr_time() {
        return arr_time;
    }

    public void setArr_time(String arr_time) {
        this.arr_time = arr_time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getTotal_cnt() {
        return total_cnt;
    }

    public void setTotal_cnt(int total_cnt) {
        this.total_cnt = total_cnt;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getSend_Id() {
        return send_Id;
    }

    public void setSend_Id(String send_Id) {
        this.send_Id = send_Id;
    }

    @Override
    public String toString() {
        return "SharedProductVO{" +
                "basket=" + basket +
                ", user_image='" + user_image + '\'' +
                ", total_price=" + total_price +
                ", arr_time='" + arr_time + '\'' +
                ", deadline='" + deadline + '\'' +
                ", total_cnt=" + total_cnt +
                ", store='" + store + '\'' +
                ", userName='" + userName + '\'' +
                ", user_Id='" + user_Id + '\'' +
                ", send_Id='" + send_Id + '\'' +
                '}';
    }
}