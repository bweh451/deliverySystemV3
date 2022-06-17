package quickFoodMS;

import java.util.ArrayList;
/**
 * Class that represents order items. 
 *
 * @author Brandon Sherriff
 */

public class OrderItems {
	
	private int orderNum;
	private ArrayList <String> itemNames = new ArrayList <>();
	private ArrayList <Integer> itemQtys = new ArrayList <>();
	private ArrayList <Double> itemPrices = new ArrayList <>();
	private int count;

	/**
	 * Method that creates order items. 
	 *
	 * @param orderNum, the order number the order items are associated with
	 * @param itemNames, a list of the order item names
	 * @param itemQtys, a list of the quantities of the order items 
	 * @param itemPrices, a list of the prices of the order items
	 * @param count, a counter used to count how many order items there are
	 */
	public OrderItems (int orderNum, ArrayList <String> itemNames, ArrayList <Integer> itemQtys, ArrayList <Double> itemPrices, int count){
		this.setOrderNum(orderNum);
		this.setItemNames(itemNames);
		this.setItemQtys(itemQtys);
		this.setItemPrices(itemPrices);
		this.setCount(count);
	}

	/**
	 * Method that retrieves the order number the order items are associated with
	 * @return orderNum, the order number the order items are associated with
	 */
	public int getOrderNum() {
		return orderNum;
	}

	/**
	 * Method that sets the order number the order items are associated with
	 * @param orderNum, the order number the order items are associated with
	 */
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * Method that retrieves a list of the order item names
	 * @return itemNames, a list of the order item names
	 */
	public ArrayList<String> getItemNames() {
		return itemNames;
	}

	/**
	 * Method that sets a list of the order item names
	 * @param itemNames, a list of the order item names
	 */
	public void setItemNames(ArrayList<String> itemNames) {
		this.itemNames = itemNames;
	}

	/**
	 * Method that retrieves a list of the quantities of the order items 
	 * @return itemQtys, a list of the quantities of the order items 
	 */
	public ArrayList<Integer> getItemQtys() {
		return itemQtys;
	}

	/**
	 * Method that sets a list of the quantities of the order items 
	 * @param itemQtys, a list of the quantities of the order items 
	 */
	public void setItemQtys(ArrayList<Integer> itemQtys) {
		this.itemQtys = itemQtys;
	}

	/**
	 * Method that retrieves a list of the prices of the order items
	 * @return itemPrices, a list of the prices of the order items
	 */
	public ArrayList<Double> getItemPrices() {
		return itemPrices;
	}

	/**
	 * Method that sets a list of the prices of the order items
	 * @param itemPrices, a list of the prices of the order items
	 */
	public void setItemPrices(ArrayList<Double> itemPrices) {
		this.itemPrices = itemPrices;
	}
	
	/**
	 * Method that retrieves a counter used to count how many order items there are
	 * @return count, a counter used to count how many order items there are
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Method that sets a counter used to count how many order items there are
	 * @param count, a counter used to count how many order items there are
	 */
	public void setCount(int count) {
		this.count = count;
	}

}
