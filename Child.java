class Child extends User {
    private List<Task> tasks;
    private List<Wish> wishes;

    public Child(int id, String name) {
        super(id, name);
        this.tasks = new ArrayList<>();
        this.wishes = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addWish(Wish wish) {
        wishes.add(wish);
    }

    public void approveTask(Task task) {
        // Task onay işlemi burada yapılacak
    }

    public void markTaskAsCompleted(Task task) {
        // Görevi tamamlanmış olarak işaretle
    }

    public void receivePoints(int points) {
        setPoints(getPoints() + points);
        updateLevel();
    }

    private void updateLevel() {
        // Puanlara göre seviyeyi güncelle
        if (getPoints() <= 40) {
            setLevel(1);
        } else if (getPoints() <= 60) {
            setLevel(2);
        } else if (getPoints() <= 80) {
            setLevel(3);
        } else {
            setLevel(4);
        }
    }
}
