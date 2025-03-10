package max.lich.lightspeed.test.task.deepclone;

import java.util.Arrays;
import java.util.List;

class Person {
    private String name;
    private int age;
    private String[] favouriteMovies;
    private List<Book> favoriteBooks;

    public Person() {
    }

    public Person(String name, int age, String[] favouriteMovies, List<Book> favoriteBooks) {
        this.name = name;
        this.age = age;
        this.favouriteMovies = favouriteMovies;
        this.favoriteBooks = favoriteBooks;
    }

    public Person(String name, int age, List<Book> favoriteBooks) {
        this.name = name;
        this.age = age;
        this.favoriteBooks = favoriteBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<Book> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    public String[] getFavouriteMovies() {
        return favouriteMovies;
    }

    public void setFavouriteMovies(String[] favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", favouriteMovies=" + Arrays.toString(favouriteMovies) +
                ", favoriteBooks=" + favoriteBooks +
                '}';
    }
}
