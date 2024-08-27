package example.micronaut.gorm.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class InteractionModel {
    Long id
    String contactType
    String details
    Date date

    @JsonBackReference("customerInteractions")
    CustomerModel customerModel

    @JsonBackReference("representativeInteractions")
    RepresentativeModel representativeModel

//    static constraints = {
//        contactType nullable: false
//        details nullable: false
//        date nullable: false
//    }
}
