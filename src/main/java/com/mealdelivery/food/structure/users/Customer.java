package com.mealdelivery.food.structure.users;

import com.mealdelivery.food.structure.delivery.Address;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

public class Customer extends User {

    List<Address> addressBook;
    LinkedHashMap<Integer, String[]> userOrders;

    public Customer(Integer id, String name, String email, Integer phone, String hashedPassword) {
        super(id, name, email, phone, hashedPassword);
    }

    public List<Address> getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(Address address) {
        this.addressBook.add(address);
    }

    public String[] getUserOrder(Integer key) {
        if(userOrders.containsKey(key)) {
            return userOrders.get(key);
        } else return new String[]{"No Entry"};
    }

    public void setUserOrders(Integer orderId, List<String> positions, Runner courier, double price,
                              Timestamp orderPlacedAt, Timestamp orderTossedToDelivery,Timestamp orderDelivered) {
        String[] orderData = {positions.toString(),
                              String.valueOf(price),
                              String.valueOf(orderPlacedAt),
                              String.valueOf(orderTossedToDelivery),
                              String.valueOf(orderDelivered),
                              String.valueOf(courier)};
        this.userOrders.put(orderId,orderData);
    }
}
