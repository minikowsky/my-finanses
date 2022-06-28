package com.example.myfinanses.data


data class Gold(
    val metal: String,
    val currency: String,
    val price: Double,
    val price_gram_24k: Double
)

/*{
  "timestamp": 1656187854,
  "metal": "XAU",
  "currency": "USD",
  "exchange": "FOREXCOM",
  "symbol": "FOREXCOM:XAUUSD",
  "prev_close_price": 1822.66,
  "open_price": 1822.66,
  "low_price": 1816.98,
  "high_price": 1831.84,
  "open_time": 1656028800,
  "price": 1827.07,
  "ch": 4.41,
  "chp": 0.24,
  "ask": 1828.03,
  "bid": 1826.03,
  "price_gram_24k": 58.7417,
  "price_gram_22k": 53.8465,
  "price_gram_21k": 51.399,
  "price_gram_20k": 48.9514,
  "price_gram_18k": 44.0562
}*/
