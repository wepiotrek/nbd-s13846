var map = function(){
	bmi = (parseFloat(this.weight) / Math.pow(parseFloat(this.height), 2)) * 100000;
	emit(this.nationality, bmi);
}
var reduce = function(nationality, values){
	return {
		"MinBMI": + Math.min(...values).toFixed(2),
		"MaxBMI": + Math.max(...values).toFixed(2),
		"AvgBMI": Array.sum(values) / values.length
	}
}
db.people.mapReduce(map, reduce, {out: "NBDMR4"});
printjson(db.NBDMR4.find().toArray());