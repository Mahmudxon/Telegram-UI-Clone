package uz.uniconsoft.messanger.business.domain.model

data class Country(
    var id: Long,
    var countryCode: String,
    var shortName: String,
    var countryName: String,
    var mask: String
)