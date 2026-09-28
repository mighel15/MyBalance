package unap.epis.team.mybalance.ui.screen.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import unap.epis.team.mybalance.ui.screen.budget.soles

import java.time.LocalDate

enum class TipoMovimiento { GASTO, INGRESO }

data class Categoria(
    val id: Int,
    val nombre: String,
    val color: Color
)

data class Movimiento(
    val id: Int,
    val tipo: TipoMovimiento,
    val monto: Double,
    val categoria: Categoria?,   // null cuando tipo == INGRESO
    val fuente: String?,        // null cuando tipo == GASTO
    val fecha: LocalDate,
    val nota: String = ""
)

data class PresupuestoCategoria(
    val categoria: Categoria,
    val montoLimite: Double,
    val montoGastado: Double
) {
    val progreso: Float
        get() = if (montoLimite <= 0) 0f else (montoGastado / montoLimite).toFloat().coerceIn(0f, 1.2f)

    val excedido: Boolean
        get() = montoGastado > montoLimite
}

data class GastoPorCategoria(val nombre: String, val monto: Double, val color: Color)
data class ComparativoMes(val mes: String, val total: Double)


// Categorias de ejemplo usadas en los previews de las 5 pantallas
object DatosDemo {
    val categorias = listOf(
        Categoria(1, "Comida", Color(0xFF3B6D11)),
        Categoria(2, "Transporte", Color(0xFF854F0B)),
        Categoria(3, "Ocio", Color(0xFFA32D2D)),
        Categoria(4, "Salud", Color(0xFF185FA5))
    )

    val movimientos = listOf(
        Movimiento(1, TipoMovimiento.INGRESO, 2500.0, null, "Sueldo", LocalDate.of(2026, 7, 1)),
        Movimiento(2, TipoMovimiento.GASTO, 18.0, categorias[0], null, LocalDate.of(2026, 7, 5), "Almuerzo"),
        Movimiento(3, TipoMovimiento.GASTO, 6.5, categorias[1], null, LocalDate.of(2026, 7, 5), "Bus")
    )

    val presupuestos = listOf(
        PresupuestoCategoria(categorias[0], 600.0, 480.0),
        PresupuestoCategoria(categorias[1], 200.0, 190.0),
        PresupuestoCategoria(categorias[2], 150.0, 210.0),
        PresupuestoCategoria(categorias[3], 150.0, 27.0)
    )
}


@Composable
fun ReportScreen(
    gastosPorCategoria: List<GastoPorCategoria> = DatosDemo.presupuestos.map {
        GastoPorCategoria(it.categoria.nombre, it.montoGastado, it.categoria.color)
    },
    comparativo: List<ComparativoMes> = listOf(
        ComparativoMes("Marzo", 1050.0),
        ComparativoMes("Abril", 1150.0),
        ComparativoMes("Mayo", 1250.0),
        ComparativoMes("Junio", 1050.0),
        ComparativoMes("Julio", 1240.0)
    ),
    mes: String = "julio"
) {
    val maximo = gastosPorCategoria.maxOf { it.monto + 50 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Reportes", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text(
            "Gasto por categoria \u2014 $mes",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            gastosPorCategoria.forEach { g ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val alturaRelativa = (g.monto / maximo).toFloat().coerceIn(0.05f, 1f)
                    Box(
                        modifier = Modifier
                            .width(36.dp)
                            .fillMaxHeight(alturaRelativa)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(g.color)
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(g.nombre, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Comparativo mensual", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Spacer(Modifier.height(8.dp))

        comparativo.forEachIndexed { index, c ->
            val esUltimo = index == comparativo.lastIndex
            val variacion = if (index > 0) {
                ((c.total - comparativo[index - 1].total) / comparativo[index - 1].total) * 100
            } else null

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(c.mes)
                Text(
                    soles.format(c.total) + (variacion?.let { " (%+.0f%%)".format(it) } ?: ""),
                    color = if (esUltimo && variacion != null && variacion > 0) Color(0xFFA32D2D) else Color.Unspecified,
                    fontWeight = if (esUltimo) FontWeight.Medium else FontWeight.Normal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReporteScreenPreview() {
    MaterialTheme { ReportScreen() }
}