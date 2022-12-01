package users;

public class User {
    String name;
    int age;
    String password;
    Long id;

    public User(String name, String password, int age)
    {
        this.age = age;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() { return password;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "/" + id + "/ Пользователь " + name + ", " + age + (age % 10 == 1 ? " год": age % 10 == 0 ? " лет" : age % 10 <= 4 ? " года" : " лет");
    }

}
