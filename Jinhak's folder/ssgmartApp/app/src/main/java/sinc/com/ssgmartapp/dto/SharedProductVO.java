package sinc.com.ssgmartapp.dto;

public class SharedProductVO {
    private String user_image;
    private int total_price;
    private String arr_time;
    private String deadline;
    private int total_cnt;
    private String store;
    private String userName;
    private int basket;

    public SharedProductVO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SharedProductVO(String user_image, int total_price, String arr_time, String deadline, int total_cnt,
                           String store, String userName) {
        super();
        this.user_image = user_image;
        this.total_price = total_price;
        this.arr_time = arr_time;
        this.deadline = deadline;
        this.total_cnt = total_cnt;
        this.store = store;
        this.userName = userName;
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

    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "SharedProductVO{" +
                "user_image='" + user_image + '\'' +
                ", total_price=" + total_price +
                ", arr_time='" + arr_time + '\'' +
                ", deadline='" + deadline + '\'' +
                ", total_cnt=" + total_cnt +
                ", store='" + store + '\'' +
                ", userName='" + userName + '\'' +
                ", basket=" + basket +
                '}';
    }
}