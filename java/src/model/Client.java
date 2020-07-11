package model;

public class Client {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private Double rating;

    public Client(Integer id, String name, String login, String password, Double rating) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.rating = rating;
    }

    public Client(Integer id, String name, String login, Double rating) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.rating = rating;
    }

    public Client(String name, String login, String password, Double rating) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
