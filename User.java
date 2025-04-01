abstract class User {
    private int id;
    private String name;
    private int level;
    private int points;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.level = 1; // Başlangıçta seviyeyi 1 olarak kabul ediyoruz.
        this.points = 0; // Başlangıçta puanları 0.
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // Abstract method: her tür kullanıcı için farklı bir task onaylama ve görev ekleme yöntemi olacaktır.
    public abstract void approveTask(Task task);
}

