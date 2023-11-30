package com.ezdream.qrscanner.domain.coin

import com.ezdream.qrscanner.network.UseCase
import com.ezdream.qrscanner.repository.coinService.CoinRepository
import com.ezdream.qrscanner.repository.coinService.reqres.CoinDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinByIdUseCase @Inject constructor(private val repository: CoinRepository) :
    UseCase<String, CoinDetail>() {

    override fun execute(parameter: String?): Flow<CoinDetail> {

        return repository.getCoinById(parameter)
    }

}