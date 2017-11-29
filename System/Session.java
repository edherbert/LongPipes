package System;

import java.util.ArrayList;

/**
 * A class to manage the current ordering session
 * @author edward
 */
public class Session {
    
    private ArrayList<Order> orders = new ArrayList();
    //used to reference which order the user interface is currently editing.
    private int currentOrder = 0;
    
    /**
     * Construct an ordering session.
     */
    public Session(){
        PipeGUI gui = new PipeGUI(this);
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
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
    
    public int getNumberOfOrders(){
        return orders.size();
    }
    
    public Order getCurrentOrder(){
        //If the current order does not fit the size of the array then return null.
        if(currentOrder < 0 || currentOrder >= orders.size()){
            return null;
        }else return orders.get(currentOrder);
    }
    
    public void setCurrentOrder(int order){
        if(order < 0) order = 0;
        //Maybe do a check to see if the passed value is higher than what's in the list.
        currentOrder = order;
    }
    
    public void setCurrentOrder(Order order){
        for(int i = 0; i < orders.size(); i++){
            if(orders.get(i) == order){
                currentOrder = i;
            }
        }
    }
}
