package com.example.anews.data

import com.example.anews.model.NewsArticle
import com.example.anews.model.Source

/**
 * Offline demo content, Google-News style: categorized sample stories
 * in English and Persian. Used when no API key is configured or the
 * network request fails, so the app always opens with content.
 */
object MockNews {

    /** Category keys (stable, language-independent). */
    val categories = listOf("top", "world", "business", "technology", "sports", "science")

    /** Localized tab titles. */
    fun categoryTitle(key: String, lang: String): String {
        val fa = lang == "fa"
        return when (key) {
            "top" -> if (fa) "سرخط خبرها" else "Top stories"
            "world" -> if (fa) "جهان" else "World"
            "business" -> if (fa) "کسب‌وکار" else "Business"
            "technology" -> if (fa) "فناوری" else "Technology"
            "sports" -> if (fa) "ورزش" else "Sports"
            "science" -> if (fa) "علم" else "Science"
            else -> key
        }
    }

    private class Item(
        val titleEn: String,
        val descEn: String,
        val titleFa: String,
        val descFa: String,
        val source: String,
        val date: String
    ) {
        fun toArticle(lang: String) = NewsArticle(
            title = if (lang == "fa") titleFa else titleEn,
            description = if (lang == "fa") descFa else descEn,
            url = "https://example.com/news",
            imageUrl = null,
            publishedAt = date,
            source = Source(source)
        )
    }

    private val world = listOf(
        Item(
            "Global Markets Rally as Central Banks Signal Rate Cuts",
            "Stock markets around the world climbed on Thursday after major central banks hinted at coordinated interest-rate cuts later this year.",
            "رشد بازارهای جهانی با سیگنال کاهش نرخ بهره",
            "بازارهای سهام جهان روز پنجشنبه پس از اشاره بانک‌های مرکزی بزرگ به کاهش هماهنگ نرخ بهره، صعود کردند.",
            "World Tribune", "2026-09-03"
        ),
        Item(
            "Historic Climate Summit Reaches New Emissions Deal",
            "Delegates from nearly 200 countries agreed on a new framework to cut greenhouse-gas emissions by 2030.",
            "توافق تاریخی اقلیمی برای کاهش انتشار گازها",
            "نمایندگان نزدیک به ۲۰۰ کشور بر سر چارچوب جدیدی برای کاهش انتشار گازهای گلخانه‌ای تا سال ۲۰۳۰ توافق کردند.",
            "Global Post", "2026-09-02"
        ),
        Item(
            "New High-Speed Rail Line Connects Three Capitals",
            "The 450-kilometre line cuts travel time between the capitals to under two hours and opens to passengers next month.",
            "خط ریلی پرسرعت سه پایتخت را به هم وصل کرد",
            "این خط ۴۵۰ کیلومتری زمان سفر میان پایتخت‌ها را به کمتر از دو ساعت می‌رساند و ماه آینده افتتاح می‌شود.",
            "World Tribune", "2026-09-01"
        )
    )

    private val business = listOf(
        Item(
            "Tech Stocks Lead Record Quarter for Startup Funding",
            "Venture funding hit a record high this quarter, with artificial-intelligence startups taking the largest share of new investment.",
            "رکورد سرمایه‌گذاری استارتاپی با پیشتازی سهام فناوری",
            "سرمایه‌گذاری خطرپذیر این فصل رکورد زد و استارتاپ‌های هوش مصنوعی بیشترین سهم را بردند.",
            "Business Daily", "2026-09-03"
        ),
        Item(
            "Small Businesses Bet Big on Digital Payments",
            "A new survey shows seven in ten small firms now accept mobile payments, up from four in ten just two years ago.",
            "شرط بزرگ کسب‌وکارهای کوچک روی پرداخت دیجیتال",
            "نظرسنجی جدید نشان می‌دهد هفتاد درصد کسب‌وکارهای کوچک اکنون پرداخت موبایلی می‌پذیرند.",
            "Market Watch", "2026-09-02"
        ),
        Item(
            "Oil Prices Steady as Producers Extend Output Agreement",
            "Crude prices held steady after major producers agreed to extend their current output limits through next quarter.",
            "ثبات قیمت نفت با تمدید توافق تولیدکنندگان",
            "قیمت نفت پس از توافق تولیدکنندگان بزرگ برای تمدید سقف تولید، ثابت ماند.",
            "Business Daily", "2026-09-01"
        )
    )

