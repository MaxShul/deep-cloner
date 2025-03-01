package max.lich.lightspeed.test.task.deepclone;

public class Book {
    private String name;
    private String description;
    private int pages;
    private String author;

    public Book() {
    }

    public Book(String name, String description, int pages, String author) {
        this.name = name;
        this.description = description;
        this.pages = pages;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pages=" + pages +
                ", author='" + author + '\'' +
                '}';
    }
}
