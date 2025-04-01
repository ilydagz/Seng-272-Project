public class User {
    protected String id;
    protected String name;
    protected String role; // Child, Parent, Teacher

    public User(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User ID: " + id + ", Name: " + name + ", Role: " + role;
    }
}

