package example.micronaut.gorm.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference

@JsonInclude(JsonInclude.Include.NON_NULL)
class CustomerModel {
    Long id
    String name
    String email
    String phone
    String address

    @JsonManagedReference("customerInteractions")
    List<InteractionModel> interactions = []

//    static constraints = {
//        id nullable: true
//        name nullable: false
//        email nullable: false
//        phone nullable: false
//        address nullable: true
//    }

    static mapping = {
        interactions fetch: 'join'
    }
}
