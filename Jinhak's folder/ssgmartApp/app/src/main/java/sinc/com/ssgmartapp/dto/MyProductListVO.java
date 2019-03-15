package sinc.com.ssgmartapp.dto;

public class MyProductListVO {
    private String product_Id;
    private String storeName;
    private String productName;
    private String category;
    private String image;
    private double price;
    private double discountPrice;
    private double stock;
    private String valid;
    private String user_Id;
    private int cnt;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
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
                '}';
    }
}
