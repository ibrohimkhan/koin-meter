package com.kodeco.koinmeter.ui.screens.topcoins

import androidx.lifecycle.ViewModel
import com.kodeco.koinmeter.domain.features.topcoins.GetTopCoinsUseCase

class TopCoinsViewModel(
    private val getTopCoinsUseCase: GetTopCoinsUseCase
) : ViewModel() {

}
