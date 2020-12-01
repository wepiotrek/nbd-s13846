printjson(db.people.updateMany({job: "Editor"}, {$unset: {email: ""}}));
printjson(db.people.find({job: "Editor"}).toArray());