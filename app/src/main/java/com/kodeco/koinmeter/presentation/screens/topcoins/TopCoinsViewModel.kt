package com.kodeco.koinmeter.presentation.screens.topcoins

import androidx.lifecycle.ViewModel
import com.kodeco.koinmeter.domain.usecase.topcoins.GetTopCoinsUseCase

class TopCoinsViewModel(
    private val getTopCoinsUseCase: GetTopCoinsUseCase
) : ViewModel() {

}
