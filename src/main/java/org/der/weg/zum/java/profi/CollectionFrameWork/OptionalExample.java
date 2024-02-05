package org.der.weg.zum.java.profi.CollectionFrameWork;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class OptionalExample {

    private List<Customer> customerList;
    private static class Customer {
        private String name;
        private String Zipcode;
        private int id;

        public Customer(String name, String zipcode, int id) {
            this.name = name;
            Zipcode = zipcode;
            this.id = id;
        }
    }

    public OptionalExample(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Optional<Customer> findCustomer(final String name){
        for (Customer customer: customerList){
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
           ArrayList<Customer> list = new ArrayList<>();
           list.add(new Customer("Paul","12222",1));
           list.add(new Customer("Micheal","124442",2));
           list.add(new Customer("Jack","124442",3));

            new OptionalExample(list).findCustomer("Tim").
                    ifPresentOrElse(customer -> System.out.println(customer.name), () -> System.out.println("Customer not found"));


          final OptionalInt min = IntStream.empty().min();
          System.out.println(min.orElse(-1));

          final IntSupplier random = () -> (int) (100 * Math.random());
          System.out.println(min.orElseGet(random));

          //min.orElseThrow(()-> new NoSuchElementException("there is no minimum"));


    }
}
