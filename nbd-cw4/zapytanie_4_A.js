printjson(
    db.people.aggregate([{
            $group: {
				
                _id: "$nationality",
				
				"MaxBMI": {
                    "$max": {
                        $divide: [
                            {$toDouble: "$weight"},
                            {$pow: [{$divide: [{$toDouble: "$height"}, 100]}, 2]}
                        ]}},
				
                "MinBMI": {
                    "$min": {
                        $divide: [
                            {$toDouble: "$weight"}, 
                            {$pow: [{$divide: [{$toDouble: "$height"}, 100]}, 2]}
                        ]}},
   
                "AvgBMI": {
                    "$avg": {
                        $divide: [
                            {$toDouble: "$weight"}, 
                            {$pow: [{$divide: [{$toDouble: "$height"}, 100]}, 2]}
                        ]}}
            }},
		]).toArray());
