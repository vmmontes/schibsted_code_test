package com.vmmontes.excurrency.di

import com.vmmontes.exchange.client.HistoryClient
import com.vmmontes.exchange.client.HistoryClientImp
import com.vmmontes.excurrency.data.datasource.cloud.HistoryCloudDataSource
import com.vmmontes.excurrency.data.datasource.cloud.HistoryCloudDataSourceImp
import com.vmmontes.excurrency.data.datasource.local.HistoryLocalDataSource
import com.vmmontes.excurrency.data.datasource.local.HistoryLocalDataSourceImp
import com.vmmontes.excurrency.data.repository.HistoryRepository
import com.vmmontes.excurrency.data.repository.HistoryRepositoryImp
import com.vmmontes.excurrency.domain.GetHistoryUseCase
import com.vmmontes.excurrency.domain.ProvideHistoryRangeDatesUseCase
import com.vmmontes.excurrency.domain.SortHistoryDatesUseCase
import com.vmmontes.excurrency.presentation.presenter.graphic.GraphicPresenter
import dagger.Module
import dagger.Provides

@Module
class GraphicModule {

    @Provides
    fun provideHistoryClient(): HistoryClient = HistoryClientImp()

    @Provides
    fun provideCloudDataSource(historyClient: HistoryClient) : HistoryCloudDataSource =
        HistoryCloudDataSourceImp(historyClient)

    @Provides
    fun provideHistoryLocalDataSource(): HistoryLocalDataSource = HistoryLocalDataSourceImp()

    @Provides
    fun provideHistoryRepository(historyCloudDataSource: HistoryCloudDataSource,
                                 historyLocalDataSource: HistoryLocalDataSource
    ): HistoryRepository = HistoryRepositoryImp(historyCloudDataSource, historyLocalDataSource)

    @Provides
    fun providesProvideHistoryRangeDatesUseCase(historyRepository: HistoryRepository): ProvideHistoryRangeDatesUseCase =
        ProvideHistoryRangeDatesUseCase(historyRepository)

    @Provides
    fun providesSortHistoryDatesUseCase(): SortHistoryDatesUseCase = SortHistoryDatesUseCase()

    @Provides
    fun providesGetHistoryUseCase(historyRepository: HistoryRepository, sortHistoryDatesUseCase: SortHistoryDatesUseCase): GetHistoryUseCase =
        GetHistoryUseCase(historyRepository, sortHistoryDatesUseCase)

    @Provides
    fun provideGraphPresenter(getHistoryUseCase: GetHistoryUseCase,
                              provideHistoryRangeDatesUseCase: ProvideHistoryRangeDatesUseCase): GraphicPresenter =
        GraphicPresenter(getHistoryUseCase, provideHistoryRangeDatesUseCase)
}