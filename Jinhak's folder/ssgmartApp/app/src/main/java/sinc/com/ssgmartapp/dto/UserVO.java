package sinc.com.ssgmartapp.dto;


public class UserVO {
    private String user_Id;
    private String user_Pwd;
    private String user_Name;
    private String user_Phone;

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Pwd() {
        return user_Pwd;
    }

    public void setUser_Pwd(String user_Pwd) {
        this.user_Pwd = user_Pwd;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "user_Id='" + user_Id + '\'' +
                ", user_Pwd='" + user_Pwd + '\'' +
                ", user_Name='" + user_Name + '\'' +
                ", user_Phone='" + user_Phone + '\'' +
                '}';
    }
}