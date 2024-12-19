package com.artofcode.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artofcode.calculator.ui.theme.CalculatorTheme
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator(){
    var isDropdown by remember{mutableStateOf(false)}
    var dis1 by remember{ mutableStateOf("")}
    var dis2 by remember{ mutableStateOf("")}
    var number by remember { mutableStateOf("")}
    var numberD by remember { mutableStateOf(0.0)}
    var ansD by remember{ mutableStateOf(0.0)}
    var ans by remember { mutableStateOf("")}

    fun Operation(expression:String):String{
        var Ans=""
        try {
            Ans= Expression(expression
                .replace("÷","/")
                .replace("×","*")
                .replace("²","^2")
            ).calculate().toString()
        }catch (e:Exception){
            e.printStackTrace()
            return ""
        }
        return Ans
    }

Column(modifier = Modifier
    .fillMaxSize()
    .background(Color.Black)
    .padding(8.dp)
){
    Column(modifier= Modifier
        .weight(1f)
        .fillMaxSize()
        .border(
            width = 1.dp,
            color = Color.White,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            )
        ),
        horizontalAlignment = Alignment.End
    ){
        Text(text = dis1,
            maxLines = 2,
            modifier = Modifier
                .weight(1f)
                .padding(4.dp,8.dp),
            style = if(dis1.length<17){
                TextStyle(fontSize = 35.sp,
                    textAlign = TextAlign.End,
                    color = Color.White)
            }else{
                TextStyle(fontSize = 25.sp,
                    textAlign = TextAlign.End,
                    color = Color.White)
            }
        )
        if(isDropdown==false){
            Spacer(modifier = Modifier.height(16.dp))
        }else{

        }
        Text(text = dis2, style = if(isDropdown){
            TextStyle(fontSize = 50.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = Color.White)
                                                }else{
            TextStyle(fontSize = 60.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = Color.White)
                                                     },
            maxLines = 1,
            modifier =if (isDropdown){
                Modifier.padding(4.dp,8.dp)
            }else{
                Modifier.padding(4.dp,16.dp)
            }
        )
    }
    if(isDropdown){
        Button(onClick = {isDropdown=false}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription ="",
                tint = Color.White,
                modifier = Modifier.size(50.dp))
        }
    }else{
        Button(onClick = {isDropdown=true}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
            Icon(
                Icons.Default.KeyboardArrowUp,
                contentDescription ="",
                tint = Color.White,
                modifier = Modifier.size(50.dp))
        }
    }
    Column {
        if(isDropdown){
            Row(modifier = Modifier.fillMaxWidth()){
                extraCalculatorButton(text = "%",
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp),
                    onClick = {
                        dis1 += "%"
                    })
                extraCalculatorButton(text = "√",
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp),
                    onClick = {
                        dis1 += "√"
                    })
                extraCalculatorButton(text = "()²",
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp),
                    onClick = {
                        dis1 += "²"
                    })
                extraCalculatorButton(text = "^",
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp),
                    onClick = {
                        dis1 += "^"
                    })
            }
        }else{

        }
        //first
        Row(modifier = Modifier.fillMaxWidth()){
            calculatorButton(text = "AC",
                modifier = Modifier
                    .weight(2f)
                    .padding(6.dp),
                isFunction = true,
                onClick = {
                    dis1=""
                    dis2=""
                })
            calculatorButton(text = "⌫",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                isFunction = true,
                onClick = {
                    if(dis1.isNotEmpty()){
                        dis1=dis1.removeRange(dis1.lastIndex,dis1.lastIndex+1)
                    }
                    if(number.isNotEmpty()){
                        number=number.removeRange(number.lastIndex,number.lastIndex+1)
                }
                })
            calculatorButton(text = "+",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                isFunction = true,
                onClick = {
                    val temp=dis1.get(dis1.lastIndex)
                    if((temp!='+')&&
                        (temp!='-')&&
                        (temp!='×')&&
                        (temp!='÷')){
                        dis1+="+"
                    }
                })
        }
        Row(modifier = Modifier.fillMaxWidth()){
            calculatorButton(text = "7",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "7"
                })
            calculatorButton(text = "8",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "8"
                })
            calculatorButton(text = "9",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "9"
                })
            calculatorButton(text = "-",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                isFunction = true,
                onClick = {
                    val temp=dis1.get(dis1.lastIndex)
                    if((temp!='+')&&
                        (temp!='-')&&
                        (temp!='×')&&
                        (temp!='÷')){
                        dis1+="-"
                    }
                })
        }
        Row(modifier = Modifier.fillMaxWidth()){
            calculatorButton(text = "4",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "4"

                })
            calculatorButton(text = "5",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "5"

                })
            calculatorButton(text = "6",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "6"

                })
            calculatorButton(text = "×",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                isFunction = true,
                onClick = {
                    val temp=dis1.get(dis1.lastIndex)
                    if((temp!='+')&&
                        (temp!='-')&&
                        (temp!='×')&&
                        (temp!='÷')){
                        dis1+="×"
                    }
                })
        }
        Row(modifier = Modifier.fillMaxWidth()){
            calculatorButton(text = "1",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "1"

                })
            calculatorButton(text = "2",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "2"

                })
            calculatorButton(text = "3",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "3"

                })
            calculatorButton(text = "÷",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                isFunction = true,
                onClick = {
                    val temp=dis1.get(dis1.lastIndex)
                    if((temp!='+')&&
                        (temp!='-')&&
                        (temp!='×')&&
                        (temp!='÷')){
                        dis1+="÷"
                    }
                })
        }
        Row(modifier = Modifier.fillMaxWidth()){
            calculatorButton(text = "0",
                modifier = Modifier
                    .weight(2f)
                    .padding(6.dp),
                onClick = {
                    dis1 += "0"

                })
            calculatorButton(text = ".",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                onClick = {
                    dis1+="."

                })
            calculatorButton(text = "=",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                isFunction = true,
                onClick = {
                    if(dis1.isNotEmpty()){
                        dis2=Operation(dis1)
                    }
                })
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview(){
    Calculator()
}