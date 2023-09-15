import org.junit.jupiter.api.*;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class ItemServiceTest {

    RestaurantService service = new RestaurantService();

    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


    @BeforeEach
    public void beforeEachTest() throws itemNotFoundException, restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        service.addRestaurant("Amelie's cafe","Chennai", openingTime, closingTime, restaurant.getMenu());
        service.addMenuItem("Amelie's cafe","Sweet corn soup");
    }

    @AfterEach
    public void AfterEachTest() throws restaurantNotFoundException {
        service.removeRestaurant("Amelie's cafe");
    }
    //>>>>>>>>>>>>>>>>>>>>>>USER: SELECTING ITEMS FROM MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void selected_menu_items_is_removed() throws itemNotFoundException, restaurantNotFoundException {
        service.removeMenuItem("Amelie's cafe","Sweet corn soup");
        assertEquals(0, service.getMenuItems().size());
    }

    @Test
    public void selected_menu_items_total_when_all_items_are_removed() throws itemNotFoundException, restaurantNotFoundException {
        service.removeMenuItem("Amelie's cafe", "Sweet corn soup");
        assertEquals(0, service.getTotalValue());
    }

    @Test
    public void selected_menu_items_total_is_summed_up() throws itemNotFoundException, restaurantNotFoundException {
        service.addMenuItem("Amelie's cafe", "Vegetable lasagne");
        assertEquals(119+269, service.getTotalValue());
    }

    //<<<<<<<<<<<<<<<<<<<<USER: SELECTING ITEMS FROM MENU>>>>>>>>>>>>>>>>>>>>>>>>>>
}
