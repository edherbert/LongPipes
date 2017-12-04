package System;

import java.util.ArrayList;

/**
 * A class to manage the current ordering session
 * @author edward
 */
public class Session {
    
    private ArrayList<Order> orders = new ArrayList();
    //used to reference which order the user interface is currently editing.
    //-1 means no selection
    private int currentOrder = -1;
    
    /**
     * Construct an ordering session.
     * This will also create an instance of the pipe gui.
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
    
    /**
     * Get the total of all the orders in the session, including quantity.
     * @return Get the session total including quantity.
     */
    public double getSessionTotal(){
        double total = 0d;
        for(Order o : orders){
            total += o.getBaseCost() * o.getQuantity();
        }
        
        return total;
    }
    
    /**
     * Get the number of orders in the session.
     * @return Number of orders.
     */
    public int getNumberOfOrders(){
        return orders.size();
    }
    
    /**
     * Get a reference to the current order. 
     * This will return null if there is no current order.
     * @return A reference to the current order.
     */
    public Order getCurrentOrder(){
        //If the current order does not fit the size of the array then return null.
        if(currentOrder < 0 || currentOrder >= orders.size()){
            return null;
        }else return orders.get(currentOrder);
    }
    
    /**
     * Set the current order by index.
     * @param orderIndex the order to set. 
     */
    public void setCurrentOrder(int orderIndex){
        if(orderIndex < 0){
            orderIndex = -1;
            return;
        }
        
        currentOrder = orderIndex;
    }
    
    /**
     * Set the current order by reference.
     * This function has efficiency O(n).
     * @param order The order to set.
     */
    public void setCurrentOrder(Order order){
        for(int i = 0; i < orders.size(); i++){
            if(orders.get(i) == order){
                currentOrder = i;
                break;
            }
        }
    }
    
    /**
     * Return the selection index.as an int.
     * @return selectionIndex
     */
    public int getSelectionIndex(){
        return currentOrder;
    }
    
    /**
     * Delete an order by index.
     * This function will attempt to set the previous order to the current one.
     * @param index The index to delete.
     */
    public void deleteOrderByIndex(int index){
        orders.remove(index);
        //Try to set the current order to the one before the one being deleted.
        //If there are no more orders then set it to nothing.
        if(index == currentOrder) setCurrentOrder(index - 1);
    }
}
