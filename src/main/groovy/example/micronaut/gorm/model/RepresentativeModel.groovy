package example.micronaut.gorm.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference

@JsonInclude(JsonInclude.Include.NON_NULL)
class RepresentativeModel {
    Long id
    String name
    String email
    String department

    @JsonManagedReference("representativeInteractions")
    List<InteractionModel> interactions = []

//    static constraints = {
//        id nullable: true
//        name nullable: false
//        email nullable: false
//        department nullable: false
//    }
}
