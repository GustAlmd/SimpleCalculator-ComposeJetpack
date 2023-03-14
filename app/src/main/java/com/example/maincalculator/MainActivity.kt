package com.example.maincalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun CalculatoraScreen(){
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var isClickedPlus by remember { mutableStateOf(false) }
    var isClickedMinus by remember { mutableStateOf(false) }
    var isClickedMultiply by remember { mutableStateOf(false) }
    var isClickedDivide by remember { mutableStateOf(false) }

    val size by animateDpAsState(
        if (isClickedPlus) 40.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000)
    )

    val size1 by animateDpAsState(
        if (isClickedMinus) 40.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000)
    )

    val size2 by animateDpAsState(
        if (isClickedMultiply) 40.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000)
    )

    val size3 by animateDpAsState(
        if (isClickedDivide) 40.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000)
    )

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = value1,
            onValueChange = { value1 = it },
            label = { Text(text = "Valor 1")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = value2,
            onValueChange = { value2 = it },
            label = { Text(text = "Valor 2")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            Modifier
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)) {
            Button(onClick = { operator = "+"
                isClickedMinus = false
                isClickedMultiply = false
                isClickedDivide = false
                isClickedPlus = !isClickedPlus}, modifier = Modifier.padding(5.dp) ){
                Text(text = "+",  modifier = Modifier.align(Alignment.CenterVertically))
                Box(
                    modifier = Modifier
                        .size(size)
                )
            }
            Button(onClick = { operator = "-"
                isClickedPlus = false
                isClickedMultiply = false
                isClickedDivide = false
                isClickedMinus = !isClickedMinus}, modifier = Modifier.padding(5.dp)){
                Text(text = "-",  modifier = Modifier.align(Alignment.CenterVertically))
                Box(
                    modifier = Modifier
                        .size(size1)
                )
            }
            Button(onClick = { operator = "*"
                isClickedPlus = false
                isClickedMinus = false
                isClickedDivide = false
                isClickedMultiply = !isClickedMultiply}, modifier = Modifier.padding(5.dp)){
                Text(text = "*",  modifier = Modifier.align(Alignment.CenterVertically))
                Box(
                    modifier = Modifier
                        .size(size2)
                )
            }
            Button(onClick = { operator = "/"
                isClickedPlus = false
                isClickedMinus = false
                isClickedMultiply = false
                isClickedDivide = !isClickedDivide}, modifier = Modifier.padding(5.dp)){
                Text(text = "/",  modifier = Modifier.align(Alignment.CenterVertically))
                Box(
                    modifier = Modifier
                        .size(size3)
                )
            }
        }

        Row(
            Modifier
                .padding(vertical = 10.dp)
                .align(CenterHorizontally)) {
            Button(modifier = Modifier.padding(5.dp),onClick = {
                if(value1.isNotEmpty() && value2.isNotEmpty() && operator.isNotEmpty()){
                    result = when (operator){
                        "+" -> (value1.toDouble() + value2.toDouble()). toString()
                        "-" -> (value1.toDouble() - value2.toDouble()). toString()
                        "*" -> (value1.toDouble() * value2.toDouble()). toString()
                        "/" -> (value1.toDouble() / value2.toDouble()). toString()
                        "%" -> (value1.toDouble() % value2.toDouble()). toString()
                        else -> ""
                    }
                }
            }) {
                Text(text = "CALCULAR")
            }
            Button(modifier = Modifier.padding(5.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),onClick = {
                value1 = ""
                value2 = ""
                operator = ""
                result = ""
                isClickedDivide = false
                isClickedMinus = false
                isClickedMultiply = false
                isClickedPlus = false
            }) {
                Text(text = "LIMPAR")
            }
        }

            if (result.isNotEmpty()){
                Button(modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(CenterHorizontally), onClick = {

                }) {
                    Text(text = "RESULTADO : $result")
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatoraScreen()
}

@Composable
fun MyBottomBar(){
    BottomAppBar(modifier = Modifier
        .fillMaxWidth(),
        ) {
        Text(text = "Made by Gustavo de Almeida", color = Color.White, fontSize = 15.sp)
    }
}

@Composable
fun MyTopBar(){
    TopAppBar(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFFBB86FC)),
    ) {
        Text(text = "Calculadora",color = Color.White, fontSize = 30.sp)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    Scaffold(
        topBar = {MyTopBar()} ,
        bottomBar = { MyBottomBar()}) {
        CalculatoraScreen()
    }
}