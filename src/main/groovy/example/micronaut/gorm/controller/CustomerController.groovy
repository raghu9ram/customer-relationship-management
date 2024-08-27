package example.micronaut.gorm.controller

import example.micronaut.gorm.domain.Customer
import example.micronaut.gorm.model.CustomerModel
import example.micronaut.gorm.service.CustomerService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

import javax.inject.Inject

@Controller("/customers")
class CustomerController {
    @Inject
    CustomerService customerService

    CustomerController(CustomerService customerService) {
        this.customerService = customerService
    }

    @Post
    Customer create(@Body CustomerModel customerModel) {
        customerService.addCustomer(customerModel)
    }

    @Get("/{id}")
    CustomerModel get(@PathVariable Long id) {
        customerService.getCustomer(id)
    }

    @Put("/{id}")
    Customer put(@PathVariable Long id, @Body CustomerModel customerModel) {
        customerService.updateCustomer(customerModel)
    }

    @Delete("/{id}")
    HttpResponse<String> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id)
        return HttpResponse.ok("Customer Deleted Successfully")
    }
}
