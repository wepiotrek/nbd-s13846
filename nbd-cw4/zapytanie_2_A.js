  printjson(
    db.people.aggregate([{
            $unwind: "$credit"
        }, {
            $group: {
                _id: "$credit.currency",
                "Srodki: ": {$sum:{ $toDouble: "$credit.balance"}}
            }
        }, {
            $project: {
                _id: 0,
                waluta: "$_id",
                "Srodki: ": 1
            }
        }]).toArray());