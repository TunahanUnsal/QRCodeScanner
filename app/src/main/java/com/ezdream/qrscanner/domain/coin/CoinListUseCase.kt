package com.ezdream.qrscanner.domain.coin

import com.ezdream.qrscanner.network.UseCase
import com.ezdream.qrscanner.repository.coinService.CoinRepository
import com.ezdream.qrscanner.repository.coinService.reqres.Coin
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class CoinListUseCase @Inject constructor(private val repository: CoinRepository) :
    UseCase<String, Response<List<Coin>>>() {

    override fun execute(parameter: String?): Flow<Response<List<Coin>>> {
        return repository.getCoinList()
    }

}