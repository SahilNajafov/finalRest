package com.finalprojectrest.enums;

import java.util.Arrays;

public enum Language {
    AZE("Azərbaycan dili", "Azerbaijani"),
    ENG("İngilis dili", "English"),
    RUS("Rus dili", "Russian"),
    TUR("Türk dili", "Turkish"),
    GER("Alman dili", "German"),
    FRA("Fransız dili", "French"),
    ITA("İtalyan dili", "Italian"),
    ESP("İspan dili", "Spanish"),
    CHI("Çin dili", "Chinese"),
    JPN("Yapon dili", "Japanese"),
    KOR("Koreya dili", "Korean"),
    ARA("Ərəb dili", "Arabic"),
    PER("Fars dili", "Persian"),
    HIN("Hind dili", "Hindi"),
    URD("Urdu dili", "Urdu"),
    GRE("Yunan dili", "Greek"),
    NLD("Hollandiya dili", "Dutch"),
    SWE("İsveç dili", "Swedish"),
    FIN("Fin dili", "Finnish"),
    NOR("Norveç dili", "Norwegian"),
    DAN("Danimarka dili", "Danish"),
    POL("Polyak dili", "Polish"),
    CZE("Çex dili", "Czech"),
    SLK("Slovak dili", "Slovak"),
    HUN("Macar dili", "Hungarian"),
    ROM("Rumın dili", "Romanian"),
    BUL("Bolqar dili", "Bulgarian"),
    SRP("Serb dili", "Serbian"),
    HRV("Xorvat dili", "Croatian"),
    LIT("Litva dili", "Lithuanian"),
    LAT("Latış dili", "Latvian"),
    EST("Eston dili", "Estonian"),
    GEO("Gürcü dili", "Georgian"),
    ARM("Erməni dili", "Armenian"),
    KAZ("Qazax dili", "Kazakh"),
    UZB("Özbək dili", "Uzbek"),
    TKM("Türkmən dili", "Turkmen"),
    KYR("Qırğız dili", "Kyrgyz"),
    TJK("Tacik dili", "Tajik"),
    IND("İndonez dili", "Indonesian"),
    MAL("Malay dili", "Malay"),
    VIE("Vyetnam dili", "Vietnamese"),
    THA("Tay dili", "Thai"),
    KIR("Kiril dili", "Kiril"),
    MAC("Makedon dili", "Macedonian"),
    UKR("Ukrayna dili", "Ukrainian");

    private final String azName;
    private final String enName;

    Language(String azName, String enName) {
        this.azName = azName;
        this.enName = enName;
    }

    public String getAzName() {
        return azName;
    }

    public String getEnName() {
        return enName;
    }
    public static Language getLanguageByAzName(String azName) {
        return Arrays.stream(Language.values())
                .filter(t->t.azName.equals(azName))
                .findAny()
                .orElseThrow(()->new RuntimeException("Language not found"));
    }
}

