package com.vakko.concierge

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF111111)
private val Cream = Color(0xFFF5F2EC)
private val Gold = Color(0xFFB89A64)
private val Wine = Color(0xFF672A33)

private enum class Tab(val label: String, val icon: ImageVector) {
    Home("Ana Sayfa", Icons.Outlined.Home), Moments("Moments", Icons.Outlined.AutoAwesome),
    Wardrobe("Gardırop", Icons.Outlined.Checkroom), Profile("Profil", Icons.Outlined.Person)
}

@Composable
fun VakkoConciergeApp() {
    var tab by remember { mutableStateOf(Tab.Home) }
    MaterialTheme(colorScheme = lightColorScheme(primary = Ink, background = Cream, surface = Color.White)) {
        Scaffold(
            containerColor = Cream,
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    Tab.entries.forEach { item ->
                        NavigationBarItem(
                            selected = tab == item, onClick = { tab = item },
                            icon = { Icon(item.icon, null) }, label = { Text(item.label, fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(selectedIconColor = Gold, indicatorColor = Ink)
                        )
                    }
                }
            }
        ) { padding ->
            AnimatedContent(tab, modifier = Modifier.padding(padding), label = "tab") { current ->
                when (current) {
                    Tab.Home -> HomeScreen(onMoments = { tab = Tab.Moments }, onWardrobe = { tab = Tab.Wardrobe })
                    Tab.Moments -> MomentsScreen()
                    Tab.Wardrobe -> WardrobeScreen()
                    Tab.Profile -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(onMoments: () -> Unit, onWardrobe: () -> Unit) {
    var event by remember { mutableStateOf("Gala") }
    val events = listOf("Düğün", "İş yemeği", "Gala", "Günlük", "Tatil", "Davet")
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Box(Modifier.fillMaxWidth().height(250.dp).background(Brush.verticalGradient(listOf(Ink, Color(0xFF302D2A))))) {
            Column(Modifier.padding(24.dp).align(Alignment.CenterStart)) {
                Text("VAKKO", color = Color.White, letterSpacing = 7.sp, fontSize = 25.sp, fontWeight = FontWeight.Light)
                Spacer(Modifier.height(34.dp))
                Text("İyi akşamlar, Selin", color = Color.White.copy(.7f), fontSize = 13.sp)
                Text("Bu gece nasıl\ngörünmek istersin?", color = Color.White, fontSize = 30.sp, lineHeight = 34.sp, fontFamily = FontFamily.Serif)
            }
            Surface(Modifier.padding(20.dp).align(Alignment.TopEnd), shape = CircleShape, color = Color.White.copy(.1f)) {
                Icon(Icons.Outlined.Notifications, null, tint = Color.White, modifier = Modifier.padding(10.dp))
            }
        }
        Column(Modifier.padding(20.dp)) {
            SectionTitle("ETKİNLİĞİNİ SEÇ", "Tümünü gör")
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                events.forEach { name ->
                    FilterChip(selected = event == name, onClick = { event = name }, label = { Text(name) },
                        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = Ink, selectedLabelColor = Color.White))
                }
            }
            Spacer(Modifier.height(22.dp))
            OutfitHero(event)
            Spacer(Modifier.height(24.dp))
            Text("CONCIERGE HİZMETLERİ", letterSpacing = 1.5.sp, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ServiceCard("Stil danışmanı", "Randevu al", Icons.Outlined.CalendarMonth, Modifier.weight(1f))
                ServiceCard("Sanal gardırop", "12 parçan var", Icons.Outlined.Checkroom, Modifier.weight(1f), onWardrobe)
            }
            Spacer(Modifier.height(16.dp))
            Surface(onClick = onMoments, color = Wine, shape = RoundedCornerShape(20.dp)) {
                Row(Modifier.fillMaxWidth().padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.AutoAwesome, null, tint = Gold, modifier = Modifier.size(32.dp))
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) { Text("Vakko Moments", color = Color.White, fontFamily = FontFamily.Serif, fontSize = 21.sp); Text("Özel anını kusursuzlaştır", color = Color.White.copy(.65f), fontSize = 12.sp) }
                    Icon(Icons.Outlined.ArrowForward, null, tint = Color.White)
                }
            }
        }
    }
}

@Composable private fun OutfitHero(event: String) {
    Surface(shape = RoundedCornerShape(24.dp), color = Color(0xFFD9D2C6), modifier = Modifier.fillMaxWidth()) {
        Column {
            Box(Modifier.fillMaxWidth().height(230.dp).background(Brush.linearGradient(listOf(Color(0xFFB8AA99), Color(0xFFE9E3DA))))) {
                Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Outlined.Checkroom, null, tint = Ink.copy(.65f), modifier = Modifier.size(82.dp))
                    Text("$event seçkisi", color = Ink.copy(.6f), fontFamily = FontFamily.Serif, fontSize = 18.sp)
                }
                Surface(Modifier.padding(14.dp).align(Alignment.TopStart), color = Color.White.copy(.92f), shape = RoundedCornerShape(50)) { Text("%94 stil eşleşmesi", Modifier.padding(horizontal = 12.dp, vertical = 7.dp), fontSize = 11.sp, fontWeight = FontWeight.Bold) }
                IconButton(onClick = {}, Modifier.padding(8.dp).align(Alignment.TopEnd)) { Icon(Icons.Outlined.FavoriteBorder, null) }
            }
            Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) { Text("Gece Siyahı", fontFamily = FontFamily.Serif, fontSize = 23.sp); Text("Ceket · Bluz · Pantolon · Çanta", color = Ink.copy(.55f), fontSize = 12.sp); Text("₺ 84.950", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp)) }
                FilledIconButton(onClick = {}, colors = IconButtonDefaults.filledIconButtonColors(containerColor = Ink)) { Icon(Icons.Outlined.ArrowForward, null, tint = Color.White) }
            }
        }
    }
}

