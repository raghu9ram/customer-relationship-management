package example.micronaut.gorm.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import grails.gorm.annotation.Entity

@Entity
class Customer {
    String name
    String email
    String phone
    String address

    @JsonManagedReference
    static hasMany = [interactions: Interaction]

    static constraints = {
        name nullable: false
        email nullable: false
        phone nullable: false
        address nullable: true
    }

    static mapping = {
        interactions fetch: 'join'
    }
}
