import java.util.Comparator;

public class StrategyB implements Strategy {
    public Item execute(WishList wishList) {
        Comparator comparator = new ComparatorString();

        Item theLowest;
        if (wishList.getNrElements() == 1) {
            theLowest = wishList.getItem(0);
            wishList.remove(theLowest);
            return theLowest;
        }
        int aux = comparator.compare(wishList.getItem(0), wishList.getItem(1));
        if (aux < 0)
            theLowest = wishList.getItem(0);
        else
            theLowest = wishList.getItem(1);


        for (int i = 1; i < wishList.getNrElements() - 1; i++) {
            aux = comparator.compare(theLowest, wishList.getItem(i + 1));
            if (aux > 0)
                theLowest = wishList.getItem(i + 1);
        }

        wishList.remove(theLowest);
        return theLowest;
    }
}
