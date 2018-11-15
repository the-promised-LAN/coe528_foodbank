# coe528_final
A repository for the final project of coe528.

## PREFACE (For Y.,K.,L.)

The Good Food Center at Ryerson is overhauling their food management system for users of the food bank. In the original formula (without our application) there are three main users: Food Delivery Man, Food Center Volunteer, and Customer.

### Food Delivery Man:

Delivers food from the Food Delivery Center. Knows what his delivery is, and knows the receiving time of the food center volunteer. In case of having mechanical issues or problems with food (For example, having a small order that will not feed enough Customers for the day), all he can do is call and have the Food Center Volunteer relay information.

### Food Center Volunteer:

Received food from the food delivery man, organized it in the food center, and then would signal to the waiting customers that the foodbank is open. In cases of being sick, delivery troubles, there is no communication.

### Customer:

Waits in line for food to be delivered and organized. Then, uses their weekly ten (10) points in exchange for food. Food can have different point values (2 for 1, maximum one item per member, etc.). In case of not being able to wait in line, there is no effect for Food Center Volunteer or Food Delivery Man.

## ACTUAL PROBLEM STATEMENT

Our application will serve as an interface for Food Delivery Man, Food Center Volunteer, and Customer. Food Delivery Man has access to the Food Array. He manages the food array and passes it off to Food Center Volunteer. Food Delivery Man can also make the contents of Food Array known to Food Center Volunteer before the handoff. However, Food Delivery Man only knows the quantities of these items and not their point values. Finally, Food Delivery Man can open Service Tickets to let the Food Center Volunteer know of three emergencies: LeftKeyInCar, VehicleAccident, and SmallOrder, all which satisfy the Emergency Interface.

Food Center Volunteer has access to the Food Delivery Man’s food array and can make a copy of it. They are able to add the properties int pointValue, boolean maxOnePerMember, as well as other properties (beRefrigerated, etc). They can also respond to Emergencies raised by Food Delivery Man, as procedures. Finally, they record the Customer’s points in their CustomerPoint array, and deny customers from going beyond their limit.

Customers can query the app to see if there is an emergency raised by the Food Center Volunteer and they can act accordingly. Customers also have access to their own Food Array, which each item corresponds to a point and is cleared by the end of the week. For example, if there were 4 apples for one point in a specific week, and a customer took the deal, then 4 apples would be in one food array spot.

