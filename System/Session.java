package System;

import java.util.ArrayList;

/**
 * A class to manage the current ordering session
 * @author edward
 */
public class Session {
    
    private ArrayList<Order> orders = new ArrayList();
    
    /**
     * Construct an ordering session.
     */
    public Session(){
        
    }
    
    /**
     * Create an order and add it to the sessions list.
     * @return Return a reference to the created order.
     */
    public Order createOrder(){
        Order order = new Order();
        
        orders.add(order);
        return order;
    }
    
    /**
     * Returns an order based on index.
     * @param index The index by which order to return.
     * @return A reference to the order at that index.
     */
    public Order getOrder(int index){
        return orders.get(index);
    }
    
    public double getSessionTotal(){
        double total = 0d;
        for(Order o : orders){
            total += o.getCost();
        }
        
        return total;
    }
}
