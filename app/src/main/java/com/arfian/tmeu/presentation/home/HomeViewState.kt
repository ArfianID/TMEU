package com.arfian.tmeu.presentation.home

import com.arfian.tmeu.domain.model.Sales

data class HomeViewState (
    val isLoading: Boolean = false,
    val products: List<Sales> = emptyList(),
    val error: String? = null
)