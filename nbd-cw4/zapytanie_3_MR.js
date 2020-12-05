var map = function(){
	emit(this.job, this.job)
}

db.people.mapReduce(map, function(){}, {
		out: "NBDMR3"
	});
printjson(db.NBDMR3.find().toArray());