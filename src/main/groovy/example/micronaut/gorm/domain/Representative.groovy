package example.micronaut.gorm.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import grails.gorm.annotation.Entity

@Entity
class Representative {
    String name
    String email
    String department

    @JsonManagedReference
    static hasMany = [interactions: Interaction]

    static constraints = {
        name nullable: false
        email nullable: false
        department nullable: false
    }

    static mapping = {
        interactions fetch: 'join'
    }
}
