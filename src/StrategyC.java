public class StrategyC implements Strategy {
    public Item execute(WishList wishList) {
        Item returnItem = wishList.getItem(wishList.getNrElements() - 1);
        wishList.remove(returnItem);
        return returnItem;
    }
}
