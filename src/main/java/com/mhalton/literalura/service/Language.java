package com.mhalton.literalura.service;

import java.util.Map;

public class Language
{
    private final static Map<String, String> languages = Map.<String, String>ofEntries(
    Map.entry("AB", "Abkhazian"),
    Map.entry("AA", "Afar"),
    Map.entry("AF", "Afrikaans"),
    Map.entry("SQ", "Albanian"),
    Map.entry("AM", "Amharic"),
    Map.entry("AR", "Arabic"),
    Map.entry("HY", "Armenian"),
    Map.entry("AS", "Assamese"),
    Map.entry("AY", "Aymara"),
    Map.entry("AZ", "Azerbaijani"),
    Map.entry("BA", "Bashkir"),
    Map.entry("EU", "Basque"),
    Map.entry("BN", "Bengali"),
    Map.entry("DZ", "Bhutani"),
    Map.entry("BH", "Bihari"),
    Map.entry("BI", "Bislama"),
    Map.entry("BR", "Breton"),
    Map.entry("BG", "Bulgarian"),
    Map.entry("MY", "Burmese"),
    Map.entry("BE", "Byelorussian"),
    Map.entry("KM", "Cambodian"),
    Map.entry("CA", "Catalan"),
    Map.entry("ZH", "Chinese"),
    Map.entry("CO", "Corsican"),
    Map.entry("HR", "Croatian"),
    Map.entry("CS", "Czech"),
    Map.entry("DA", "Danish"),
    Map.entry("NL", "Dutch"),
    Map.entry("EN", "English"),
    Map.entry("EO", "Esperanto"),
    Map.entry("ET", "Estonian"),
    Map.entry("FO", "Faeroese"),
    Map.entry("FJ", "Fiji"),
    Map.entry("FI", "Finnish"),
    Map.entry("FR", "French"),
    Map.entry("FY", "Frisian"),
    Map.entry("GD", "Gaelic"),
    Map.entry("GL", "Galician"),
    Map.entry("KA", "Georgian"),
    Map.entry("DE", "German"),
    Map.entry("EL", "Greek"),
    Map.entry("KL", "Greenlandic"),
    Map.entry("GN", "Guarani"),
    Map.entry("GU", "Gujarati"),
    Map.entry("HA", "Hausa"),
    Map.entry("IW", "Hebrew"),
    Map.entry("HI", "Hindi"),
    Map.entry("HU", "Hungarian"),
    Map.entry("IS", "Icelandic"),
    Map.entry("IN", "Indonesian"),
    Map.entry("IA", "Interlingua"),
    Map.entry("IE", "Interlingue"),
    Map.entry("IK", "Inupiak"),
    Map.entry("GA", "Irish"),
    Map.entry("IT", "Italian"),
    Map.entry("JA", "Japanese"),
    Map.entry("JW", "Javanese"),
    Map.entry("KN", "Kannada"),
    Map.entry("KS", "Kashmiri"),
    Map.entry("KK", "Kazakh"),
    Map.entry("RW", "Kinyarwanda"),
    Map.entry("KY", "Kirghiz"),
    Map.entry("RN", "Kirundi"),
    Map.entry("KO", "Korean"),
    Map.entry("KU", "Kurdish"),
    Map.entry("LO", "Laothian"),
    Map.entry("LA", "Latin"),
    Map.entry("LV", "Latvian"),
    Map.entry("LN", "Lingala"),
    Map.entry("LT", "Lithuanian"),
    Map.entry("MK", "Macedonian"),
    Map.entry("MG", "Malagasy"),
    Map.entry("MS", "Malay"),
    Map.entry("ML", "Malayalam"),
    Map.entry("MT", "Maltese"),
    Map.entry("MI", "Maori"),
    Map.entry("MR", "Marathi"),
    Map.entry("MO", "Moldavian"),
    Map.entry("MN", "Mongolian"),
    Map.entry("NA", "Nauru"),
    Map.entry("NE", "Nepali"),
    Map.entry("NO", "Norwegian"),
    Map.entry("OC", "Occitan"),
    Map.entry("OR", "Oriya"),
    Map.entry("OM", "Oromo"),
    Map.entry("PS", "Pashto"),
    Map.entry("FA", "Persian"),
    Map.entry("PL", "Polish"),
    Map.entry("PT", "Portuguese"),
    Map.entry("PA", "Punjabi"),
    Map.entry("QU", "Quechua"),
    Map.entry("RM", "Rhaeto-Romance"),
    Map.entry("RO", "Romanian"),
    Map.entry("RU", "Russian"),
    Map.entry("SM", "Samoan"),
    Map.entry("SG", "Sangro"),
    Map.entry("SA", "Sanskrit"),
    Map.entry("SR", "Serbian"),
    Map.entry("SH", "Serbo-Croatian"),
    Map.entry("ST", "Sesotho"),
    Map.entry("TN", "Setswana"),
    Map.entry("SN", "Shona"),
    Map.entry("SD", "Sindhi"),
    Map.entry("SI", "Singhalese"),
    Map.entry("SS", "Siswati"),
    Map.entry("SK", "Slovak"),
    Map.entry("SL", "Slovenian"),
    Map.entry("SO", "Somali"),
    Map.entry("ES", "Spanish"),
    Map.entry("SU", "Sudanese"),
    Map.entry("SW", "Swahili"),
    Map.entry("SV", "Swedish"),
    Map.entry("TL", "Tagalog"),
    Map.entry("TG", "Tajik"),
    Map.entry("TA", "Tamil"),
    Map.entry("TT", "Tatar"),
    Map.entry("TE", "Tegulu"),
    Map.entry("TH", "Thai"),
    Map.entry("BO", "Tibetan"),
    Map.entry("TI", "Tigrinya"),
    Map.entry("TO", "Tonga"),
    Map.entry("TS", "Tsonga"),
    Map.entry("TR", "Turkish"),
    Map.entry("TK", "Turkmen"),
    Map.entry("TW", "Twi"),
    Map.entry("UK", "Ukrainian"),
    Map.entry("UR", "Urdu"),
    Map.entry("UZ", "Uzbek"),
    Map.entry("VI", "Vietnamese"),
    Map.entry("VO", "Volapuk"),
    Map.entry("CY", "Welsh"),
    Map.entry("WO", "Wolof"),
    Map.entry("XH", "Xhosa"),
    Map.entry("JI", "Yiddish"),
    Map.entry("YO", "Yoruba"),
    Map.entry("ZU", "Zulu"));

    public static String getLanguage(String languageCode)
    {
        return languages.getOrDefault(languageCode.toUpperCase(), "Unknown language");
    }
}
