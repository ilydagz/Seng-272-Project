class WishManager {
    private List<Wish> wishes;

    public WishManager() {
        wishes = new ArrayList<>();
    }

    public void addWish(Wish wish) {
        wishes.add(wish);
    }

    public List<Wish> listAllWishes() {
        return wishes;
    }

    public Wish getWishById(int wishId) {
        for (Wish wish : wishes) {
            if (wish.getId() == wishId) {
                return wish;
            }
        }
        return null;
    }
}