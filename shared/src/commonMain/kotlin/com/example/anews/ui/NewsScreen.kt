package com.example.anews.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.anews.data.MockNews
import com.example.anews.data.NewsApi
import com.example.anews.model.NewsArticle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(apiKey: String) {
    var lang by remember { mutableStateOf("en") }
    var dark by remember { mutableStateOf(false) }
    val direction = if (lang == "fa") LayoutDirection.Rtl else LayoutDirection.Ltr

    MaterialTheme(
        colorScheme = if (dark) darkColorScheme() else lightColorScheme()
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides direction) {
            NewsContent(
                apiKey = apiKey,
                lang = lang,
                dark = dark,
                onLangChange = { lang = it },
                onDarkChange = { dark = it }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsContent(
    apiKey: String,
    lang: String,
    dark: Boolean,
    onLangChange: (String) -> Unit,
    onDarkChange: (Boolean) -> Unit
) {
    val categories = MockNews.categories
    var selectedTab by remember { mutableStateOf(0) }
    var query by remember { mutableStateOf("") }
    var newsList by remember { mutableStateOf<List<NewsArticle>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    val newsApi = remember { NewsApi(apiKey) }

    LaunchedEffect(selectedTab, query, lang) {
        isLoading = true
        newsList = if (query.isBlank()) {
            newsApi.getTopHeadlines(category = categories[selectedTab], lang = lang)
        } else {
            newsApi.searchNews(query, lang)
        }
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Strings.appTitle(lang)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    TextButton(onClick = { onLangChange(if (lang == "fa") "en" else "fa") }) {
                        Text(Strings.langButton(lang), color = Color.White)
                    }
                    TextButton(onClick = { onDarkChange(!dark) }) {
                        Text(Strings.themeButton(dark), color = Color.White)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text(Strings.searchHint(lang)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { /* reload via LaunchedEffect */ }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                edgePadding = 8.dp
            ) {
                categories.forEachIndexed { index, key ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                            query = ""
                        },
                        text = { Text(MockNews.categoryTitle(key, lang)) }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    newsList.isEmpty() -> {
                        Text(
                            text = Strings.noNews(lang),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(newsList) { article ->
                                NewsCard(article)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(article: NewsArticle) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = article.source.name.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3
                )
            }

            Text(
                text = article.publishedAt.take(10),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
