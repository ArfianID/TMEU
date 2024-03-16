package com.arfian.tmeu.presentation.home

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    HomeContent(viewModel)
}

@Composable
fun HomeContent(viewModel: HomeViewModel) {
    val salesData by viewModel.data.collectAsStateWithLifecycle()

    val target = salesData.target.toString()
    val ach = salesData.ach.toString()
    val salesNet = salesData.salesNet.toString()
    val struk = salesData.struk.toString()
    val apc = salesData.apc.toString()
    val varian = salesData.varian.toString()
    val penggantian = salesData.penggantian.toString()
    val spd = salesData.spd.toString()
    val spdPercentage = salesData.spdPercentage.toString()
    val std = salesData.std.toString()
    val stdPercentage = salesData.stdPercentage.toString()
    val apc1 = salesData.apc1.toString()
    val apc1Percentage = salesData.apc1Percentage.toString()
    val margin = salesData.margin.toString()
    val marginPercentage = salesData.marginPercentage.toString()
    val salesLpptk = salesData.salesLpptk.toString()
    val akmSales = salesData.akmSales.toString()

    // State to control the visibility of the dialog
    var showDialog by remember { mutableStateOf(false) }

    // Show the dialog if showDialog is true
    if (showDialog) {
        GenerateResultDialog(
            onDismiss = { showDialog = false },
            target = target,
            ach = ach,
            salesNet = salesNet,
            struk = struk,
            apc = apc,
            varian = varian,
            penggantian = penggantian,
            spd = spd,
            spdPercentage = spdPercentage,
            std = std,
            stdPercentage = stdPercentage,
            apc1 = apc1,
            apc1Percentage = apc1Percentage,
            margin = margin,
            marginPercentage = marginPercentage,
            salesLpptk = salesLpptk,
            akmSales = akmSales
        )
    }

    Column {
        // A text field for entering the sales net
        OutlinedTextField(
            value = salesNet,
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0 // Handle invalid input
                viewModel.setSalesNet(newValue)
            },
            label = { Text(text = "Input jumlah sales Net!") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // A text field for entering the number of receipts
        OutlinedTextField(
            value = struk,
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0 // Handle invalid input
                viewModel.setStruk(newValue)
            },
            label = { Text(text = "Input jumlah struk!") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // A text field for entering the number of variants
        OutlinedTextField(
            value = varian,
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0 // Handle invalid input
                viewModel.setVarian(newValue)
            },
            label = { Text(text = "Input jumlah varian!") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // A text field for entering the number of replacements
        OutlinedTextField(
            value = penggantian,
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0 // Handle invalid input
                viewModel.setPenggantian(newValue)
            },
            label = { Text(text = "Input jumlah penggantian!") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // A text field for entering the target
        OutlinedTextField(
            value = target,
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0 // Handle invalid input
                viewModel.setTarget(newValue)
            },
            label = { Text(text = "Input jumlah target!") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        Button(
            onClick = { showDialog = true }, modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text(text = "Generate")
        }
    }
}

@Composable
fun GenerateResultDialog(
    onDismiss: () -> Unit,
    target: String,
    ach: String,
    salesNet: String,
    struk: String,
    apc: String,
    varian: String,
    penggantian: String,
    spd: String,
    spdPercentage: String,
    std: String,
    stdPercentage: String,
    apc1: String,
    apc1Percentage: String,
    margin: String,
    marginPercentage: String,
    salesLpptk: String,
    akmSales: String
) {
    val openDialog by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val contentDialog = AnnotatedString.Builder(
        "LAPORAN SALES\n" +
                "TMEU.MANDUNG\n" +
                "Tgl :9/3/2024\n" +
                "Target : $target\n" +
                "Ach : $ach%\n" +
                "\n" +
                "SALES Net : $salesNet\n" +
                "Struk: $struk\n" +
                "Apc : $apc\n" +
                "Varian :+ $varian\n" +
                "Penggantian: $penggantian\n" +
                "SPD : $spd/$spdPercentage%\n" +
                "STD : $std/$stdPercentage%.\n" +
                "APC : $apc1/$apc1Percentage%\n" +
                "Margin : $margin/$marginPercentage%\n" +
                "com.arfian.tmeu.domain.model.Sales lpptk : $salesLpptk\n" +
                "Akm sales : $akmSales\n" +
                "\n" +
                " Audit : \n" +
                "Nk:-Rp\n" +
                "NL: +Rp\n" +
                "\n" +
                "__+\n" +
                "NkL : \n" +
                "Bugget : \n" +
                "Itt :\n" +
                "\n" +
                "3. NBR\n" +
                "Dry :0\n" +
                "Buah: / %\n" +
                "4. Mr. Bread\n" +
                "margin total : 695.000"
    ).toAnnotatedString()

    if (openDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier
                        .heightIn(300.dp)
                        .padding(16.dp)
                        .verticalScroll(scrollState)
                ) {
                    androidx.compose.foundation.text.selection.SelectionContainer {
                        Text(
                            text = contentDialog,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray)
                                .padding(16.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                clipboardManager.setText(contentDialog)
                                Toast.makeText(context, "Text copied", Toast.LENGTH_SHORT)
                                    .show()
                            },
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 16.dp)
                        ) {
                            Text("Copy")
                        }

                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text("Keluar")
                        }
                    }
                }


            }
        }
    }
}
