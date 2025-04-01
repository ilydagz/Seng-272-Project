class Teacher extends User {

    public Teacher(int id, String name) {
        super(id, name);
    }

    public void addTaskForChild(Child child, Task task) {
        child.addTask(task);
    }

    @Override
    public void approveTask(Task task) {
        // Teacher için özel onay işlemleri
    }
}

