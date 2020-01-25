public class User {


    int age;

    public User(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int grow(){
        return ++age;
    }
    public int grow2(){
        return age+=2;
    }
}
