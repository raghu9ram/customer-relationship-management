package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Customer
import example.micronaut.gorm.domain.Interaction
import example.micronaut.gorm.model.CustomerModel
import example.micronaut.gorm.model.InteractionModel
import grails.gorm.transactions.Transactional

class CustomerServiceImpl implements CustomerService {

    @Transactional
    @Override
    Customer addCustomer(CustomerModel customerModel) {
        Customer customer = new  Customer(
                name: customerModel.name,
                email: customerModel.email,
                phone: customerModel.phone,
                address: customerModel.address
        )
        customerModel.interactions.each { interactionModel ->
            Interaction interaction = new Interaction(
                    contactType: interactionModel.contactType,
                    details: interactionModel.details,
                    date: interactionModel.date,
                    customer: customer
            )
            //customer.interactions << interaction
            customer.addToInteractions(interaction)
        }
        customer.save(flush: true)
        return customer
    }

    @Transactional
    @Override
    CustomerModel getCustomer(Long id) {
        Customer customer = Customer.get(id)
        if(!customer) throw new IllegalArgumentException("Check your ID to get")

        CustomerModel customerModel = new CustomerModel(
                name: customer.name,
                email: customer.email,
                phone: customer.phone,
                address: customer.address,
                interactions: customer.interactions.collect { interaction ->
                    new InteractionModel(
                            contactType: interaction.contactType,
                            details: interaction.details,
                            date: interaction.date
                    )
                }
        )
        return customerModel
    }

    @Transactional
    @Override
    Customer updateCustomer(Long id, CustomerModel customerModel) {
        Customer customer = Customer.get(id)
        if(!customer) throw new IllegalArgumentException("Check the ID to Update")

        customer.name = customerModel.name
        customer.email = customerModel.email
        customer.phone = customerModel.phone
        customer.address = customerModel.address

        customer.interactions.clear()

        customerModel.interactions?.each { interactionModel ->
            Interaction interaction = new Interaction(
                    contactType: interactionModel.contactType,
                    details: interactionModel.details,
                    date: interactionModel.date
            )
            //customer.interactions << interaction
            customer.addToInteractions(interaction)
        }
        customer.save(flush: true)
        return customer
    }

    @Transactional
    @Override
    def deleteCustomer(Long id) {
        Customer customer = Customer.get(id)
        if(!customer){
            throw new IllegalArgumentException("Customer with ${id} not found")
        }
        customer.delete(flush: true)
    }
}
