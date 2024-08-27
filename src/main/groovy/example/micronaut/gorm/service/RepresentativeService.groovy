package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Representative
import example.micronaut.gorm.model.RepresentativeModel

import javax.inject.Singleton

@Singleton
interface RepresentativeService {
    Representative addRepresentative(RepresentativeModel representativeModel)
    RepresentativeModel getRepresentative(Long id)
    Representative updateRepresentative(Long id, RepresentativeModel representativeModel)
    def deleteRepresentative(Long id)
}
