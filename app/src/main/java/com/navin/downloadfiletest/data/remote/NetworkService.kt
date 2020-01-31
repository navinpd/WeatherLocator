package com.navin.downloadfiletest.data.remote

import com.navin.downloadfiletest.data.remote.response.search_response.SearchResults
import com.navin.downloadfiletest.data.remote.response.weather_response.WeatherResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {


    // http://api.worldweatheronline.com/premium/v1/search.ashx?key=5f58d538e2ca430885c122530203001&query=washington&num_of_results=3&format=json
    @GET(Endpoints.ENDPOINT_SEARCH_API)
    fun doSearchCity(
        @Query(Endpoints.KEY_API_KEY) apiKey: String = Networking.API_VAL,
        @Query(Endpoints.KEY_QUERY) queryText: String,
        @Query(Endpoints.KEY_NUM_OF_RESULTS) numOfResults: Int,
        @Query(Endpoints.KEY_FORMAT) format: String = Endpoints.KEY_JSON_FORMAT
    ): Single<SearchResults>
    // {
    //  "search_api": {
    //    "result": [
    //      {
    //        "areaName": [
    //          {
    //            "value": "London"
    //          }
    //        ],
    //        "country": [
    //          {
    //            "value": "United Kingdom"
    //          }
    //        ],
    //        "region": [
    //          {
    //            "value": "City of London, Greater London"
    //          }
    //        ],
    //        "latitude": "51.517",
    //        "longitude": "-0.106",
    //        "population": "7421228",
    //        "weatherUrl": [
    //          {
    //            "value": "https://www.worldweatheronline.com/v2/weather.aspx?q=51.5171,-0.1062"
    //          }
    //        ]
    //      }
    //    ]
    //  }
    //}

    // https://api.worldweatheronline.com/premium/v1/weather.ashx?key=5f58d538e2ca430885c122530203001&q=washington&format=json&date=today&tp=24
    @GET(Endpoints.ENDPOINT_WEATHER_API)
    fun getWeatherDetails(
        @Query(Endpoints.KEY_API_KEY) apiKey: String = Networking.API_VAL,
        @Query(Endpoints.KEY_Q) queryText: String,
        @Query(Endpoints.KEY_FORMAT) format: String = Endpoints.KEY_JSON_FORMAT,
        @Query(Endpoints.KEY_DATE) date: String = Endpoints.VAL_TODAY,
        @Query(Endpoints.KEY_TP) tp: Int = Endpoints.VAL_24
    ): Single<WeatherResult>
    //{
    //  "data": {
    //    "request": [
    //      {
    //        "type": "City",
    //        "query": "Washington, United States of America"
    //      }
    //    ],
    //    "current_condition": [
    //      {
    //        "observation_time": "01:14 PM",
    //        "temp_C": "2",
    //        "temp_F": "35",
    //        "weatherCode": "116",
    //        "weatherIconUrl": [
    //          {
    //            "value": "http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png"
    //          }
    //        ],
    //        "weatherDesc": [
    //          {
    //            "value": "Partly cloudy"
    //          }
    //        ],
    //        "windspeedMiles": "0",
    //        "windspeedKmph": "0",
    //        "winddirDegree": "0",
    //        "winddir16Point": "N",
    //        "precipMM": "0.0",
    //        "precipInches": "0.0",
    //        "humidity": "75",
    //        "visibility": "16",
    //        "visibilityMiles": "9",
    //        "pressure": "1027",
    //        "pressureInches": "31",
    //        "cloudcover": "75",
    //        "FeelsLikeC": "2",
    //        "FeelsLikeF": "35",
    //        "uvIndex": 2
    //      }
    //    ],
    //    "weather": [
    //      {
    //        "date": "2020-01-31",
    //        "astronomy": [
    //          {
    //            "sunrise": "07:16 AM",
    //            "sunset": "05:28 PM",
    //            "moonrise": "10:53 AM",
    //            "moonset": "11:56 PM",
    //            "moon_phase": "Waxing Crescent",
    //            "moon_illumination": "37"
    //          }
    //        ],
    //        "maxtempC": "8",
    //        "maxtempF": "47",
    //        "mintempC": "0",
    //        "mintempF": "32",
    //        "avgtempC": "4",
    //        "avgtempF": "39",
    //        "totalSnow_cm": "0.0",
    //        "sunHour": "5.4",
    //        "uvIndex": "1",
    //        "hourly": [
    //          {
    //            "time": "24",
    //            "tempC": "8",
    //            "tempF": "47",
    //            "windspeedMiles": "3",
    //            "windspeedKmph": "5",
    //            "winddirDegree": "163",
    //            "winddir16Point": "SSE",
    //            "weatherCode": "116",
    //            "weatherIconUrl": [
    //              {
    //                "value": "http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png"
    //              }
    //            ],
    //            "weatherDesc": [
    //              {
    //                "value": "Partly cloudy"
    //              }
    //            ],
    //            "precipMM": "0.0",
    //            "precipInches": "0.0",
    //            "humidity": "73",
    //            "visibility": "10",
    //            "visibilityMiles": "6",
    //            "pressure": "1025",
    //            "pressureInches": "31",
    //            "cloudcover": "52",
    //            "HeatIndexC": "4",
    //            "HeatIndexF": "39",
    //            "DewPointC": "-1",
    //            "DewPointF": "31",
    //            "WindChillC": "3",
    //            "WindChillF": "38",
    //            "WindGustMiles": "4",
    //            "WindGustKmph": "7",
    //            "FeelsLikeC": "3",
    //            "FeelsLikeF": "38",
    //            "chanceofrain": "0",
    //            "chanceofremdry": "80",
    //            "chanceofwindy": "0",
    //            "chanceofovercast": "32",
    //            "chanceofsunshine": "68",
    //            "chanceoffrost": "8",
    //            "chanceofhightemp": "0",
    //            "chanceoffog": "0",
    //            "chanceofsnow": "0",
    //            "chanceofthunder": "0",
    //            "uvIndex": "1"
    //          }
    //        ]
    //      }
    //    ],
    //    "ClimateAverages": [
    //      {
    //        "month": [
    //          {
    //            "index": "1",
    //            "name": "January",
    //            "avgMinTemp": "-2.8",
    //            "avgMinTemp_F": "27.0",
    //            "absMaxTemp": "6.884329",
    //            "absMaxTemp_F": "44.4",
    //            "avgDailyRainfall": "1.16"
    //          },
    //          {
    //            "index": "2",
    //            "name": "February",
    //            "avgMinTemp": "-1.3",
    //            "avgMinTemp_F": "29.6",
    //            "absMaxTemp": "12.126107",
    //            "absMaxTemp_F": "53.8",
    //            "avgDailyRainfall": "1.35"
    //          },
    //          {
    //            "index": "3",
    //            "name": "March",
    //            "avgMinTemp": "2.6",
    //            "avgMinTemp_F": "36.6",
    //            "absMaxTemp": "16.773355",
    //            "absMaxTemp_F": "62.2",
    //            "avgDailyRainfall": "1.42"
    //          },
    //          {
    //            "index": "4",
    //            "name": "April",
    //            "avgMinTemp": "8.4",
    //            "avgMinTemp_F": "47.2",
    //            "absMaxTemp": "21.486666",
    //            "absMaxTemp_F": "70.7",
    //            "avgDailyRainfall": "1.42"
    //          },
    //          {
    //            "index": "5",
    //            "name": "May",
    //            "avgMinTemp": "13.8",
    //            "avgMinTemp_F": "56.8",
    //            "absMaxTemp": "27.352",
    //            "absMaxTemp_F": "81.2",
    //            "avgDailyRainfall": "1.93"
    //          },
    //          {
    //            "index": "6",
    //            "name": "June",
    //            "avgMinTemp": "17.7",
    //            "avgMinTemp_F": "63.9",
    //            "absMaxTemp": "30.016666",
    //            "absMaxTemp_F": "86.0",
    //            "avgDailyRainfall": "1.74"
    //          },
    //          {
    //            "index": "7",
    //            "name": "July",
    //            "avgMinTemp": "20.5",
    //            "avgMinTemp_F": "68.8",
    //            "absMaxTemp": "33.809677",
    //            "absMaxTemp_F": "92.9",
    //            "avgDailyRainfall": "1.83"
    //          },
    //          {
    //            "index": "8",
    //            "name": "August",
    //            "avgMinTemp": "19.8",
    //            "avgMinTemp_F": "67.6",
    //            "absMaxTemp": "31.790194",
    //            "absMaxTemp_F": "89.2",
    //            "avgDailyRainfall": "1.75"
    //          },
    //          {
    //            "index": "9",
    //            "name": "September",
    //            "avgMinTemp": "16.6",
    //            "avgMinTemp_F": "61.8",
    //            "absMaxTemp": "28.3",
    //            "absMaxTemp_F": "82.9",
    //            "avgDailyRainfall": "1.55"
    //          },
    //          {
    //            "index": "10",
    //            "name": "October",
    //            "avgMinTemp": "10.7",
    //            "avgMinTemp_F": "51.3",
    //            "absMaxTemp": "22.657227",
    //            "absMaxTemp_F": "72.8",
    //            "avgDailyRainfall": "1.65"
    //          },
    //          {
    //            "index": "11",
    //            "name": "November",
    //            "avgMinTemp": "4.4",
    //            "avgMinTemp_F": "39.9",
    //            "absMaxTemp": "15.585834",
    //            "absMaxTemp_F": "60.1",
    //            "avgDailyRainfall": "1.45"
    //          },
    //          {
    //            "index": "12",
    //            "name": "December",
    //            "avgMinTemp": "1.2",
    //            "avgMinTemp_F": "34.1",
    //            "absMaxTemp": "13.506758",
    //            "absMaxTemp_F": "56.3",
    //            "avgDailyRainfall": "1.57"
    //          }
    //        ]
    //      }
    //    ]
    //  }
    //}

}