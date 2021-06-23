package com.example.raihanclubapps.core.utils


import com.example.raihanclubapps.core.data.local.ClubEntity
import com.example.raihanclubapps.core.data.remote.response.ClubResponse
import com.example.raihanclubapps.core.domain.Club

object DataMapper {
    fun mapResponsesToEntities(input: List<ClubResponse>): List<ClubEntity> {
        val clubList = ArrayList<ClubEntity>()
        input.map {
            val club = ClubEntity(
               it.id,
                it.name,
                it.description,
                it.stadium,
               it.logo,
                isFavorite = false
            )
            clubList.add(club)
        }
        return clubList
    }

    fun mapEntitiesToDomain(input: List<ClubEntity>): List<Club> =
        input.map {
            Club(
               it.clubId,
             it.name,
                it.description,
                 it.stadium,
                 it.logo,
                it.isFavorite
            )
        }


    fun mapDomainToEntity(input: Club) = ClubEntity(
       input.clubId,
        input.name,
       input.description,
       input.stadium,
       input.logo,
        isFavorite = input.isFavorite
    )
}