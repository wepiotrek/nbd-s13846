printjson(db.people.updateMany({first_name: "Antonio"}, {$set:{ hobby: "pingpong"}}));
printjson(db.people.find({first_name: "Antonio"}, {first_name: 1, hobby: 1}).toArray());