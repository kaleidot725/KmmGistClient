package jp.kaleidot725.githubclient.android.view.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.android.resources.Strings.DETAILS_PAGE_TITLE
import jp.kaleidot725.githubclient.android.resources.Strings.LOADING_ERROR
import jp.kaleidot725.githubclient.android.resources.TextStyles
import jp.kaleidot725.githubclient.api.dto.FileDto
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailPage(
    filesFlow: StateFlow<UiState<List<FileDto>, Unit>>
) {
    val files by filesFlow.collectAsState()

    Column {
        Text(
            text = DETAILS_PAGE_TITLE,
            style = TextStyles.h3,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (files) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Error -> {
                    Text(
                        LOADING_ERROR,
                        style = TextStyles.h6,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is UiState.Success -> {
                    // TODO
                }
            }
        }
    }
}