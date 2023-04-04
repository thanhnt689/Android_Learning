package com.ntt.androidjetpack.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ntt.androidjetpack.model.Passenger
import java.lang.Exception

class PassengerDataSource(private val passengerApi: PassengerApi) : PagingSource<Int, Passenger>() {
    override fun getRefreshKey(state: PagingState<Int, Passenger>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {
            val nextPage = params.key ?: 0
            val response = passengerApi.getPassenger(page = nextPage, size = 20)
            LoadResult.Page(
                data = response.data,
                nextKey = if (nextPage < response.totalPages) nextPage + 1 else null,
                prevKey = if (nextPage > 0) nextPage - 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}