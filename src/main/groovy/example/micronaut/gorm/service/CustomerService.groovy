package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Customer
import example.micronaut.gorm.model.CustomerModel

import javax.inject.Singleton


@Singleton
interface CustomerService {
    Customer addCustomer(CustomerModel customerModel)
    CustomerModel getCustomer(Long id)
    Customer updateCustomer(Long id, CustomerModel customerModel)
    def deleteCustomer(Long id)
}
