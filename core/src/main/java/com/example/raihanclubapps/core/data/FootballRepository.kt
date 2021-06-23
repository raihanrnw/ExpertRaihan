package com.example.raihanclubapps.core.data


import com.example.raihanclubapps.core.data.local.LocalDataSource
import com.example.raihanclubapps.core.data.remote.RemoteDataSource
import com.example.raihanclubapps.core.data.remote.network.ApiResponse
import com.example.raihanclubapps.core.data.remote.response.ClubResponse
import com.example.raihanclubapps.core.domain.Club
import com.example.raihanclubapps.core.domain.IFootballRepository
import com.example.raihanclubapps.core.utils.AppExecutors
import com.example.raihanclubapps.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FootballRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFootballRepository {

    override fun getAllClub(): Flow<Resource<List<Club>>> =
        object : NetworkBoundResource<List<Club>, List<ClubResponse>>() {
            override fun loadFromDB(): Flow<List<Club>> {
                return localDataSource.getAllClub().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Club>?): Boolean{
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ClubResponse>>> =
                remoteDataSource.getAllClub()

            override suspend fun saveCallResult(data: List<ClubResponse>) {
                val clubList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertClub(clubList)
            }
        }.asFlow()

    override fun getFavoriteClub(): Flow<List<Club>> {
        return localDataSource.getFavoriteClub().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteClub(club: Club, state: Boolean) {
        val clubEntity = DataMapper.mapDomainToEntity(club)
        appExecutors.diskIO().execute { localDataSource.setFavoriteClub(clubEntity, state) }
    }
}