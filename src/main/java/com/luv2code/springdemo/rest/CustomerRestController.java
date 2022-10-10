package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.exception.CustomerNotFoundException;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Can't find customer by id " + customerId);
        }
        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        //@RequestBody przyjmie przesyłanego jsona z danymi customera
        //ustawiamy customerId na 0, bo dalej używamu metody saveOrUpdate i jeśli id jest 'puste' (0 lub null), to tworzy nowego
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer passedCustomer) {
        Customer customer = customerService.getCustomer(passedCustomer.getId());
        if (customer == null) {
            throw new CustomerNotFoundException("Can't find customer by id " + customer.getId());
        }
        customerService.saveCustomer(passedCustomer);
        return passedCustomer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Can't find customer by id " + customerId);
        }
        customerService.deleteCustomer(customerId);
        return "Deleted customer with id " + customerId;
    }
}
