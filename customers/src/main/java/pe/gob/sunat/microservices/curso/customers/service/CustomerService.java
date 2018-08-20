package pe.gob.sunat.microservices.curso.customers.service;

import pe.gob.sunat.microservices.curso.customers.dao.AddressDaoImpl;
import pe.gob.sunat.microservices.curso.customers.dao.CustomerDaoImpl;
import pe.gob.sunat.microservices.curso.customers.model.Customer;

import java.util.Optional;

public class CustomerService {
  private final CustomerDaoImpl dao;
  private final AddressDaoImpl addressDao;

  public CustomerService(CustomerDaoImpl dao, AddressDaoImpl addressDao) {
    this.dao = dao;
    this.addressDao = addressDao;
  }

  public Customer create(Customer customer) {
    return dao.create(customer);
  }

  public Optional<Customer> findById(Long id, Boolean includeAddresses) {
    return dao.find(id).map(customer -> {
      if (includeAddresses) {
        customer.setAddresses(addressDao.findByCustomer(id));
      }
      return customer;
    });
  }

  public void delete(Long id) {
    dao.delete(id);
  }
}
