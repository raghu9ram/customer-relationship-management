package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Interaction
import example.micronaut.gorm.domain.Representative
import example.micronaut.gorm.model.InteractionModel
import example.micronaut.gorm.model.RepresentativeModel
import grails.gorm.transactions.Transactional

class RepresentativeServiceImpl implements RepresentativeService{

    @Transactional
    @Override
    Representative addRepresentative(RepresentativeModel representativeModel) {
        Representative representative = new Representative(
                name: representativeModel.name,
                email: representativeModel.email,
                department: representativeModel.department
        )
        representativeModel.interactions.each { interactionModel ->
            Interaction interaction = new Interaction(
                    contactType: interactionModel.contactType,
                    details: interactionModel.details,
                    date: interactionModel.date,
                    representative: representative
            )
            //representative.interactions << interaction
            representative.addToInteractions(interaction)
        }
        representative.save(flush: true)
        return representative
    }

    @Transactional
    @Override
    RepresentativeModel getRepresentative(Long id) {
        Representative representative = Representative.get(id)
        if(!representative) throw new IllegalArgumentException("Check your ID to get")

        RepresentativeModel representativeModel = new RepresentativeModel(
                name: representative.name,
                email: representative.email,
                department: representative.department,
                interactions: representative.interactions.collect { interaction ->
                    new InteractionModel(
                            contactType: interaction.contactType,
                            details: interaction.details,
                            date: interaction.date
                    )
                }
        )
        return representativeModel

    }

    @Transactional
    @Override
    Representative updateRepresentative(Long id, RepresentativeModel representativeModel) {
        Representative representative = Representative.get(id)
        if(!representative) throw new IllegalArgumentException("Check the ID to Update")
        representative.name = representativeModel.name
        representative.email = representativeModel.email
        representative.department = representativeModel.department

        representative.interactions.clear()
        representativeModel.interactions?.each { interactionModel ->
            Interaction interaction = new Interaction(
                    contactType: interactionModel.contactType,
                    details:  interactionModel.details,
                    date: interactionModel.date
            )
            //representative.interactions << interaction
            representative.addToInteractions(interaction)
        }
        representative.save(flush: true)
        return representative
    }

    @Override
    def deleteRepresentative(Long id) {
        Representative representative = Representative.get(id)
        if(!representative) throw new IllegalArgumentException("Representative with ${id} not found")
        representative.delete(flush: true)
    }
}
