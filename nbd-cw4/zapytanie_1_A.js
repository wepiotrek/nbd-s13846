printjson(
    db.people.aggregate( 
        { 
            $group: {
                _id: "$sex",
                "srednia waga": {$avg: {$toDouble: "$weight"}},
                "sredni wzrost": {$avg: { $toDouble: "$height"}}
            }
        }).toArray()
	);