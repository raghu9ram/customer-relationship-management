package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Customer
import example.micronaut.gorm.domain.Interaction
import example.micronaut.gorm.domain.Representative
import example.micronaut.gorm.model.CustomerModel
import example.micronaut.gorm.model.InteractionModel
import example.micronaut.gorm.model.RepresentativeModel
import grails.gorm.transactions.Transactional

class InteractionServiceImpl implements InteractionService {

    @Transactional
    @Override
    Interaction addInteraction(InteractionModel interactionModel) {

        if(!interactionModel.customerModel?.id || !interactionModel.representativeModel?.id) {
            throw new IllegalArgumentException("Both customer and representative IDs must be given")
        }
        Customer customer = Customer.findById(interactionModel.customerModel.id)
        Representative representative = Representative.findById(interactionModel.representativeModel.id)

        if(!customer) {
            throw new IllegalArgumentException("Customer Not Found")
        }

        if(!representative) {
            throw new IllegalArgumentException("Representative Not Found")
        }

        Interaction interaction = new Interaction(
                contactType: interactionModel.contactType,
                details: interactionModel.details,
                date: interactionModel.date,
                customer: customer,
                representative: representative
        )
        interaction.save(flush: true)
        return interaction
    }

    @Transactional
    @Override
    InteractionModel getInteraction(Long id) {
        Interaction interaction = Interaction.get(id)
        if(!interaction) return null
        InteractionModel interactionModel = new InteractionModel(
                contactType:interaction.contactType,
                details: interaction.details,
                date: interaction.date,
                customerModel: interaction.customer ? new CustomerModel(
                        name: interaction.customer.name,
                        email: interaction.customer.email,
                        phone: interaction.customer.phone,
                        address: interaction.customer.address
                ) : null,
                representativeModel: interaction.representative ? new RepresentativeModel(
                        name: interaction.representative.name,
                        email: interaction.representative.email,
                        department: interaction.representative.department
                ) : null
        )

        return interactionModel
    }

    @Transactional
    @Override
    Interaction updateInteraction(Long id, InteractionModel interactionModel) {
        Interaction interaction = Interaction.get(id)
        if (!interaction) throw new IllegalArgumentException("Interaction not found")

        interaction.contactType = interactionModel.contactType
        interaction.details = interactionModel.details
        interaction.date = interactionModel.date

        if (interactionModel.customerModel?.id){
            Customer customer = Customer.get(interactionModel.customerModel.id)
            if (!customer) {
                throw new IllegalArgumentException("Customer Not Found")
            }
            interaction.customer = customer
        }
        if (interactionModel.representativeModel?.id){
            Representative representative = Representative.get(interactionModel.representativeModel.id)
            if (!representative) {
                throw new IllegalArgumentException("Representative Not Found")
            }
            interaction.representative = representative
        }
        interaction.save(flush: true)
        return interaction
    }

    @Transactional
    @Override
    def deleteInteraction(Long id) {
        Interaction interaction = Interaction.get(id)
        if(!interaction){
            throw new IllegalArgumentException("Interaction with ${id} not found")
        }
        interaction.delete(flush: true)
    }
}
