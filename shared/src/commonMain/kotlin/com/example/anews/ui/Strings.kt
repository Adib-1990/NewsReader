package com.example.anews.ui

/** English / Persian UI strings. lang is "en" or "fa". */
object Strings {
    fun appTitle(lang: String) = "ANews"
    fun searchHint(lang: String) = if (lang == "fa") "جست‌وجوی اخبار…" else "Search news…"
    fun noNews(lang: String) = if (lang == "fa") "خبری یافت نشد" else "No news found"
    fun langButton(lang: String) = if (lang == "fa") "EN" else "فا"
    fun themeButton(dark: Boolean) = if (dark) "☀" else "☾"
}
