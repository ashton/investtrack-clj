(defrecord Share 
  [code
   market-type
   name
   opening-price
   max-price
   min-price
   avg-price
   last-price
   best-buy
   best-sell
   trade-count
   trade-amount])

(defrecord Transaction [date quantity share value])