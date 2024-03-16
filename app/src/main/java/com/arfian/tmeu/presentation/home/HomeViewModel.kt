package com.arfian.tmeu.presentation.home

import androidx.lifecycle.ViewModel
import com.arfian.tmeu.domain.repository.SalesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val salesRepository: SalesRepository) : ViewModel() {
    private val _data = MutableStateFlow(Data(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
    val data: StateFlow<Data> = _data

    fun setSalesNet(value: Int) {
        _data.value = _data.value.copy(salesNet = value)
    }

    fun setStruk(value: Int) {
        _data.value = _data.value.copy(struk = value)
    }

    fun setVarian(value: Int) {
        _data.value = _data.value.copy(varian = value)
    }

    fun setPenggantian(value: Int) {
        _data.value = _data.value.copy(penggantian = value)
    }

    fun setTarget(value: Int) {
        _data.value = _data.value.copy(target = value)
    }

    fun calculateAndUpdateSales() {
        /**TODO*/
//        val akmSales = _data.value.salesNet + previeusDays()

        val apc = _data.value.salesNet / _data.value.struk
        _data.value = _data.value.copy(apc = apc)

        val spd =  _data.value.akmSales / getCurrentDate()
        _data.value = _data.value.copy(spd = spd)

        val ach = (_data.value.spd) / _data.value.target * 100
        _data.value = _data.value.copy(ach = ach)
    }

    private fun getCurrentDate(): Int {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd")
        val formatted = today.format(formatter)
        return formatted.toInt()
    }

    data class Data(
        val target: Int,
        val ach: Int,
        val salesNet: Int,
        val struk: Int,
        val apc: Int,
        val varian: Int,
        val penggantian: Int,
        val spd: Int,
        val spdPercentage: Int,
        val std: Int,
        val stdPercentage: Int,
        val apc1: Int,
        val apc1Percentage: Int,
        val spdLastMonth: Int,
        val stdLastMonth: Int,
        val apc1LastMonth: Int,
        val margin: Int,
        val marginPercentage: Int,
        val salesLpptk: Int,
        val akmSales: Int
    )
}