package com.artofcode.calculator

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artofcode.calculator.ui.theme.extraButton
import com.artofcode.calculator.ui.theme.grayButton
import com.artofcode.calculator.ui.theme.yButton

@Composable
fun calculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isFunction: Boolean= false
){
    Button(
        onClick = {onClick()},
        modifier= modifier.size(72.dp).clip(CircleShape),
        colors = ButtonDefaults.buttonColors(containerColor = if(isFunction){
            yButton
        }else{
            grayButton})
        ) {
        Text(text = text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 32.sp)
    }
}

@Composable
fun extraCalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isFunction: Boolean= false
){
    Button(
        onClick = {onClick()},
        modifier= modifier.size(72.dp).clip(CircleShape),
        colors = ButtonDefaults.buttonColors(containerColor = extraButton
    ) ){
        Text(text = text, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}

/*calculatorButton(text = "+",
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                isFunction = true,
                onClick = {
                    if((dis1[dis1.lastIndex]!='+')&&
                        (dis1[dis1.lastIndex]!='-')&&
                        (dis1[dis1.lastIndex]!='x')&&
                        (dis1[dis1.lastIndex]!='/')){
                        numberD=number.toDouble()
                        val tempNum=numberD
                        ansD += numberD
                        if(tempNum!=ansD){
                            dis2=ansD.toString()
                        }
                        dis1+="+"
                        number=""
                        numberD=0.0
                    }

                })*/