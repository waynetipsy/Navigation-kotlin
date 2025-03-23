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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.ify.navigationkotlin.ui.theme.NavigationKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          // 1- Navcontroller:
         // Navigating between composables
            val navController = rememberNavController()

            //2- NavHost
            // Defines the nav graph, start & destination

            NavHost(
                navController = navController,
                startDestination = "First"
            ) {

                // Define the "first" composable Destination
                composable ("first") { FirstScreen(navController)}

                // Define the "second" composable Destination
                composable (
                  route =  "second/{userName}?age={age}",
                    arguments = listOf(
                        navArgument("userName") {
                            type = NavType.StringType
                        },

                        navArgument ("age"){
                            type = NavType.StringType
                            defaultValue = "30"
                            nullable = true
                        }
                    )
                    ){
                    backStackEntry ->
                    SecondScreen(navController,
                               backStackEntry.arguments?.getString("userName").toString(),
                                backStackEntry.arguments?.getString("age").toString()

                    ) }

            }

        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    Column (modifier = Modifier.padding(40.dp)){
        Text(text = "This is the First Screen")

        var enteredText by remember {
            mutableStateOf("")
        }

        var enteredText2 by remember {
            mutableStateOf("")
        }

        TextField(value = enteredText,
            onValueChange = {enteredText = it},
            label = {Text(text = "Enter your Name")}
        )


        TextField(value = enteredText2,
            onValueChange = {enteredText2 = it},
            label = {Text(text = "Enter your Age")}
        )

        Button(onClick = {
            //1- Passing the entered name as argument
            navController.navigate("second/$enteredText?age=$enteredText2")
        }) {
            Text(text = "Go to 2nd Screen")
        }
    }
}

 @Composable
 fun SecondScreen(navController: NavController, username: String, age: String) {
     Column (modifier = Modifier.padding(40.dp)){
         Text(text = "Welcome $username, Your age is $age")
         Button(onClick = {
             navController.navigateUp()
         }) {
             Text(text = "Go to 1st screen")
         }
     }
 }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationKotlinTheme {

    }
}