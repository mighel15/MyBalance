package unap.epis.team.mybalance.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import unap.epis.team.mybalance.ui.screen.login.LoginViewModel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import unap.epis.team.mybalance.ui.commons.MovimientoRow
import unap.epis.team.mybalance.ui.commons.ResumenCard
import unap.epis.team.mybalance.ui.screen.budget.soles
import unap.epis.team.mybalance.ui.screen.home.add.AddModalScreen
import unap.epis.team.mybalance.ui.screen.report.DatosDemo
import unap.epis.team.mybalance.ui.screen.report.Movimiento
import unap.epis.team.mybalance.ui.screen.report.TipoMovimiento
import unap.epis.team.mybalance.ui.theme.MyBalanceTheme

@Composable
fun HomeScreen(
    navController: NavController? = null,
    movimientos: List<Movimiento> = DatosDemo.movimientos,
    nombreUsuario: String = "Anthony Steve",
    mes: String = "Julio",
    modifier: Modifier = Modifier
) {
    val totalIngresos = movimientos.filter { it.tipo == TipoMovimiento.INGRESO }.sumOf { it.monto }
    val totalGastos = movimientos.filter { it.tipo == TipoMovimiento.GASTO }.sumOf { it.monto }
    val balance = totalIngresos - totalGastos
    var mostraAgregar by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(20.dp).fillMaxSize()
    ) {
        Text("Hola, $nombreUsuario", style = MaterialTheme.typography.headlineSmall, color = Color.Gray)

        Spacer(Modifier.height(2.dp))

        Text(mes, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ResumenCard(
                titulo = "Ingresos",
                monto = totalIngresos,
                color = Color(0xFF3B6D11),
                icono = Icons.Filled.ArrowDownward,
                modifier = Modifier.weight(1f)
            )
            ResumenCard(
                titulo = "Gastos",
                monto = totalGastos,
                color = Color(0xFFA32D2D),
                icono = Icons.Filled.ArrowUpward,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(12.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE6F1FB)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(14.dp)) {
                Text("Balance del mes", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
                Text(
                    soles.format(balance),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF185FA5),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        Row(modifier.fillMaxWidth()) {
            Text("Movimientos recientes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            Text("Agregar", color = Color.Blue, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.End, modifier = Modifier.weight(1f).clickable(onClick = { mostraAgregar = true }))

        }
        Spacer(Modifier.height(4.dp))

        LazyColumn {
            items(movimientos.sortedByDescending { it.fecha }) { mov ->
                MovimientoRow(mov)
                HorizontalDivider()
            }
        }

    }

    AddModalScreen(
        visible = mostraAgregar,
        onDismiss = { mostraAgregar = false },
        onGuardar = { mostraAgregar = false }
    )
}



@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MyBalanceTheme { HomeScreen() }
}