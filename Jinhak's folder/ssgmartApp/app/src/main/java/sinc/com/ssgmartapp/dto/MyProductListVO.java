package sinc.com.ssgmartapp.dto;

public class MyProductListVO {
    private String product_Id;
    private String storeName;
    private String productName;
    private String category;
    private String image;
    private int price;
    private int discountPrice;
    private int stock;
    private String valid;
    private String user_Id;
    private int cnt;
    private String rev_Id;
    private String send_Id;
    private int basket;
    private boolean check;

    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MyProductListVO() {
    }


    public String getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(String product_Id) {
        this.product_Id = product_Id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getRev_Id() {
        return rev_Id;
    }

    public void setRev_Id(String rev_Id) {
        this.rev_Id = rev_Id;
    }

    public String getSend_Id() {
        return send_Id;
    }

    public void setSend_Id(String send_Id) {
        this.send_Id = send_Id;
    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "MyProductListVO{" +
                "product_Id='" + product_Id + '\'' +
                ", storeName='" + storeName + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", stock=" + stock +
                ", valid='" + valid + '\'' +
                ", user_Id='" + user_Id + '\'' +
                ", cnt=" + cnt +
                ", rev_Id='" + rev_Id + '\'' +
                ", send_Id='" + send_Id + '\'' +
                ", basket=" + basket +
                ", check=" + check +
                '}';
    }
}