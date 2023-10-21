package eu.kanade.presentation.reader.appbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FormatListNumbered
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.reader.setting.OrientationType
import eu.kanade.tachiyomi.ui.reader.setting.ReaderBottomButton
import eu.kanade.tachiyomi.ui.reader.setting.ReadingModeType

@Composable
fun BottomReaderBar(
    // SY -->
    enabledButtons: Set<String>,
    // SY <--
    backgroundColor: Color,
    readingMode: ReadingModeType,
    onClickReadingMode: () -> Unit,
    orientationMode: OrientationType,
    onClickOrientationMode: () -> Unit,
    cropEnabled: Boolean,
    onClickCropBorder: () -> Unit,
    onClickSettings: () -> Unit,
    // SY -->
    dualPageSplitEnabled: Boolean,
    doublePages: Boolean,
    onClickChapterList: () -> Unit,
    onClickWebView: (() -> Unit)?,
    onClickShare: (() -> Unit)?,
    onClickPageLayout: () -> Unit,
    onClickShiftPage: () -> Unit,
    // SY <--
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // SY -->
        if (ReaderBottomButton.ViewChapters.isIn(enabledButtons)) {
            IconButton(onClick = onClickChapterList) {
                Icon(
                    imageVector = Icons.Outlined.FormatListNumbered,
                    contentDescription = stringResource(R.string.chapters),
                )
            }
        }

        if (ReaderBottomButton.WebView.isIn(enabledButtons) && onClickWebView != null) {
            IconButton(onClick = onClickWebView) {
                Icon(
                    imageVector = Icons.Outlined.Public,
                    contentDescription = stringResource(R.string.action_open_in_web_view),
                )
            }
        }

        if (ReaderBottomButton.Share.isIn(enabledButtons) && onClickShare != null) {
            IconButton(onClick = onClickShare) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = stringResource(R.string.action_share),
                )
            }
        }

        if (ReaderBottomButton.ReadingMode.isIn(enabledButtons)) {
            IconButton(onClick = onClickReadingMode) {
                Icon(
                    painter = painterResource(readingMode.iconRes),
                    contentDescription = stringResource(R.string.viewer),
                )
            }
        }

        val cropBorders = when (readingMode) {
            ReadingModeType.WEBTOON -> ReaderBottomButton.CropBordersWebtoon
            ReadingModeType.CONTINUOUS_VERTICAL -> ReaderBottomButton.CropBordersContinuesVertical
            else -> ReaderBottomButton.CropBordersPager
        }
        if (cropBorders.isIn(enabledButtons)) {
            IconButton(onClick = onClickCropBorder) {
                Icon(
                    painter = painterResource(if (cropEnabled) R.drawable.ic_crop_24dp else R.drawable.ic_crop_off_24dp),
                    contentDescription = stringResource(R.string.pref_crop_borders),
                )
            }
        }

        if (ReaderBottomButton.Rotation.isIn(enabledButtons)) {
            IconButton(onClick = onClickOrientationMode) {
                Icon(
                    painter = painterResource(orientationMode.iconRes),
                    contentDescription = stringResource(R.string.pref_rotation_type),
                )
            }
        }

        if (
            !dualPageSplitEnabled &&
            ReaderBottomButton.PageLayout.isIn(enabledButtons) &&
            ReadingModeType.isPagerType(readingMode.flagValue)
        ) {
            IconButton(onClick = onClickPageLayout) {
                Icon(
                    painter = painterResource(R.drawable.ic_book_open_variant_24dp),
                    contentDescription = stringResource(R.string.page_layout),
                )
            }
        }

        if (doublePages) {
            IconButton(onClick = onClickShiftPage) {
                Icon(
                    painter = painterResource(R.drawable.ic_page_next_outline_24dp),
                    contentDescription = stringResource(R.string.shift_double_pages),
                )
            }
        }

        IconButton(onClick = onClickSettings) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.action_settings),
            )
        }
        // SY <--
    }
}