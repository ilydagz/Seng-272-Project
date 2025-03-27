public class Wish {
    private int id;
    private String title;
    private String description;
    private boolean isApproved;

    public Wish(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isApproved = false;
    }

    public int getId() {
        return id;
    }
}
