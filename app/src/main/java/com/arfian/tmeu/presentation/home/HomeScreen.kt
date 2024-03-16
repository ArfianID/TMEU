package com.arfian.tmeu.presentation.home

import DecimalInputVisualTransformation
import android.content.Context
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
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arfian.tmeu.util.DecimalFormatter

@Composable
internal fun HomeScreen(){
    val viewModel = HomeViewModel()
    HomeContent(viewModel)
}

@Composable
fun HomeContent(viewModel: HomeViewModel) {
    val salesData by viewModel.data.collectAsStateWithLifecycle()
    val decimalFormatter = DecimalFormatter()
    var showDialog by remember { mutableStateOf(false) }

    Column {
        // A text field for entering the sales net
        OutlinedTextField(
            value = salesData.salesNet.toString(),
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0
                viewModel.setSalesNet(newValue)
            },
            label = { Text(text = "Sales Net") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            prefix = { Text(text = "Rp") },
            visualTransformation = DecimalInputVisualTransformation(decimalFormatter),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // A text field for entering the number of receipts
        OutlinedTextField(
            value = salesData.struk.toString(),
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0
                viewModel.setStruk(newValue)
            },
            label = { Text(text = "Struk") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            prefix = { Text(text = "") },
            visualTransformation = DecimalInputVisualTransformation(decimalFormatter),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // A text field for entering the number of variants
        OutlinedTextField(
            value = salesData.varian.toString(),
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0
                viewModel.setVarian(newValue)
            },
            label = { Text(text = "Varian") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            prefix = { Text(text = "Rp") },
            visualTransformation = DecimalInputVisualTransformation(decimalFormatter),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // A text field for entering the number of replacements
        OutlinedTextField(
            value = salesData.penggantian.toString(),
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0
                viewModel.setPenggantian(newValue)
            },
            label = { Text(text = "Penggantian") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            prefix = { Text(text = "Rp") },
            visualTransformation = DecimalInputVisualTransformation(decimalFormatter),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // A text field for entering the target
        OutlinedTextField(
            value = salesData.target.toString(),
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0
                viewModel.setTarget(newValue)
            },
            label = { Text(text = "Target") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            prefix = { Text(text = "Rp") },
            visualTransformation = DecimalInputVisualTransformation(decimalFormatter),
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

    if (showDialog) {
        GenerateResultDialog(
            onDismiss = { showDialog = false },
            salesData = salesData,
            context = LocalContext.current
        )
    }
}

@Composable
fun GenerateResultDialog(
    onDismiss: () -> Unit,
    salesData: HomeViewModel.Data,
    context: Context
) {
    val clipboardManager = LocalClipboardManager.current
    val contentDialog = AnnotatedString.Builder(
        "LAPORAN SALES\n" +
                "TMEU.MANDUNG\n" +
                "Tgl :9/3/2024\n" +
                "Target : ${salesData.target}\n" +
                "Ach: ${salesData.ach}%\n" +
                "\n" +
                "SALES Net : ${salesData.salesNet}\n" +
                "Struk: ${salesData.struk}\n" +
                "Apc : ${salesData.apc}\n" +
                "Varian :+ ${salesData.varian}\n" +
                "Penggantian: ${salesData.penggantian}\n" +
                "SPD : ${salesData.spd}/${salesData.spdPercentage}%\n" +
                "STD : ${salesData.std}/${salesData.stdPercentage}%.\n" +
                "APC : ${salesData.apc1}/${salesData.apc1Percentage}%\n" +
                "Margin : ${salesData.margin}/${salesData.marginPercentage}%\n" +
                "Sales lpptk : ${salesData.salesLpptk}\n" +
                "Akm sales : ${salesData.akmSales}\n" +
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
                "margin total : 0"
    ).toAnnotatedString()

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
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = contentDialog,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .padding(16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            clipboardManager.setText(contentDialog)
                            Toast.makeText(context, "Text copied", Toast.LENGTH_SHORT).show()
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