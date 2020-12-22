package sample;

public class User {
    private String username;
    private String password;
    private String name;
    private String imagePath;
    private int type;
    public User(String username,String password, String name, String imagePath,int type)
    {
        this.username=username;
        this.password=password;
        this.name=name;
        this.imagePath=imagePath;
        this.type=type;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
