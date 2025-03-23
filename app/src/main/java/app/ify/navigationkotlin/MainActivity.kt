package app.ify.navigationkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import app.ify.navigationkotlin.ui.theme.NavigationKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          // 1- Navcontroller:
         // Navigating between composables
            val navController = rememberNavController()

        }
    }
}

@Composable
fun FirstScreen() {
    Column {
        Text(text = "This is the First Screen")
        Button(onClick = {}) {
            Text(text = "Go to 2nd Screen")
        }
    }
}

 @Composable
 fun SecondScreen() {
     Column {
         Text(text = "This is the Second Screen")
         Button(onClick = {}) {
             Text(text = "Go to 1st screen")
         }
     }
 }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationKotlinTheme {
   FirstScreen()
    }
}