    private val technology = listOf(
        Item(
            "Next-Gen AI Assistants Can Now Work Fully Offline",
            "New on-device models bring fast, private AI help to mid-range phones with no internet connection required.",
            "دستیارهای هوش مصنوعی آفلاین از راه رسیدند",
            "مدل‌های جدید روی دستگاه، بدون نیاز به اینترنت روی گوشی‌های میان‌رده دستیار سریع و خصوصی می‌آورند.",
            "Tech Insight", "2026-09-03"
        ),
        Item(
            "Foldable Phones Go Mainstream as Prices Fall",
            "Foldable shipments doubled this year as prices dropped below flagship levels for the first time.",
            "گوشی‌های تاشو با افت قیمت فراگیر شدند",
            "فروش گوشی‌های تاشو امسال دو برابر شد و قیمت برای نخستین بار از پرچمدارها پایین‌تر آمد.",
            "Tech Insight", "2026-09-02"
        ),
        Item(
            "Open-Source Robotics Kit Wins Global Design Award",
            "The low-cost kit lets students build and program real robots with recycled parts and a phone app.",
            "کیت رباتیک متن‌باز برنده جایزه جهانی طراحی",
            "این کیت ارزان به دانش‌آموزان امکان ساخت و برنامه‌نویسی ربات واقعی را می‌دهد.",
            "Future Lab", "2026-09-01"
        )
    )

    private val sports = listOf(
        Item(
            "Championship Final Sets All-Time Attendance Record",
            "More than ninety thousand fans packed the stadium for a dramatic final decided in stoppage time.",
            "رکورد تماشاگر در فینال قهرمانی شکست",
            "بیش از نود هزار تماشاگر فینال دراماتیک را که در وقت اضافه مشخص شد، از نزدیک دیدند.",
            "Sports Arena", "2026-09-03"
        ),
        Item(
            "Teenage Sprinter Breaks National 100m Record",
            "The 17-year-old clocked 9.98 seconds, becoming the youngest national champion in history.",
            "رکورد ۱۰۰ متر کشور به دست دونده نوجوان شکست",
            "دونده ۱۷ ساله با زمان ۹٫۹۸ ثانیه جوان‌ترین قهرمان تاریخ کشور شد.",
            "Sports Arena", "2026-09-02"
        ),
        Item(
            "Underdogs Reach Cup Semi-Final After Penalty Shootout",
            "The second-division side held on through extra time and won 4-3 on penalties.",
            "صعود شگفتی‌سازان به نیمه‌نهایی جام در ضربات پنالتی",
            "تیم دسته دومی در وقت اضافه مقاومت کرد و ۴ بر ۳ در پنالتی برد.",
            "Goal Line", "2026-09-01"
        )
    )

    private val science = listOf(
        Item(
            "Astronomers Spot Potentially Habitable Exoplanet Nearby",
            "The rocky planet orbits in its star's habitable zone just 40 light-years away, making it a prime telescope target.",
            "کشف سیاره‌ای با احتمال حیات در همسایگی زمین",
            "این سیاره سنگی در کمربند حیات ستاره‌اش و فقط ۴۰ سال نوری دورتر است.",
            "Science Today", "2026-09-03"
        ),
        Item(
            "Breakthrough Battery Charges in Under Five Minutes",
            "Researchers demonstrated a new solid-state cell that keeps 90 percent capacity after a thousand fast charges.",
            "باتری انقلابی با شارژ زیر پنج دقیقه",
            "پژوهشگران سلول حالت‌جامد جدیدی ساختند که پس از هزار شارژ سریع، ۹۰ درصد ظرفیت دارد.",
            "Science Today", "2026-09-02"
        ),
        Item(
            "Deep-Sea Expedition Discovers Dozens of New Species",
            "Scientists returned with footage of glowing corals and fish never seen before at 3,000 metres depth.",
            "کشف ده‌ها گونه جدید در اعماق دریا",
            "دانشمندان با تصاویری از مرجان‌های درخشان و ماهی‌های ناشناخته از عمق ۳۰۰۰ متری بازگشتند.",
            "Nature Scope", "2026-09-01"
        )
    )

    /** Google-News-like sections; "top" mixes the latest of everything. */
    fun articles(category: String, lang: String = "en"): List<NewsArticle> {
        val list = when (category) {
            "world" -> world
            "business" -> business
            "technology" -> technology
            "sports" -> sports
            "science" -> science
            else -> listOf(
                world[0], technology[0], sports[0],
                business[0], science[0], world[1]
            )
        }
        return list.map { it.toArticle(lang) }
    }

    fun search(query: String, lang: String = "en"): List<NewsArticle> {
        val q = query.trim()
        if (q.isEmpty()) return articles("top", lang)
        val all = world + business + technology + sports + science
        return all.map { it.toArticle(lang) }.filter {
            it.title.contains(q, ignoreCase = true) ||
                (it.description?.contains(q, ignoreCase = true) == true)
        }
    }
}
