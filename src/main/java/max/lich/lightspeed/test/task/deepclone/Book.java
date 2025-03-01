package max.lich.lightspeed.test.task.deepclone;

public class Book {
    private String title;
    private String description;
    private int pages;
    private String author;

    public Book(String title, String description, int pages, String author) {
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
