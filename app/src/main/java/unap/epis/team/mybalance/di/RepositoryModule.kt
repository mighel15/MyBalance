package unap.epis.team.mybalance.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import unap.epis.team.mybalance.data.repository.BudgetRepository
import unap.epis.team.mybalance.data.repository.BudgetRepositoryImpl
import unap.epis.team.mybalance.data.repository.CategoryRepositoryImpl
import unap.epis.team.mybalance.data.repository.CategoryRepository
import unap.epis.team.mybalance.data.repository.ReportRepository
import unap.epis.team.mybalance.data.repository.ReportRepositoryImpl
import unap.epis.team.mybalance.data.repository.TransactionRepository
import unap.epis.team.mybalance.data.repository.TransactionRepositoryImpl
import unap.epis.team.mybalance.data.repository.UserRepository
import unap.epis.team.mybalance.data.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository


    @Binds
    @Singleton
    abstract fun bindTransactionRepository(impl: TransactionRepositoryImpl): TransactionRepository


    @Binds
    @Singleton
    abstract fun bindBudgetRepository(impl: BudgetRepositoryImpl): BudgetRepository


    @Binds
    @Singleton
    abstract fun bindReportRepository(impl: ReportRepositoryImpl): ReportRepository

}