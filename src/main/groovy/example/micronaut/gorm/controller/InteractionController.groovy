package example.micronaut.gorm.controller

import example.micronaut.gorm.domain.Interaction
import example.micronaut.gorm.model.InteractionModel
import example.micronaut.gorm.service.InteractionService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

import javax.inject.Inject

@Controller("/interactions")
class InteractionController {
    @Inject
    InteractionService interactionService
    InteractionController(InteractionService interactionService){
        this.interactionService = interactionService
    }

    @Post
    Interaction create(@Body InteractionModel interactionModel) {
        interactionService.addInteraction(interactionModel)
    }

    @Get("/{id}")
    InteractionModel get(@PathVariable Long id) {
        interactionService.getInteraction(id)
    }

    @Put("/{id}")
    Interaction put(@PathVariable Long id, @Body InteractionModel interactionModel) {
        interactionService.updateInteraction(id, interactionModel)
    }

    @Delete("/{id}")
    HttpResponse<String> delete(@PathVariable Long id){
        interactionService.deleteInteraction(id)
        return HttpResponse.ok("Interaction deleted successfully")
    }
}
