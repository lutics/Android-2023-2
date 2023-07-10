package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val result by viewModel.result.collectAsStateWithLifecycle(initialValue = listOf())

                var query by remember { mutableStateOf("") }

                HomeScreen(
                    query = query,
                    data = result,
                    onValueChange = {
                        query = it
                    },
                    onSearch = {
                        viewModel.onSearch(query)
                    }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    query: String,
    data: List<VO.Response.Result.OrganicResults>,
    onValueChange: (query: String) -> Unit,
    onSearch: () -> Unit
) {

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(4.dp),
                label = {

                },
                value = query,
                placeholder = {
                    "ASDASD"
                },
                onValueChange = onValueChange,
                maxLines = 1,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Timber.e("TEST")

                        onSearch()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
            )

            LazyColumn {
                items(data) { organicResult ->
                    Text(organicResult.toString())
                }
            }
        }
    }
}
