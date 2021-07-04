package jp.kaleidot725.githubclient.android.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.android.resources.TextStyles
import jp.kaleidot725.githubclient.api.dto.GistDto

@Composable
fun FilesList(
    gists: List<GistDto>,
    modifier: Modifier = Modifier,
    onClicked: ((GistDto) -> Unit)? = null
) {
    LazyColumn(modifier = modifier) {
        items(
            (gists as UiState.Success<List<GistDto>>).data,
            itemContent = { gist -> GistCard(gist, onClicked) }
        )
    }
}

@Composable
private fun GistCard(gist: GistDto, onClicked: ((GistDto) -> Unit)? = null) {
    val files = gist.gistFiles
    val firstFile = files.firstOrNull() ?: return
    val firstFileName = firstFile.filename
    val createdAt = gist.createdAt

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable { onClicked?.invoke(gist) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = firstFileName, style = TextStyles.h6, color = Color.Black, maxLines = 2)
            Text(
                text = createdAt,
                style = TextStyles.caption,
                color = Color.Black,
                maxLines = 1
            )
        }
    }
}