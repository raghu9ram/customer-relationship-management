package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Interaction
import example.micronaut.gorm.model.InteractionModel
import grails.gorm.transactions.Transactional

import javax.inject.Singleton

@Singleton
@Transactional
interface InteractionService {
    Interaction addInteraction(InteractionModel interactionModel)
    InteractionModel getInteraction(Long id)
    Interaction updateInteraction(Long id, InteractionModel interactionModel)
    def deleteInteraction(Long id)
}
