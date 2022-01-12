package com.example.countrybook

import com.google.gson.annotations.SerializedName
import java.util.jar.Attributes

class CountryResponse {

        var name: Name? = null
        var tld: ArrayList<String>? = null
        var cca2: String? = null
        var ccn3: String? = null
        var cca3: String? = null
        var cioc: String? = null
        var independent = false
        var status: String? = null
        var unMember = false
        var currencies: Currencies? = null
        var idd: Idd? = null
        var capital: ArrayList<String>? = null
        var altSpellings: ArrayList<String>? = null
        var region: String? = null
        var subregion: String? = null
        var languages: Languages? = null
        var translations: Translations? = null
        var latlng: ArrayList<Double>? = null
        var landlocked = false
        var borders: ArrayList<String>? = null
        var area = 0.0
        var demonyms: Demonyms? = null
        var flag: String? = null
        var maps: Maps? = null
        var population = 0
        var gini: Gini? = null
        var fifa: String? = null
        var car: Car? = null
        var timezones: ArrayList<String>? = null
        var continents: ArrayList<String>? = null
        var flags: Flags? = null
        var coatOfArms: CoatOfArms? = null
        var startOfWeek: String? = null
        var capitalInfo: CapitalInfo? = null
        var postalCode: PostalCode? = null


        class Mya {
                var official: String? = null
                var common: String? = null
        }

        class NativeName {
                var mya: Mya? = null
        }

        class Name {
                var common: String? = null
                var official: String? = null
                var nativeName: NativeName? = null
        }

        class MMK {
                var name: String? = null
                var symbol: String? = null
        }

        class Currencies {
                @SerializedName("MMK")
                var mMK: MMK? = null
        }

        class Idd {
                var root: String? = null
                var suffixes: ArrayList<String>? = null
        }

        class Languages {
                var mya: String? = null
        }

        class Ara {
                var official: String? = null
                var common: String? = null
        }

        class Ces {
                var official: String? = null
                var common: String? = null
        }

        class Cym {
                var official: String? = null
                var common: String? = null
        }

        class Deu {
                var official: String? = null
                var common: String? = null
        }

        class Est {
                var official: String? = null
                var common: String? = null
        }

        class Fin {
                var official: String? = null
                var common: String? = null
        }

        class Fra {
                var official: String? = null
                var common: String? = null
                var f: String? = null
                var m: String? = null
        }

        class Hrv {
                var official: String? = null
                var common: String? = null
        }

        class Hun {
                var official: String? = null
                var common: String? = null
        }

        class Ita {
                var official: String? = null
                var common: String? = null
        }

        class Jpn {
                var official: String? = null
                var common: String? = null
        }

        class Kor {
                var official: String? = null
                var common: String? = null
        }

        class Nld {
                var official: String? = null
                var common: String? = null
        }

        class Per {
                var official: String? = null
                var common: String? = null
        }

        class Pol {
                var official: String? = null
                var common: String? = null
        }

        class Por {
                var official: String? = null
                var common: String? = null
        }

        class Rus {
                var official: String? = null
                var common: String? = null
        }

        class Slk {
                var official: String? = null
                var common: String? = null
        }

        class Spa {
                var official: String? = null
                var common: String? = null
        }

        class Swe {
                var official: String? = null
                var common: String? = null
        }

        class Urd {
                var official: String? = null
                var common: String? = null
        }

        class Zho {
                var official: String? = null
                var common: String? = null
        }

        class Translations {
                var ara: Ara? = null
                var ces: Ces? = null
                var cym: Cym? = null
                var deu: Deu? = null
                var est: Est? = null
                var fin: Fin? = null
                var fra: Fra? = null
                var hrv: Hrv? = null
                var hun: Hun? = null
                var ita: Ita? = null
                var jpn: Jpn? = null
                var kor: Kor? = null
                var nld: Nld? = null
                var per: Per? = null
                var pol: Pol? = null
                var por: Por? = null
                var rus: Rus? = null
                var slk: Slk? = null
                var spa: Spa? = null
                var swe: Swe? = null
                var urd: Urd? = null
                var zho: Zho? = null
        }

        class Eng {
                var f: String? = null
                var m: String? = null
        }

        class Demonyms {
                var eng: Eng? = null
                var fra: Fra? = null
        }

        class Maps {
                var googleMaps: String? = null
                var openStreetMaps: String? = null
        }

        class Gini {
                @SerializedName("2017")
                var _2017 = 0.0
        }

        class Car {
                var signs: ArrayList<String>? = null
                var side: String? = null
        }

        class Flags {
                var png: String? = null
                var svg: String? = null
        }

        class CoatOfArms {
                var png: String? = null
                var svg: String? = null
        }

        class CapitalInfo {
                var latlng: ArrayList<Double>? = null
        }

        class PostalCode {
                var format: String? = null
                var regex: String? = null
        }



}