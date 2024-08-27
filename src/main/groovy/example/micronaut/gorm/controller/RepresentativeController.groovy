package example.micronaut.gorm.controller

import example.micronaut.gorm.domain.Representative
import example.micronaut.gorm.model.RepresentativeModel
import example.micronaut.gorm.service.RepresentativeService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

import javax.inject.Inject

@Controller("/representatives")
class RepresentativeController {
    @Inject
    RepresentativeService representativeService

    RepresentativeController(RepresentativeService representativeService){
        this.representativeService = representativeService
    }

    @Post
    Representative post(@Body RepresentativeModel representativeModel){
        representativeService.addRepresentative(representativeModel)
    }

    @Get("/{id}")
    RepresentativeModel get(@PathVariable Long id) {
        representativeService.getRepresentative(id)
    }

    @Put("/{id}")
    Representative put(@PathVariable Long id, @Body RepresentativeModel representativeModel) {
        representativeService.updateRepresentative(id, representativeModel)
    }

    @Delete("/{id}")
    HttpResponse<String> delete(@PathVariable Long id){
        representativeService.deleteRepresentative(id)
        return HttpResponse.ok("Representative Deleted Succesfully")
    }
}
