class Parent extends User {

    public Parent(int id, String name) {
        super(id, name);
    }

    public void approveTask(Task task, int rating) {
        // Task onaylama ve rating verme işlemi burada yapılacak
    }

    public void approveWish(Wish wish, int requiredLevel) {
        // Çocuğun isteğini onaylama işlemi burada yapılacak
    }

    @Override
    public void approveTask(Task task) {
        // Parent için özel onay işlemleri
    }
}
