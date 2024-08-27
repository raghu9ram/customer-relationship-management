package example.micronaut.gorm.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import grails.gorm.annotation.Entity

@Entity
class Interaction {
    String contactType
    String details
    Date date

    @JsonBackReference
    Customer customer

    @JsonBackReference
    Representative representative

    static belongsTo = [customer: Customer, representative: Representative]

    static constraints = {
        contactType nullable: false
        details nullable: false
        date nullable: false
        customer(nullable: false)
        representative(nullable: false)
    }



}
