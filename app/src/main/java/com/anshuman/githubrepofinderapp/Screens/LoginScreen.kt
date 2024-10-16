package com.anshuman.githubrepofinderapp.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anshuman.githubrepofinderapp.R




@Composable
fun LoginPageNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login"){ LoginScreen(navController)}
        composable("home"){ HomeScreen(navController)}


    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.git1),
            contentDescription = "GitHub Logo",
            modifier = Modifier
                .size(100.dp) // Set the desired size (width and height) for the image
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Sign in to GitHub",
            style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
            color = Color.Black,
        )
      Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Username:",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start)
        )

        // Username input
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(text = "Enter Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, // Space items across the row
            modifier = Modifier
                .fillMaxWidth() // Ensure the Row fills the width
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Password:",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start) // Align the text to the start
            )

            Text(
                text = "Forgot Password?",
                style = TextStyle(color = Color.Blue),

                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .clickable {
                        // Handle click event
                    },

            )
        }



        // Password input with visual transformation for hiding characters
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        ElevatedButton(
            onClick = {
                // Handle login logic here
                if (userName.isNotEmpty() && password.isNotEmpty()) {
                     navController.navigate("home")
                } else {
                    println("Please enter both username and password!")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF43AF47)), // Light green background
            enabled = userName.isNotEmpty() && password.isNotEmpty()
        ) {
            Text(
                text = "Login",
                color = Color.White, // White text
                fontWeight = FontWeight.Bold
            )
        }


        // Error message if fields are empty
        if (userName.isEmpty() || password.isEmpty()) {
            Text(
                text = "Please enter both username and password!",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.padding(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "New to GitHub?", modifier = Modifier.padding(end = 4.dp))
            ClickableText(
                text = androidx.compose.ui.text.AnnotatedString("Create an account."),
                onClick = { /* Handle create account click */ },
                style = TextStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
            )
        }
    }
    }



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {

}