@Composable private fun MomentsScreen() {
    var selected by remember { mutableStateOf("Nişan") }
    val moments = listOf("Mezuniyet", "Nişan", "İş görüşmesi", "Yılbaşı daveti")
    Page("VAKKO MOMENTS", "Anını seç, gerisini bize bırak.") {
        moments.forEach { moment ->
            Surface(onClick = { selected = moment }, shape = RoundedCornerShape(18.dp), color = if (selected == moment) Ink else Color.White, modifier = Modifier.fillMaxWidth()) {
                Row(Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(if (moment == "Nişan") Icons.Outlined.Diamond else Icons.Outlined.Celebration, null, tint = if (selected == moment) Gold else Ink)
                    Text(moment, Modifier.padding(start = 16.dp).weight(1f), color = if (selected == moment) Color.White else Ink, fontFamily = FontFamily.Serif, fontSize = 19.sp)
                    Icon(Icons.Outlined.ChevronRight, null, tint = if (selected == moment) Color.White else Ink)
                }
            }
            Spacer(Modifier.height(10.dp))
        }
        Spacer(Modifier.height(12.dp))
        Button(onClick = {}, Modifier.fillMaxWidth().height(54.dp), colors = ButtonDefaults.buttonColors(containerColor = Gold)) { Text("ÖZEL SEÇKİYİ HAZIRLA", color = Ink, fontWeight = FontWeight.Bold) }
    }
}

@Composable private fun WardrobeScreen() = Page("SANAL GARDIROBUM", "Sahip olduklarınla yeni kombinler keşfet.") {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Stat("12", "PARÇA", Modifier.weight(1f)); Stat("8", "KOMBİN", Modifier.weight(1f)); Stat("3", "YENİ ÖNERİ", Modifier.weight(1f))
    }
    Spacer(Modifier.height(22.dp))
    Surface(color = Ink, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(22.dp)) { Text("Yeni eşleşme", color = Gold, fontSize = 12.sp, fontWeight = FontWeight.Bold); Text("Lacivert ceketini yeni sezon ipek eşarpla tamamla.", color = Color.White, fontFamily = FontFamily.Serif, fontSize = 22.sp, modifier = Modifier.padding(vertical = 10.dp)); Text("Eşleşmeyi gör  →", color = Color.White, fontWeight = FontWeight.Bold) }
    }
    Spacer(Modifier.height(20.dp))
    OutlinedButton(onClick = {}, Modifier.fillMaxWidth().height(54.dp)) { Icon(Icons.Outlined.Add, null); Spacer(Modifier.width(8.dp)); Text("VAKKO ÜRÜNÜ EKLE") }
}

@Composable private fun ProfileScreen() = Page("CONCIERGE PROFİLİ", "Stil tercihlerin ve ayrıcalıkların.") {
    Surface(color = Ink, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(22.dp)) { Text("VAKKO PRIVATE", color = Gold, letterSpacing = 2.sp, fontWeight = FontWeight.Bold); Text("Selin Yılmaz", color = Color.White, fontFamily = FontFamily.Serif, fontSize = 26.sp, modifier = Modifier.padding(top = 20.dp)); Text("Gold üye · 4.250 puan", color = Color.White.copy(.6f)) }
    }
    Spacer(Modifier.height(20.dp))
    listOf("Stil profilim" to Icons.Outlined.Tune, "Randevularım" to Icons.Outlined.CalendarMonth, "AR ile dene" to Icons.Outlined.ViewInAr, "Ayrıcalıklarım" to Icons.Outlined.CardGiftcard).forEach { (name, icon) ->
        Surface(color = Color.White, shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) { Row(Modifier.padding(17.dp), verticalAlignment = Alignment.CenterVertically) { Icon(icon, null); Text(name, Modifier.padding(start = 14.dp).weight(1f)); Icon(Icons.Outlined.ChevronRight, null) } }
    }
}

@Composable private fun Page(title: String, subtitle: String, content: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
        Spacer(Modifier.height(18.dp)); Text(title, fontSize = 12.sp, letterSpacing = 2.sp, fontWeight = FontWeight.Bold); Text(subtitle, fontFamily = FontFamily.Serif, fontSize = 28.sp, lineHeight = 32.sp, modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)); content(); Spacer(Modifier.height(30.dp))
    }
}

@Composable private fun SectionTitle(title: String, action: String) = Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text(title, fontSize = 12.sp, letterSpacing = 1.5.sp, fontWeight = FontWeight.Bold); Text(action, fontSize = 12.sp, color = Ink.copy(.5f)) }
@Composable private fun ServiceCard(title: String, subtitle: String, icon: ImageVector, modifier: Modifier, onClick: () -> Unit = {}) = Surface(onClick = onClick, modifier = modifier, color = Color.White, shape = RoundedCornerShape(18.dp)) { Column(Modifier.padding(16.dp)) { Icon(icon, null, tint = Gold); Text(title, fontFamily = FontFamily.Serif, fontSize = 18.sp, modifier = Modifier.padding(top = 18.dp)); Text(subtitle, fontSize = 11.sp, color = Ink.copy(.55f)) } }
@Composable private fun Stat(value: String, label: String, modifier: Modifier) = Surface(modifier, color = Color.White, shape = RoundedCornerShape(14.dp)) { Column(Modifier.padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) { Text(value, fontSize = 22.sp, fontFamily = FontFamily.Serif); Text(label, fontSize = 9.sp, letterSpacing = 1.sp, color = Ink.copy(.5f)) } }
