import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    private static List<Item> menuItems = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for (Restaurant i : getRestaurants()) {
            if (i.getName().equals(restaurantName)){
                return i;
            }
        }
        throw new restaurantNotFoundException(restaurantName);
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime, List<Item> menu) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        for(Item i: menu){
            newRestaurant.addToMenu(i.getName(), i.getPrice());
        }
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Item findMenuItemByName(String restaurantName, String menuItemName) throws itemNotFoundException, restaurantNotFoundException {
        for (Item i : findRestaurantByName(restaurantName).getMenu()) {
            if (i.getName().equals(menuItemName)){
                return i;
            }
        }
        throw new itemNotFoundException(menuItemName);
    }

    public void addMenuItem(String restaurantName, String menuItemName) throws itemNotFoundException, restaurantNotFoundException {
        Item menuItem = findMenuItemByName(restaurantName, menuItemName);
        menuItems.add(menuItem);
    }

    public void removeMenuItem(String restaurantName, String menuItemName) throws itemNotFoundException, restaurantNotFoundException {
        Item menuItem = findMenuItemByName(restaurantName, menuItemName);
        menuItems.remove(menuItem);
    }

    public List<Item> getMenuItems() {
        return menuItems;
    }

    public Integer getTotalValue() {
        int totalValue = 0;
        for(Item i: getMenuItems()) {
            totalValue += i.getPrice();
        }
        return totalValue;
    }
}
